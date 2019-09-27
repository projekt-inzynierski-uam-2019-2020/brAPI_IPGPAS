import {AdditionalInfo} from '../studies/additionalInfo';
import {Studies} from '../studies/studies';

export class Trials {
  active: boolean;
  additionalInfo: AdditionalInfo;
  commonCropName: string;
  documentationURL: string;
  endDate: string;
  programDbId: string;
  programName: string;
  startDate: string;
  studies: Studies;
  locationDbId: string;
  studyDbId: string;
  studyName: string;
  trialDbId: string;
  trialName: string;
}
