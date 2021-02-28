import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {GradeListComponent} from './components/grade-list/grade-list.component';


const routes: Routes = [
  {
    path: 'list',
    component: GradeListComponent,
    data: {title: 'Grade List'}
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GradeRoutingModule { }
