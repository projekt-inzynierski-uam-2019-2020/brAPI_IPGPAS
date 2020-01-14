import { Injectable } from '@angular/core';
import {ServerTrial} from './analytics-components/trial/serverTrial';
import {ServerStudy} from './analytics-components/study/serverStudy';
import {Variable} from './call-models/variable';
import {VariableValuesSelect} from './analytics-components/variables/VariableValuesSelect';
import {StudyStatisticVariables} from './analytics-components/variables/studyStatisticVariables';
import {StudyVariables} from './analytics-components/variables/studyVariables';

@Injectable()
export class Globals {
  role = 'test';
  selectedServers: string[] = [];
  trialName = [];
  programName = [];
  commonCropName = [];
  serverArray = [];
  studiesIdArray = [];
  locationDbId = [];
  studiesArray = [];
  locationsArray = [];
  test = [];
  selectedServer;
  studyDbId = [];
  selectedServerTrials: ServerTrial[] = [];
  selectedServerStudies: ServerStudy[] = [];
  selectedStudiesDbId: ServerStudy[] = [];
  selectedVariablesValues: VariableValuesSelect[] = [];
  variables: Variable[] = [];
  studyStatisticVariables: StudyStatisticVariables[] = [];
  studyVariables: StudyVariables[] = [];
}
