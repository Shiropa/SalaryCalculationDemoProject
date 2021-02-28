// import {Injectable} from '@angular/core';
// import {HttpClient, HttpHeaders} from '@angular/common/http';
// import {throwError} from 'rxjs/internal/observable/throwError';
// import {Observable} from 'rxjs/internal/Observable';
//
// @Injectable({ providedIn: 'root' })
// export class RestClient {
//    constructor(private http: HttpClient) {
//    }
//    private get _headers(): any {
//      return {
//          headers: new HttpHeaders({
//            'Content-Type': 'application/json',
//            'Accept': 'application/json',
//          })
//        };
//    }
//
//    getFromFile(url: string): any {
//        return this.http.get(url, this._headers);
//    }
//
//    get(url: string): Observable<any> {
//        try {
//            return this.http.get<Observable<any>>(url, this._headers);
//        } catch (err) {
//            return throwError(err.message);
//        }
//    }
//    post(url: string, data: any): Observable<any> {
//        try {
//            return this.http.post<any>(url, data, this._headers);
//        } catch (err) {
//            return throwError(err.message);
//        }
//    }
// }
