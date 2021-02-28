import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {GradeRoutingModule} from './grade-routing.module';
import {GradeListComponent} from './components/grade-list/grade-list.component';
import {GradeBalanceAddComponent} from './components/grade-balance-add/grade-balance-add.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FormsModule} from '@angular/forms';


@NgModule({
  declarations: [
    GradeListComponent,
    GradeBalanceAddComponent,
  ],
  imports: [
    CommonModule,
    GradeRoutingModule,
    NgbModule,
    FormsModule
  ],
  entryComponents: [
    GradeBalanceAddComponent
  ]
})
export class GradeModule {
}
