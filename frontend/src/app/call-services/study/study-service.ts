import {Injectable} from '@angular/core';
import {FetchBrapiResultService} from '../../fetch-services/fetch-brapi-result-service';
import {Globals} from '../../globals';
import {DetailResult} from '../../call-models/response/detailResult';

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
    this.fetchBrApiResponseService.getBrAPIDetailResult(brAPIServerUrl + '/brapi/v1/studies?studyDbId=' + studyDbId).subscribe(result => {
      this.pushStudies(result);
      return this.globals.studyArray;
    });
  }

  pushStudies(studies: DetailResult) {
    for ( const study of studies.data) {
      this.globals.studyArray.push({
        commonCropName: study.commonCropName,
        trialName: study.trialName,
        programName: study.programName,
        studyName: study.studyName
      });
    }
    return this.globals.studyArray;
  }
}
