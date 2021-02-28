export interface IRequestHeader {
  requestId: string;
  requestSource: string;
  requestSourceService: string;
  requestClient: string;
  requestType: string;
  requestTime: string;
  requestVersion: string;
  requestTimeoutInSeconds: number;
  requestRetryCount: number;
}

// tslint:disable-next-line:no-empty-interface
export interface IRequestBody {
}

export interface IOidHolderRequestBody {
  oid: string;
}

export interface IOidsHolderRequestBody {
  oids: string[];
}

export interface IValueOidsHolderRequestBody {
  value: string;
  oids: string[];
}

export interface IValueHolderRequestBody {
  value: string;
}

export interface IOidValuesHolderRequestBody {
  oid: string;
  values: string[];
}

export interface IValuesHolderRequestBody {
  value: string[];
}

export interface IMultipleOidHolderRequestBody {
  employeeMasterInfoOid?: string;
  officeOid: string;
}

export interface IGetListByOidSetHolderRequestBody {
  oids: string[];
  strict?: string;
}

export interface IRequest<T extends IRequestBody> {
  header: IRequestHeader;
  meta: Object;
  body: T;
}

export interface IgetEmployeeByOfficeUnitPostOidsHolderRequestBody {
  officeUnitPostOids: string[];
  date?: string;
}

export interface IItemTrackingReportRequestBody {
  itemCategory: string;
  itemGroup: string;
  itemName: string;
  oids: string[];
}

export interface ITrackingHistroy {
  value: string;
}

export interface ITrackingPossessions {
  typeBn: string;
  officeUnit: string;
  officeUnitPost: string;
  startDate: string;
  oid: string;
  type: string;
}

export interface IItemDirectInRequestBody {
  storeOid: string;
  itemCategoryOid: string;
  itemGroupOid: string;
  itemOid: string;
  startDate: string;
  endDate: string;
}

export interface IEmployeeAndUnitWiseAssetRequestBody {
  storeOid: string;
  type: string;
  officeUnitOid: string;
  postOid: string;
  startDate: string;
  endDate: string;
}

export interface ILedgerSumarryRequestBody {
  itemCatOid: string;
  itemGroupOid: string;
  itemOid: string;
  storeOid: string;
  startDate: string;
  endDate: string;
}
export interface IAssetDecommissionedReportRequestBody {
  itemCategoryOid: string;
  itemGroupOid: string;
  itemOid: string;
  storeOid: string[];

}
export interface IAssetDepreciationReportRequestBody {
  itemCategoryOid: string;
  itemGroupOid: string;
  itemOid: string;
  fromDate: string;
  toDate: string;
  actionType: string;
  storeOid: string[];
}


