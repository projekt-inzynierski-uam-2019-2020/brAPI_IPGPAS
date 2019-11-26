import {Injectable} from '@angular/core';
import {FetchBrapiResultService} from '../../fetch-services/fetch-brapi-result-service';
import {Globals} from '../../globals';
import {DetailResult} from '../../call-models/response/detailResult';

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
    return this.fetchBrApiResponseService.getBrAPIDetailResult(brAPIServerUrl + '/brapi/v1/trials');
  }

  getTrialsByCommonCropName(brAPIServerUrl: string, commonCropName: string) {
    let data: any[] = [];
    this.fetchBrApiResponseService.getBrAPIDetailResult(brAPIServerUrl + '/brapi/v1/trials?commonCropName=' + commonCropName).subscribe(result => {
      data = result.data;
    });
    return data;
  }

  pushTrials(trials: DetailResult) {
    for ( const trial of trials.data) {
      this.globals.trialsArray.push({
        commonCropName: trial.commonCropName,
        trialName: trial.trialName,
        programName: trial.programName
      });
    }
    return this.globals.trialsArray;
  }


}
