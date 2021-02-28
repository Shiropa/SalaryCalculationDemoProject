import {Component, OnDestroy, OnInit} from '@angular/core';
import {GradeService} from '../../services/grade.service';
import {IGrade} from '../../models/grade';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {GradeBalanceAddComponent} from '../grade-balance-add/grade-balance-add.component';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-grade-list',
  templateUrl: './grade-list.component.html',
  styles: [``]
})
export class GradeListComponent implements OnInit, OnDestroy {

  grades: IGrade[] = [];
  private subscription: Subscription;

  constructor(private gradeService: GradeService,
              private modalService: NgbModal) { }

  ngOnInit() {
    this.getList();
  }

  private getList() {
    this.subscription = this.gradeService.getList().subscribe(res => {
      this.grades = res.sort((a, b) => a.gradeNo - b.gradeNo);
    });
  }

  addBasic() {
    const modalRef = this.modalService.open(GradeBalanceAddComponent);
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
