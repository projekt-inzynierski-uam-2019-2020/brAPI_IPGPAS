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
  serversService: ServersService;
  trialService: TrialService;
  trials: Trial[];
  globals: Globals;

  constructor(trialService: TrialService, serversService: ServersService, globals: Globals, private fetchBrapiResult: FetchBrapiResultService, private routes: ActivatedRoute) {
    this.trialService = trialService;
    this.serversService = serversService;
    this.globals = globals;
  }

  ngOnInit() {
    for ( const trial of this.routes.snapshot.data.dataResolve.data){
      this.checkboxes.push({
        trialName: trial.trialName,
        commonCropName: trial.commonCropName,
        programName: trial.programName,
        selected: false
      });
    }

  }
}
