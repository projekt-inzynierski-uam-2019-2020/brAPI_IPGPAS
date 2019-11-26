import {Component, OnInit} from '@angular/core';
import {TrialService} from '../../call-services/trial/trial-service';
import {ServersService} from '../../servers-services/servers-service';
import {Trial} from '../../call-models/trial';
import {Globals} from '../../globals';
import {FetchBrapiResultService} from '../../fetch-services/fetch-brapi-result-service';
import {ActivatedRoute, Router, Routes} from '@angular/router';
import {StudyService} from '../../call-services/study/study-service';

@Component({
  selector: 'app-trial',
  templateUrl: './trial.component.html',
  styleUrls: ['./trial.component.scss']
})
export class TrialComponent implements OnInit {
  checkboxes = [];
  serversService: ServersService;
  trialService: TrialService;
  studyService: StudyService;
  trials: Trial[];
  globals: Globals;

  constructor(trialService: TrialService, serversService: ServersService, globals: Globals, private fetchBrapiResult: FetchBrapiResultService, private routes: ActivatedRoute, studyService: StudyService) {
    this.trialService = trialService;
    this.serversService = serversService;
    this.globals = globals;
    this.studyService = studyService;
  }

  ngOnInit() {
    for ( const trial of this.routes.snapshot.data.dataResolve.data){
      this.checkboxes.push({
        trialName: trial.trialName,
        commonCropName: trial.commonCropName,
        programName: trial.programName,
        studies: trial.studies,
        selected: false
      });
    }
  }

  showStudies() {
    const resultStudies = this.checkboxes.filter((checkbox) => {
      return checkbox.selected;
    })
      .map((checkbox) => {
        this.trialService.getTrialsByCommonCropName(this.globals.selectedServer, checkbox.commonCropName);
        return checkbox.studies;
      });
    for (const trials of resultStudies) {
      for (let i = 0; i < trials.length; i++) {
        this.studyService.getStudyByStudyDbId(this.globals.selectedServer, trials[i].studyDbId);
        this.globals.studyDbId.push(trials[i].studyDbId);
      }
    }
  }

}
