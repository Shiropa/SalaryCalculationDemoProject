import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DefaultLayoutComponent} from './container/default-layout';


const routes: Routes = [
  {
    path: '',
    component: DefaultLayoutComponent,
    children: [
      {
        path: '',
        redirectTo: '',
        pathMatch: 'full'
      },
      {
        path: 'grade',
        loadChildren: () => import('./views/modules/grade/grade.module').then(m => m.GradeModule)
      },
      {
        path: 'employee',
        loadChildren: () => import('./views/modules/employee/employee.module').then(m => m.EmployeeModule)
      },
      {
        path: 'bank-account',
        loadChildren: () => import('./views/modules/bank-account/bank-account.module').then(m => m.BankAccountModule)
      },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
