import {Injectable} from '@angular/core';
import {FetchBrapiResultService} from '../../fetch-services/fetch-brapi-result-service';
import {Globals} from '../../globals';
import {DetailResult} from '../../call-models/response/detailResult';
import {catchError, map} from 'rxjs/operators';
import {of, throwError} from 'rxjs';

@Injectable()
export class TrialService {

  fetchBrApiResponseService: FetchBrapiResultService;
  globals: Globals;

  /* services */
  constructor(fetchBrApiResponseService: FetchBrapiResultService, globals: Globals) {
    this.fetchBrApiResponseService = fetchBrApiResponseService;
    this.globals = globals;
  }

  getAllTrials(brAPIServerUrl: string) {
    return this.fetchBrApiResponseService.getBrAPIDetailResult(brAPIServerUrl + '/brapi/v1/trials')
      .pipe(map(result => result.data),  catchError( error => {
        return of(null);
      }));
  }

  getTrialsByCommonCropName(brAPIServerUrl: string, commonCropName: string) {
    return this.fetchBrApiResponseService.getBrAPIDetailResult(brAPIServerUrl + '/brapi/v1/trials?commonCropName=' + commonCropName).pipe(map(result => result.data));
  }


}
