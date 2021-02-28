import {Component, EventEmitter, OnDestroy, OnInit} from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {GradeService} from '../../services/grade.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-grade-balance-add',
  templateUrl: './grade-balance-add.component.html',
  styles: [``]
})
export class GradeBalanceAddComponent implements OnInit, OnDestroy {

  salary: { basicSalary: number, increased: number } = {basicSalary: 0, increased: 0};
  closeEventEmitter = new EventEmitter<boolean>();
  private subscription: Subscription;

  constructor(public activeModal: NgbActiveModal,
              private gradeService: GradeService) {
  }

  ngOnInit() {
  }

  insertSalary() {
    this.subscription = this.gradeService.insertSalary(this.salary).subscribe(res => {
      this.closeEventEmitter.emit(true);
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
