import { Injectable } from '@angular/core';
import {ServerTrial} from './analytics-components/trial/serverTrial';
import {ServerStudy} from './analytics-components/study/serverStudy';

@Injectable()
export class Globals {
  role = 'test';
  selectedServers: string[] = [];
  serversArray2 = [];
  trialName = [];
  programName = [];
  commonCropName = [];
  serverArray = [];
  studiesIdArray = [];
  trialsArray = [];
  locationDbId = [];
  studiesArray = [];
  locationsArray = [];
  test = [];
  selectedServer;
  studyDbId = [];
  commonCropsArray = [];
  selectedServerTrials: ServerTrial[] = [];
  selectedServerStudies: ServerStudy[] = [];
}
