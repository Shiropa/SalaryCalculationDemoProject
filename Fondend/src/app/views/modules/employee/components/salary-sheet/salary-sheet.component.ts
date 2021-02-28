import {Component, OnDestroy, OnInit} from '@angular/core';
import {IEmployee} from '../../models/employee';
import {EmployeeService} from '../../services/employee.service';
import {BankAccountService} from '../../../bank-account/services/bank-account.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-salary-sheet',
  templateUrl: './salary-sheet.component.html',
  styles: [``]
})
export class SalarySheetComponent implements OnInit, OnDestroy {

  employees: IEmployee[] = [];
  isTransfer: boolean;
  employeeOids: string[] = [];
  private subscription: Subscription;

  constructor(private service: EmployeeService,
              private bankAccountService: BankAccountService) {
  }

  ngOnInit() {
    this.getList();
  }

  private getList() {
    this.subscription = this.service.getList().subscribe(res => {
      this.employees = res;
    });
  }

  transferCheckedChanged(value) {
    this.isTransfer = value;
  }

  employeeSelection(checked, oid: string) {
    if (checked) {
      this.employeeOids.push(oid);
    } else {
      const index = this.employeeOids.findIndex(f => f === oid);
      if (index !== -1) {
        this.employeeOids.splice(index, 1);
      }
    }
  }

  transferSalary() {
    this.subscription = this.bankAccountService.insertSalary(this.employeeOids).subscribe(res => {
      if (res.length > 0) {
        this.getList();
        this.employeeOids = [];
        this.isTransfer = false;
      }
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
