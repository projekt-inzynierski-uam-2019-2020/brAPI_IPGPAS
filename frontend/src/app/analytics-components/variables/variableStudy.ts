import {Study} from '../../call-models/study';
import {Variable} from '../../call-models/variable';
import {VariableSelect} from './variableSelect';

export class VariableStudy {
  study: Study;
  variable: string[];
  selected: boolean;
  serverUrl: string;
}
