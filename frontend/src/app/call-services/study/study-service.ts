import {Injectable} from '@angular/core';
import {FetchBrapiResultService} from '../../fetch-services/fetch-brapi-result-service';
import {Globals} from '../../globals';
import {DetailResult} from '../../call-models/response/detailResult';
import {map} from 'rxjs/operators';
import {Observable} from 'rxjs';
import {Config} from 'protractor';
import {HttpResponse} from '@angular/common/http';

@Injectable()
export class StudyService {

  fetchBrApiResponseService: FetchBrapiResultService;
  globals: Globals;


  /* services */
  constructor(fetchBrApiResponseService: FetchBrapiResultService, globals: Globals) {
    this.fetchBrApiResponseService = fetchBrApiResponseService;
    this.globals = globals;
  }

  getAllStudies(brAPIServerUrl: string) {
    this.fetchBrApiResponseService.getBrAPIDetailResult(brAPIServerUrl + '/brapi/v1/studies');
  }

  getStudyByStudyDbId(brAPIServerUrl: string, studyDbId: string) {
    return this.fetchBrApiResponseService.getBrAPIDetailResult(brAPIServerUrl + '/brapi/v1/studies?studyDbId=' + studyDbId).pipe(map(result => result.data));
  }

  getStudyByTrialDbId(brAPIServerUrl: string, trialDbId: string) {
    if (brAPIServerUrl === 'https://teamprojectuam.tk') {
      return this.fetchBrApiResponseService.getBrAPIDetailResult(brAPIServerUrl + '/brapi/v1/studies?trialDbId=' + trialDbId).pipe(map(result => result.data));
    } else {
      return this.fetchBrApiResponseService.getBrAPIDetailResult(brAPIServerUrl + '/brapi/v1/studies-search?trialDbId=' + trialDbId).pipe(map(result => result.data));
    }
  }

}


