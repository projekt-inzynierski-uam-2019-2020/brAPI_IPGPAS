import {Injectable} from '@angular/core';
import {Resolve} from '@angular/router';
import {IPost} from './Ipost';
import {FetchBrapiResultService} from './fetch-brapi-result-service';
import {BrAPIDetailResponse} from '../call-models/response/brAPIDetailResponse';
import {Globals} from '../globals';
import {delay, map} from 'rxjs/operators';
import {TrialService} from '../call-services/trial/trial-service';
import {DetailResult} from '../call-models/response/detailResult';
import {pipe} from 'rxjs';

@Injectable()
export class DataBrapiResolve implements Resolve<DetailResult> {

  globals: Globals;

  constructor(private  fetchBrapiResult: FetchBrapiResultService, globals: Globals, private trialService: TrialService) {
    this.globals = globals;
  }

  resolve() {
    for (let i = 0; i < this.globals.selectedServers.length; i++) {
      this.trialService.getAllTrials(this.globals.selectedServers[i]).subscribe(
        call => {
          this.trialService.pushTrials(call);
        }
      );
      return this.trialService.getAllTrials(this.globals.selectedServers[i]);
    }
  }
}
