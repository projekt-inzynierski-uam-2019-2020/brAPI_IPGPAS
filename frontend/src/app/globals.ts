import { Injectable } from '@angular/core';
import {ServerTrial} from './analytics-components/trial/serverTrial';
import {ServerStudy} from './analytics-components/study/serverStudy';
import {Variable} from './call-models/variable';
import {ServerVariable} from './analytics-components/variables/serverVariable';
import {VariableStudy} from './analytics-components/variables/variableStudy';
import {VariableSelect} from './analytics-components/variables/variableSelect';
import {VariableValuesSelect} from './analytics-components/variables/VariableValuesSelect';

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
  selectedStudiesDbId: ServerStudy[] = [];
  selectedVariables: VariableSelect[] = [];
  selectedVariablesValues: VariableValuesSelect[] = [];
  variables: Variable[] = [];
}
