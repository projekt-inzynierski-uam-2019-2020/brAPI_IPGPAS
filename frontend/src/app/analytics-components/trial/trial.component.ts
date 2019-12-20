import {Component, OnInit} from '@angular/core';
import {TrialService} from '../../call-services/trial/trial-service';
import {Trial} from '../../call-models/trial';
import {Globals} from '../../globals';
import {TrialRow} from './trialRow';
import {ServerTrial} from './serverTrial';
import {Router} from '@angular/router';
import {CommonCropName} from './commonCropName';

@Component({
  selector: 'app-trial',
  templateUrl: './trial.component.html',
  styleUrls: ['./trial.component.scss']
})
export class TrialComponent implements OnInit {
  globals: Globals;
  trialService: TrialService;

  trialRows: TrialRow[] = [];
  filteredTrialRows: TrialRow[] = [];
  commonCropNames: CommonCropName[] = [];
  serverTrials: ServerTrial[] = [];

  optionalFiltersShow = false;
  isLoading = true;

  constructor(globals: Globals, trialService: TrialService, private router: Router) {
    this.globals = globals;
    this.trialService = trialService;
  }

  ngOnInit() {
    this.fetchTrialsFromSelectedServers();
  }

  fetchTrialsFromSelectedServers() {
    let loadingCounter = 0;
    this.globals.selectedServers.map(serverUrl => this.trialService.getAllTrials(serverUrl)
      .subscribe(fetchedTrials => {
        this.setTrialRows(fetchedTrials);
        this.setServerTrials(serverUrl, fetchedTrials);
        this.setFilterCommonCropNames(fetchedTrials);
        loadingCounter = loadingCounter + 1;
        if (loadingCounter === this.globals.selectedServers.length) {
          this.isLoading = false;
        }
      }));
  }

  setTrialRows(trials: Trial[]) {
    trials.map(trial => this.trialRows.push({trial: trial, selected: false}));
    trials.map(trial => this.filteredTrialRows.push({trial: trial, selected: false}));
  }

  setServerTrials(serverUrl: string, trials: Trial[]) {
    trials.map(trial => this.serverTrials.push({serverUrl: serverUrl, trial: trial}));
  }

  setFilterCommonCropNames(trials: Trial[]) {
    for (const trial of trials) {
      if (!this.commonCropNames.some(item => item.commonCropName === trial.commonCropName)) {
        if (trial.commonCropName) {
          this.commonCropNames.push({commonCropName: trial.commonCropName, selected: false});
        }
      }
    }
  }

  filterTrialRowsByCommonCropNames() {
    // get selected commonCropoNames
    const selectedCommonCropNames = this.commonCropNames
      .filter(commonCropNameCheckbox => commonCropNameCheckbox.selected)
      .map(commonCropNameCheckbox => commonCropNameCheckbox.commonCropName);

    // filter
    if (selectedCommonCropNames.length === 0) {
      this.filteredTrialRows = this.trialRows;
    } else {
      this.filteredTrialRows = this.trialRows
        .filter(trialRow => {
          for (const commonCropName of selectedCommonCropNames) {
            if (commonCropName === trialRow.trial.commonCropName) {
              return true;
            }
          }
        });
    }

  }

  setSelectedServerTrialsFromSelectedFilteredRows() {
    // get selected filtered trials
    const selectedTrialRows = this.filteredTrialRows
      .filter(trialRow => trialRow.selected)
      .map(trialRow => trialRow.trial);

    // get server trials with trials equal selected filtered trials
    this.globals.selectedServerTrials = this.serverTrials
      .filter(serverTrial => {
        for (const selectedTrialRow of selectedTrialRows) {
          if (Object.is(selectedTrialRow, serverTrial.trial)) {
            return true;
          }
        }
      });
    this.globals.selectedServerTrials.length > 0 ? this.router.navigate(['/servers/study']) : alert('You have to select trial first.');
  }

  checkValue(event: any) {
    if (event === 'checked') {
      for (const trialBox of this.trialRows) {
        trialBox.selected = true;
      }
    } else {
      for (const trialBox of this.trialRows) {
        trialBox.selected = false;
      }
    }
  }
}
