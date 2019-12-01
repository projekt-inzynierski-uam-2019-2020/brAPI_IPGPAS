import {Component, OnInit} from '@angular/core';
import {Globals} from '../../globals';
import {Study} from '../../call-models/study';
import {StudyCheckbox} from './studyCheckbox';
import {StudyService} from '../../call-services/study/study-service';
import {ServerStudy} from './serverStudy';
import {st} from '@angular/core/src/render3';
import {LocationStudy} from './locationStudy';
import {ServerTrial} from '../trial/serverTrial';

@Component({
  selector: 'app-study',
  templateUrl: './study.component.html',
  styleUrls: ['./study.component.scss']
})
export class StudyComponent implements OnInit {
  globals: Globals;
  studyCheckboxes: StudyCheckbox[] = [];
  studyLocationCheckboxes: StudyCheckbox[] = [];
  serverStudy: ServerStudy[] = [];
  locationStudy: LocationStudy[] = [];

  studyService: StudyService;
  selectedLocationStudy: LocationStudy[] = [];

  isShowAllStudies = true;
  isLocationFilterShow = false;



  constructor(globals: Globals, studyService: StudyService) {
    this.globals = globals;
    this.studyService = studyService;
  }

  ngOnInit() {
    this.fetchStudiesFromSelectedServerTrials();
  }

  fetchStudiesFromSelectedServerTrials() {
    this.globals.selectedServerTrials.map(selectedTrial => this.studyService.getStudyByTrialDbId(selectedTrial.serverUrl, selectedTrial.trial.trialDbId)
      .subscribe(fetchedStudies => {
        this.setStudyCheckboxes(fetchedStudies);
        this.setServerStudies(selectedTrial.serverUrl, fetchedStudies);

      }));
  }

  setStudyCheckboxes(studies: Study[]) {
    studies.map(study => this.studyCheckboxes.push({study: study, selected: false}));
  }

  setServerStudies(serverUrl: string, studies: Study[]) {
    studies.map(study => this.serverStudy.push({serverUrl: serverUrl, study: study}));

    for (const study of this.serverStudy) {
      if (!this.locationStudy.some((item) => item.study.locationName === study.study.locationName)) {
        if (study.study.locationName) {
          this.locationStudy.push({locationName: study.study.locationName, study: study.study, serverUrl: study.serverUrl});
          this.studyLocationCheckboxes.push({study: study.study, selected: false});
        }
      }
    }
  }

  setSelectedServerStudies() {
    const selectedStudies = this.studyCheckboxes.filter(studyCheckbox => studyCheckbox.selected).map(studyCheckbox => studyCheckbox.study);
    this.globals.selectedServerStudies = this.serverStudy
      .filter(serverStudy => {
        for (const selectedStudy of selectedStudies) {
          if (Object.is(serverStudy.study, selectedStudy)) {
            return true;
          }
        }
      });
  }

  // functions for filter for Location

  showLocations() {
    this.studyCheckboxes = [];
    this.isShowAllStudies = false;
    this.isLocationFilterShow = true;

  }

  setLocationStudy() {
    this.isShowAllStudies = true;
    this.isLocationFilterShow = false;
    this.studyCheckboxes = [];
    const selectedLocation = this.studyLocationCheckboxes.filter(studyLocationCheckboxes => studyLocationCheckboxes.selected).map(studyLocationCheckboxes => studyLocationCheckboxes.study);

    this.selectedLocationStudy = this.locationStudy
      .filter(location => {
        for (const locationStudyy of selectedLocation) {
          if (Object.is(location.study, locationStudyy)) {
            return true;
          }
        }
      });

    for (const study of this.serverStudy) {
      if (this.selectedLocationStudy.some((item) => item.locationName === study.study.locationName)) {
        if (!this.studyCheckboxes.some((item) => item.study === study.study)) {
          this.studyCheckboxes.push({study: study.study, selected: false});
        }
      }
    }
  }

}
