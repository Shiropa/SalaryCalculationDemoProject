import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {EmployeeListComponent} from './components/employee-list/employee-list.component';
import {SalarySheetComponent} from './components/salary-sheet/salary-sheet.component';

const routes: Routes = [
  {
    path: 'list',
    component: EmployeeListComponent
  },
  {
    path: 'salary-sheet',
    component: SalarySheetComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmployeeRoutingModule { }
