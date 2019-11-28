import {Component, OnInit} from '@angular/core';
import {TrialService} from '../../call-services/trial/trial-service';
import {Trial} from '../../call-models/trial';
import {Globals} from '../../globals';
import {TrialCheckbox} from './trialCheckbox';
import {ServerTrial} from './serverTrial';
import {distinct} from 'rxjs/operators';

@Component({
  selector: 'app-trial',
  templateUrl: './trial.component.html',
  styleUrls: ['./trial.component.scss']
})
export class TrialComponent implements OnInit {
  globals: Globals;
  trialCheckboxes: TrialCheckbox[] = [];
  serverTrials: ServerTrial[] = [];

  trialService: TrialService;

  constructor(globals: Globals, trialService: TrialService) {
    this.globals = globals;
    this.trialService = trialService;
  }

  ngOnInit() {
    this.fetchTrialsFromSelectedServers();
  }

  fetchTrialsFromSelectedServers() {
    this.globals.selectedServers.map(serverUrl => this.trialService.getAllTrials(serverUrl)
      .subscribe(fetchedTrials => {
        this.setTrialCheckboxes(fetchedTrials);
        this.setServerTrials(serverUrl, fetchedTrials);
      }));
  }

  setTrialCheckboxes(trials: Trial[]) {
    trials.map(trial => this.trialCheckboxes.push({trial: trial, selected: false}));
  }

  setServerTrials(serverUrl: string, trials: Trial[]) {
    trials.map(trial => this.serverTrials.push({serverUrl: serverUrl, trial: trial}));
  }

  setSelectedServerTrials() {
    const selectedTrials = this.trialCheckboxes.filter(trialCheckbox => trialCheckbox.selected).map(trialCheckbox => trialCheckbox.trial);
    this.globals.selectedServerTrials = this.serverTrials
      .filter(serverTrial => {
        for (const selectedTrial of selectedTrials) {
        if (Object.is(selectedTrial, serverTrial.trial)) {
          return true;
        }
      }
    });
  }
}
