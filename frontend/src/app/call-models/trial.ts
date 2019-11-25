import {TrialStudy} from './trialStudy';

export class Trial {
  active: boolean;
  additionalInfo: any;
  commonCropName: string;
  documentationURL: string;
  endDate: string;
  programDbId: string;
  programName: string;
  startDate: string;
  studies: TrialStudy[];
  locationDbId: string;
  studyDbId: string;
  studyName: string;
  trialDbId: string;
  trialName: string;
}
