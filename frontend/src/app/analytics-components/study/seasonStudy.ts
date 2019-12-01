import {Season} from '../../call-models/season';
import {Study} from '../../call-models/study';

export class SeasonStudy {
  season: Season;
  year: number;
  study: Study;
  serverUrl: string;
}
