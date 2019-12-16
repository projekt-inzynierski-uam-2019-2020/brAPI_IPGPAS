import {Study} from '../../call-models/study';
import {Germplasm} from '../../call-models/germplasm';

export class StudyColumn {
  study: Study;
  germplasms: Germplasm[];
  selected: boolean;
}

