import {IResponse, IResponseData} from '../models/response';
import {IRequestBody} from '../models/request';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {CREATE_URL, DELETE_URL, GET_BY_OID_URL, GET_LIST_URL, ROOT, UPDATE_URL} from '../constant/api';

export abstract class CrudRequestService<I extends IRequestBody & IResponseData> {

  protected constructor(private httpClient: HttpClient,
                        protected _BASE_URL: string) {
  }

  getList(): Observable<I[]> {
    return this.httpClient.get<I[]>(ROOT + this._BASE_URL + GET_LIST_URL);
  }

  getByOid(oid: string): Observable<I> {
    return this.httpClient.get<I>(ROOT + this._BASE_URL + GET_BY_OID_URL + + '/' + oid);
  }

  create(i: I): Observable<I> {
    return this.httpClient.post<I>(ROOT + this._BASE_URL + CREATE_URL, i);
  }

  update(i: I): Observable<I> {
    return this.httpClient.put<I>(ROOT + this._BASE_URL + UPDATE_URL, i);
  }

  delete(oid: string): Observable<{value: boolean}> {
    return this.httpClient.delete<{value: boolean}>(ROOT + this._BASE_URL + DELETE_URL + '/' + oid);
  }

}
