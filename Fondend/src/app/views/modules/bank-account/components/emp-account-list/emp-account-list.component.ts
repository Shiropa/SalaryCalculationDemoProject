import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {IBankAccount} from '../../models/bank-account';
import {BankAccountService} from '../../services/bank-account.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {BankAddComponent} from '../../shared/components/bank-add/bank-add..component';
import {IEmployee} from '../../../employee/models/employee';
import {EmployeeAddComponent} from '../../../employee/components/employee-add/employee-add.component';

@Component({
  selector: 'app-emp-account-list',
  templateUrl: './emp-account-list.component.html',
  styles: [``]
})
export class EmpAccountListComponent implements OnInit, OnDestroy {

  accounts: IBankAccount[] = [];
  private subscription: Subscription;

  constructor(private service: BankAccountService,
              private modalService: NgbModal) { }

  ngOnInit() {
    this.getList();
  }

  private getList() {
    this.subscription = this.service.geEmployeeAccounts().subscribe(res => {
      this.accounts = res;
    });
  }

  add() {
    const modalRef = this.modalService.open(BankAddComponent);
    modalRef.componentInstance.forEmployee = 'Yes';
    modalRef.componentInstance.closeEventEmitter.subscribe(res => {
      if (res) {
        this.getList();
        modalRef.close();
      }
    });
  }

  edit(bank: IBankAccount) {
    const modalRef = this.modalService.open(BankAddComponent);
    modalRef.componentInstance.forEmployee = 'Yes';
    modalRef.componentInstance.oid = bank.oid;
    modalRef.componentInstance.bank = bank;
    modalRef.componentInstance.closeEventEmitter.subscribe(res => {
      if (res) {
        this.getList();
        modalRef.close();
      }
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
