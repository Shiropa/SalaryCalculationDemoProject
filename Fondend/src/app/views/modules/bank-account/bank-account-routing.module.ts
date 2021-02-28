import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MainAccountViewComponent} from './components/main-account/main-account-view.component';
import {EmpAccountListComponent} from './components/emp-account-list/emp-account-list.component';


const routes: Routes = [
  {
    path: 'emp-acc-list',
    component: EmpAccountListComponent
  },
  {
    path: 'account-info',
    component: MainAccountViewComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BankAccountRoutingModule { }
