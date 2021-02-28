import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {EmployeeRoutingModule} from './employee-routing.module';
import {EmployeeListComponent} from './components/employee-list/employee-list.component';
import {SalarySheetComponent} from './components/salary-sheet/salary-sheet.component';
import {EmployeeAddComponent} from './components/employee-add/employee-add.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FormsModule} from '@angular/forms';


@NgModule({
  declarations: [
    EmployeeListComponent,
    SalarySheetComponent,
    EmployeeAddComponent
  ],
  imports: [
    CommonModule,
    EmployeeRoutingModule,
    NgbModule,
    FormsModule
  ],
  entryComponents: [
    EmployeeAddComponent
  ]
})
export class EmployeeModule {
}
