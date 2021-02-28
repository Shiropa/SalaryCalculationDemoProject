import {IOidHolderRequestBody} from '../../../core/models/request';

export interface IGrade extends IOidHolderRequestBody {
  oid: string;
  gradeName: string;
  gradeNo: number;
  basicSalary: number;
}
