import {Injectable} from '@angular/core';
import {Resolve} from '@angular/router';
import {FetchBrapiResultService} from '../../fetch-services/fetch-brapi-result-service';
import {Globals} from '../../globals';
import {TrialService} from './trial-service';

@Injectable()
export class TrialsResolve implements Resolve<any> {

  globals: Globals;

  constructor(private  fetchBrapiResult: FetchBrapiResultService, globals: Globals, private trialService: TrialService) {
    this.globals = globals;
  }

  resolve() {
    this.trialService.getAllTrials(this.globals.selectedServer).subscribe(call => {
    });
    return this.trialService.getAllTrials(this.globals.selectedServer);
  }
}
