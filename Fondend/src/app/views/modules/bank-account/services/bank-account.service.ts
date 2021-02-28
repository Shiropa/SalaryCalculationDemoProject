import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {
  BANK_ACCOUNT,
  GET_EMP_ACCOUNTS,
  GET_MAIN_ACCOUNT,
  GET_NON_ASSIGNED_BANK_ACCOUNT,
  MAIN_ACC_BALANCE_ADD,
  ROOT,
  TRANSFER_SALARY
} from '../../../core/constant/api';
import {CrudRequestService} from '../../../core/services/crud-request.service';
import {Observable} from 'rxjs';
import {IBankAccount} from '../models/bank-account';

@Injectable({
  providedIn: 'root'
})
export class BankAccountService extends CrudRequestService<IBankAccount> {

  baseUrl = '';
  constructor(private http: HttpClient) {
    super(http, BANK_ACCOUNT );
    this.baseUrl = ROOT + BANK_ACCOUNT;
  }

  insertSalary(oidSet: string[]): Observable<IBankAccount[]> {
    return this.http.post<IBankAccount[]>(this.baseUrl + TRANSFER_SALARY, {oids : oidSet});
  }

  addBalanceInMainAccount(amount: number): Observable<IBankAccount> {
    return this.http.post<IBankAccount>(this.baseUrl + MAIN_ACC_BALANCE_ADD, {balance : amount});
  }

  getMainAccount(): Observable<IBankAccount> {
    return this.http.get<IBankAccount>(this.baseUrl + GET_MAIN_ACCOUNT);
  }

  geEmployeeAccounts(): Observable<IBankAccount[]> {
    return this.http.get<IBankAccount[]>(this.baseUrl + GET_EMP_ACCOUNTS);
  }

  getNonAssignedBankList(): Observable<IBankAccount[]> {
    return this.http.get<IBankAccount[]>(this.baseUrl + GET_NON_ASSIGNED_BANK_ACCOUNT);
  }

}
