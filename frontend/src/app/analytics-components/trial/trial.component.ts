import {Component, OnInit} from '@angular/core';
import {TrialService} from '../../call-services/trial/trial-service';
import {Trial} from '../../call-models/trial';
import {Globals} from '../../globals';
import {TrialCheckbox} from './trialCheckbox';
import {ServerTrial} from './serverTrial';
import {ServerTrials} from './serverTrials';
import {Router} from '@angular/router';

@Component({
  selector: 'app-trial',
  templateUrl: './trial.component.html',
  styleUrls: ['./trial.component.scss']
})
export class TrialComponent implements OnInit {
  globals: Globals;
  trialCheckboxes: TrialCheckbox[] = [];
  trialCommonCropCheckboxes: TrialCheckbox[] = [];
  serverTrial: ServerTrial[] = [];
  serverUniqCommonTrial: ServerTrial[] = [];
  serverTrials: ServerTrials[] = [];

  trials = [];
  isCropFilter = false;
  isShowStudies = true;
  selectedServerCommonCropsTrials: ServerTrial[] = [];
  optionalFiltersShow = false;
  isLoading = true;
  isChecked = false;

  trialService: TrialService;

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
        this.setTrialCheckboxes(fetchedTrials);
        this.setServerTrials(serverUrl, fetchedTrials);
        loadingCounter =  loadingCounter + 1;
        if (loadingCounter === this.globals.selectedServers.length) {
          this.isLoading = false;
        }
      }));
  }

  checkValue(event: any) {
    if (event === 'checked') {
      for (const trialBox of this.trialCheckboxes) {
        trialBox.selected = true;
      }
    } else {
      for (const trialBox of this.trialCheckboxes) {
        trialBox.selected = false;
      }
    }
  }

  setTrialCheckboxes(trials: Trial[]) {

    trials.map(trial => this.trialCheckboxes.push({trial: trial, selected: false}));

  }

  setServerTrials(serverUrl: string, trials: Trial[]) {
    trials.map(trial => this.serverTrial.push({serverUrl: serverUrl, trial: trial}));

    this.serverTrials.push({serverUrl: serverUrl, trials: trials});

    for (const trial of trials) {
      if (!this.serverUniqCommonTrial.some((item) => item.trial.commonCropName === trial.commonCropName)) {
        if (trial.commonCropName) {
          this.serverUniqCommonTrial.push({trial: trial, serverUrl: serverUrl});
          this.trialCommonCropCheckboxes.push({trial: trial, selected: false});
        }
      }
    }


  }

  setSelectedServerTrials() {
    const selectedTrials = this.trialCheckboxes.filter(trialCheckbox => trialCheckbox.selected).map(trialCheckbox => trialCheckbox.trial);

    this.globals.selectedServerTrials = this.serverTrial
      .filter(serverTrial => {
        for (const selectedTrial of selectedTrials) {
          if (Object.is(selectedTrial, serverTrial.trial)) {

            return true;
          }
        }

      });

    this.globals.selectedServerTrials.length > 0 ? this.router.navigate(['/servers/study']) : alert('You have to select trial first.');
  }


  setSelectedCrops() {
    this.serverTrial = [];
    this.isCropFilter = false;
    this.isShowStudies = true;
    this.trialCheckboxes = [];

    const selectedTrials = this.trialCommonCropCheckboxes.filter(trialCommonCropCheckboxes => trialCommonCropCheckboxes.selected).map(trialCommonCropCheckbox => trialCommonCropCheckbox.trial);

    this.selectedServerCommonCropsTrials = this.serverUniqCommonTrial
      .filter(serverTrial => {
        for (const selectedTrial of selectedTrials) {
          if (Object.is(selectedTrial, serverTrial.trial)) {
            return true;
          }
        }
      });
    console.log(this.selectedServerCommonCropsTrials);

    this.selectedServerCommonCropsTrials.map(serverCommon => this.trialService.getTrialsByCommonCropName(serverCommon.serverUrl, serverCommon.trial.commonCropName)
      .subscribe(trials => {
          trials.map(trial => this.trialCheckboxes.push({trial: trial, selected: false}));
          trials.map(trial => this.serverTrial.push({serverUrl: serverCommon.serverUrl, trial: trial}));

        }
      ));
  }


}
