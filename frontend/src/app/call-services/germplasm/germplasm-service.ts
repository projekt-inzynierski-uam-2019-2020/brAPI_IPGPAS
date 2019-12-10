import {Injectable} from '@angular/core';
import {FetchBrapiResultService} from '../../fetch-services/fetch-brapi-result-service';
import {Globals} from '../../globals';
import {map} from 'rxjs/operators';

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
    return this.fetchBrApiResponseService.getBrAPIDetailResult(brAPIServerUrl + '/brapi/v1/studies/' + studyDbId + '/germplasm').pipe(map(result => result.data));
  }

  getVariablesByStudyDbId(brAPIServerUrl: string, studyDbId: string){
    return this.fetchBrApiResponseService.getTableVariables(brAPIServerUrl + '/brapi/v1/studies/' + studyDbId + '/table?format=csv');
  }


}
