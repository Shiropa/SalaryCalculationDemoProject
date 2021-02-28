import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {BankAccountRoutingModule} from './bank-account-routing.module';
import {MainAccountViewComponent} from './components/main-account/main-account-view.component';
import {ComAccBalanceAddComponent} from './components/com-acc-balance-add/com-acc-balance-add.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {EmpAccountListComponent} from './components/emp-account-list/emp-account-list.component';
import {BankAddComponent} from './shared/components/bank-add/bank-add..component';


@NgModule({
  declarations: [
    MainAccountViewComponent,
    ComAccBalanceAddComponent,
    EmpAccountListComponent,
    BankAddComponent
  ],
    imports: [
        CommonModule,
        BankAccountRoutingModule,
        NgbModule,
        FormsModule,
        ReactiveFormsModule
    ],
  entryComponents: [
    ComAccBalanceAddComponent,
    BankAddComponent
  ]
})
export class BankAccountModule { }
