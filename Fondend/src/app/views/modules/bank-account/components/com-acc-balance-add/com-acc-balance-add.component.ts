import {Component, EventEmitter, OnDestroy, OnInit} from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {Subscription} from 'rxjs';
import {BankAccountService} from '../../services/bank-account.service';

@Component({
  selector: 'app-com-acc-balance-add',
  templateUrl: './com-acc-balance-add.component.html',
  styles: [``]
})
export class ComAccBalanceAddComponent implements OnInit, OnDestroy {

  balance = 0;
  closeEventEmitter = new EventEmitter<boolean>();
  private subscription: Subscription;

  constructor(public activeModal: NgbActiveModal,
              private service: BankAccountService) {
  }

  ngOnInit() {
  }

  addBalanceInMainAccount() {
    this.subscription = this.service.addBalanceInMainAccount(this.balance).subscribe(res => {
      if (res) {
        this.closeEventEmitter.emit(true);
      }
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
