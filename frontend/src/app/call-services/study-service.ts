import {Injectable} from '@angular/core';
import {FetchBrapiResultService} from '../fetch-services/fetch-brapi-result-service';

@Injectable()
export class StudyService {

  fetchBrApiResponseService: FetchBrapiResultService;

  /* services */
  constructor(fetchBrApiResponseService: FetchBrapiResultService) {
    this.fetchBrApiResponseService = fetchBrApiResponseService;
  }

  getAllStudies(brAPIServerUrl: string) {
    this.fetchBrApiResponseService.getBrAPIDetailResult(brAPIServerUrl + '/brapi/v1/studies').subscribe(result => {
      //return result.data;
    });
  }

  getStudyByStudyDbId(brAPIServerUrl: string, studyDbId: string) {
    this.fetchBrApiResponseService.getBrAPIDetailResult(brAPIServerUrl + 'brapi/v1/studies?studyDbId=' + studyDbId).subscribe(result => {
      //return result.data;
    });
  }
}
