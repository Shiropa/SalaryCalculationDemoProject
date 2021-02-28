import {Component, EventEmitter, OnDestroy, OnInit} from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {Subscription} from 'rxjs';
import {EmployeeService} from '../../services/employee.service';
import {IEmployee} from '../../models/employee';
import {GradeService} from '../../../grade/services/grade.service';
import {BankAccountService} from '../../../bank-account/services/bank-account.service';
import {map, switchMap} from 'rxjs/operators';
import {IGrade} from '../../../grade/models/grade';
import {IBankAccount} from '../../../bank-account/models/bank-account';

@Component({
  selector: 'app-employee-add',
  templateUrl: './employee-add.component.html',
  styles: [``]
})
export class EmployeeAddComponent implements OnInit, OnDestroy {

  oid = '';
  employee = {} as IEmployee;
  closeEventEmitter = new EventEmitter<boolean>();
  private subscription: Subscription;
  grades: IGrade[] = [];
  banks: IBankAccount[] = [];

  constructor(public activeModal: NgbActiveModal,
              private service: EmployeeService,
              private bankAccountService: BankAccountService,
              private gradeService: GradeService) {
  }

  ngOnInit() {
    this.subscription = this.bankAccountService.geEmployeeAccounts().pipe(
      switchMap(acc => this.gradeService.getList().pipe(
        map(grades => ({acc, grades}))
      ))
    ).subscribe(res => {
      this.grades = res.grades;
      this.banks = res.acc;
    });
  }

  add() {
    this.employee.oid = this.oid;
    const a = this.oid ? this.service.update(this.employee) : this.service.create(this.employee);
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
