import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {EMPLOYEE, ROOT} from '../../../core/constant/api';
import {CrudRequestService} from '../../../core/services/crud-request.service';
import {IEmployee} from '../models/employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService extends CrudRequestService<IEmployee> {

  baseUrl = '';
  constructor(private http: HttpClient) {
    super(http, EMPLOYEE );
    this.baseUrl = ROOT + EMPLOYEE;
  }

  // insertSalary(salary: {basicSalary: number, increased: number}): Observable<IGrade[]> {
  //   return this.http.post<IGrade[]>(this.baseUrl + INSERT_SALARY, salary);
  // }

}
