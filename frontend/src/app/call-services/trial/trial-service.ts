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
    this.fetchBrApiResponseService.getBrAPIDetailResult(brAPIServerUrl + '/brapi/v1/trials?commonCropName=' + commonCropName).subscribe(result => {
      this.pushCommonCrops(result);
    });
   return this.globals.commonCropsArray;
  }

  pushAllTrials(trials: DetailResult) {
    for ( const trial of trials.data) {
      this.globals.trialsArray.push({
        commonCropName: trial.commonCropName,
        trialName: trial.trialName,
        programName: trial.programName
      });
    }
    return this.globals.trialsArray;
  }

  pushCommonCrops(commonCrops: DetailResult) {
    for ( const commonCrop of commonCrops.data) {
      this.globals.commonCropsArray.push({
        commonCropName: commonCrop.commonCropName,
        programName: commonCrop.programName
      });
    }
    return this.globals.commonCropsArray;
  }


}
