import {Season} from './season';

export class Study {
  active: boolean;
  additionalInfo: any;
  commonCropName: string;
  documentationURL: string;
  endDate: string;
  locationDbId: string;
  locationName: string;
  name: string;
  programDbId: string;
  programName: string;
  seasons: Season[];
  startDate: string;
  studyDbId: string;
  studyName: string;
  studyType: string;
  studyTypeDbId: string;
  studyTypeName: string;
  trialDbId: string;
  trialName: string;
}
