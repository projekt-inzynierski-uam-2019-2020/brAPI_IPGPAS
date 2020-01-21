import {Injectable} from '@angular/core';
import {FetchBrapiResultService} from '../../fetch-services/fetch-brapi-result-service';
import {Globals} from '../../globals';
import {catchError, map} from 'rxjs/operators';
import {of} from 'rxjs';

@Injectable()
export class GermplasmService {

  fetchBrApiResponseService: FetchBrapiResultService;
  globals: Globals;

  /* services */
  constructor(fetchBrApiResponseService: FetchBrapiResultService, globals: Globals) {
    this.fetchBrApiResponseService = fetchBrApiResponseService;
    this.globals = globals;
  }

  getGermplasmByStudyDbId(brAPIServerUrl: string, studyDbId: string){
    return this.fetchBrApiResponseService.getBrAPIDetailResult(brAPIServerUrl + '/brapi/v1/studies/' + studyDbId + '/germplasm').pipe(map(result => result.data), catchError( error => {
      return of(null);
    }));
  }

  getVariablesByStudyDbId(brAPIServerUrl: string, studyDbId: string){
    return this.fetchBrApiResponseService.getBrAPIDetailResult(brAPIServerUrl + '/brapi/v1/studies/' + studyDbId + '/table').pipe(map(result => result), catchError( error => {
      return of(null);
    }));
  }


}
