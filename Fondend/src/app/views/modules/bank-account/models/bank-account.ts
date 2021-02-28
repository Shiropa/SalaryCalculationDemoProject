import {IOidHolderRequestBody} from '../../../core/models/request';

export interface IBankAccount extends IOidHolderRequestBody {
  oid: string;
  accountType: number;
  forEmployee: string;
  accountNumber: string;
  accountName: string;
  branchName: string;
  bankName: string;
  currentBalance: number;
  totalDebit: number;
  totalCredit: number;
}
