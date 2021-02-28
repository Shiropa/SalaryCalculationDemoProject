import {Component, EventEmitter, OnDestroy, OnInit} from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {Subscription} from 'rxjs';
import {IBankAccount} from '../../../models/bank-account';
import {BankAccountService} from '../../../services/bank-account.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-bank-add',
  templateUrl: './bank-add.component.html',
  styles: [``]
})
export class BankAddComponent implements OnInit, OnDestroy {

  oid = '';
  forEmployee: 'Yes' | 'No';
  bank = {} as IBankAccount;
  closeEventEmitter = new EventEmitter<boolean>();
  private subscription: Subscription;

  constructor(public activeModal: NgbActiveModal,
              private service: BankAccountService) {
  }

  ngOnInit() {
  }

  add() {
    this.bank.forEmployee = this.forEmployee;
    this.bank.oid = this.oid;
    const a = this.oid ? this.service.update(this.bank) : this.service.create(this.bank);
    this.subscription = a.subscribe(res => {
      if (res) {
        this.closeEventEmitter.emit(true);
      }
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
