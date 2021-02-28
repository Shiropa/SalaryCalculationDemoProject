import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {BankAccountService} from '../../services/bank-account.service';
import {IBankAccount} from '../../models/bank-account';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {GradeBalanceAddComponent} from '../../../grade/components/grade-balance-add/grade-balance-add.component';
import {ComAccBalanceAddComponent} from '../com-acc-balance-add/com-acc-balance-add.component';
import {BankAddComponent} from '../../shared/components/bank-add/bank-add..component';

@Component({
  selector: 'app-main-account',
  templateUrl: './main-account-view.component.html',
  styles: [``]
})
export class MainAccountViewComponent implements OnInit, OnDestroy {

  bank: IBankAccount;
  private subscription: Subscription;

  constructor(private service: BankAccountService,
              private modalService: NgbModal) { }

  ngOnInit() {
    this.getMainAccount();
  }

  private getMainAccount() {
    this.subscription = this.service.getMainAccount().subscribe(res => {
      this.bank = res;
    });
  }

  add() {
    const modalRef = this.modalService.open(ComAccBalanceAddComponent);
    modalRef.componentInstance.closeEventEmitter.subscribe(res => {
      if (res) {
        this.getMainAccount();
        modalRef.close();
      }
    });
  }

  accountAdd() {
    const modalRef = this.modalService.open(BankAddComponent);
    modalRef.componentInstance.forEmployee = 'No';
    modalRef.componentInstance.closeEventEmitter.subscribe(res => {
      if (res) {
        this.getMainAccount();
        modalRef.close();
      }
    });
  }

  edit() {
    const modalRef = this.modalService.open(BankAddComponent);
    modalRef.componentInstance.forEmployee = 'No';
    modalRef.componentInstance.oid = this.bank.oid;
    modalRef.componentInstance.bank = this.bank;
    modalRef.componentInstance.closeEventEmitter.subscribe(res => {
      if (res) {
        this.getMainAccount();
        modalRef.close();
      }
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
