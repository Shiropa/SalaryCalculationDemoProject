import {Component, OnDestroy, OnInit} from '@angular/core';
import {IEmployee} from '../../models/employee';
import {EmployeeService} from '../../services/employee.service';
import {Subscription} from 'rxjs';
import {BankAddComponent} from '../../../bank-account/shared/components/bank-add/bank-add..component';
import {BankAccountService} from '../../../bank-account/services/bank-account.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {EmployeeAddComponent} from '../employee-add/employee-add.component';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styles: [``]
})
export class EmployeeListComponent implements OnInit, OnDestroy {

  employees: IEmployee[] = [];
  private subscription: Subscription;

  constructor(private service: EmployeeService,
              private modalService: NgbModal) { }

  ngOnInit() {
    this.getList();
  }

  private getList() {
    this.subscription = this.service.getList().subscribe(res => {
      this.employees = res;
    });
  }

  add() {
    const modalRef = this.modalService.open(EmployeeAddComponent);
    modalRef.componentInstance.closeEventEmitter.subscribe(res => {
      if (res) {
        this.getList();
        modalRef.close();
      }
    });
  }

  edit(employee: IEmployee) {
    const modalRef = this.modalService.open(EmployeeAddComponent);
    modalRef.componentInstance.oid = employee.oid;
    modalRef.componentInstance.employee = employee;
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

  delete(employee: IEmployee) {
    this.subscription = this.service.delete(employee.oid).subscribe(res => {
      if (res.value) {
        this.getList();
      }
    });
  }
}
