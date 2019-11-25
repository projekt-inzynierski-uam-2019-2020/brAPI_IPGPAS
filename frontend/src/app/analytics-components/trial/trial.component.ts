import {Component, OnInit} from '@angular/core';
import {TrialService} from '../../call-services/trial/trial-service';
import {ServersService} from '../../servers-services/servers-service';
import {Trial} from '../../call-models/trial';
import {Globals} from '../../globals';
import {FetchBrapiResultService} from '../../fetch-services/fetch-brapi-result-service';
import {ActivatedRoute, Router, Routes} from '@angular/router';
import {BrAPIDetailResponse} from '../../call-models/response/brAPIDetailResponse';
import {IPost} from '../../fetch-services/Ipost';
import {delay, map} from 'rxjs/operators';

@Component({
  selector: 'app-trial',
  templateUrl: './trial.component.html',
  styleUrls: ['./trial.component.scss']
})
export class TrialComponent implements OnInit {
  checkboxes = [];
  isShow = false;

  serversService: ServersService;
  trialService: TrialService;
  test: BrAPIDetailResponse;
  test2;

  postObject$;

  trials: Trial[];
  globals: Globals;
  isDataAvailable = false;

  constructor(trialService: TrialService, serversService: ServersService, globals: Globals, private fetchBrapiResult: FetchBrapiResultService, private routes: ActivatedRoute) {
    this.trialService = trialService;
    this.serversService = serversService;
    this.globals = globals;
  }

  ngOnInit() {
    this.getTrialsFromSelectedServers(this.serversService.getAllSelected());
    for (let i = 0; i < this.routes.snapshot.data.dataResolve.data.length; i++) {
      this.checkboxes.push({
        trialName: this.globals.trialsArray[i].trialName,
        commonCropName: this.globals.trialsArray[i].commonCropName,
        programName: this.globals.trialsArray[i].programName,
        studies: this.globals.trialsArray[i].studyDbId,
        selected: false
      });
    }


  }

  getTrialsFromSelectedServers(selectedServersUrls: string[]) {
    for (const serverUrl of selectedServersUrls) {
      this.trialService.getAllTrials(serverUrl);
    }
  }

  function() {

    return 'sdf';
  }
}
