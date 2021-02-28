import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {GRADE, INSERT_SALARY, ROOT} from '../../../core/constant/api';
import {CrudRequestService} from '../../../core/services/crud-request.service';
import {IGrade} from '../models/grade';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GradeService extends CrudRequestService<IGrade> {

  baseUrl = '';
  constructor(private http: HttpClient) {
    super(http, GRADE );
    this.baseUrl = ROOT + GRADE;
  }

  insertSalary(salary: {basicSalary: number, increased: number}): Observable<IGrade[]> {
    return this.http.post<IGrade[]>(this.baseUrl + INSERT_SALARY, salary);
  }

}
