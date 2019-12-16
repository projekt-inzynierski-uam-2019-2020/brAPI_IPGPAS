import {Study} from '../../call-models/study';
import {Variable} from '../../call-models/variable';

export class VariableStudy {
  study: Study;
  variable: Variable;
  selected: boolean;
  serverUrl: string;
}
