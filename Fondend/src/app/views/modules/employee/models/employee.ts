import {IOidHolderRequestBody} from '../../../core/models/request';
import {IGrade} from '../../grade/models/grade';
import {IBankAccount} from '../../bank-account/models/bank-account';

export interface IEmployee extends IOidHolderRequestBody {
  oid: string;
  employeeId: number;
  employeeName: string;
  address: string;
  mobile: string;
  bankAccountOid: string;
  gradeOid: string;
  bankAccount: IBankAccount;
  grade: IGrade;
  salary: Salary;
}

interface Salary {
  total: number;
  basic: number;
  houseRent: number;
  medicalAllowance: number;

}
