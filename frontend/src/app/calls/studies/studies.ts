import {AdditionalInfo} from './additionalInfo';
import {Seasons} from '../seasons/seasons';

export class Studies {
  active: boolean;
  additionalInfo: AdditionalInfo;
  commonCropName: string;
  documentationURL: string;
  endDate: string;
  locationDbId: string;
  locationName: string;
  name: string;
  programDbId: string;
  programName: string;
  seasons: Seasons[];
  startDate: string;
  studyDbId: string;
  studyName: string;
  studyType: string;
  studyTypeDbId: string;
  studyTypeName: string;
  trialDbId: string;
  trialName: string;
}
