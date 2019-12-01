import {Component, OnInit} from '@angular/core';
import {Globals} from '../../globals';
import {Study} from '../../call-models/study';
import {StudyCheckbox} from './studyCheckbox';
import {StudyService} from '../../call-services/study/study-service';
import {ServerStudy} from './serverStudy';
import {st} from '@angular/core/src/render3';

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
  filteredStudiesByLocation: ServerStudy[] = [];

  studyService: StudyService;
  locationFilterSelected = false;
  showAllStudiesWithoutFilter = true;



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
        console.log(fetchedStudies);

      }));
  }

  setStudyCheckboxes(studies: Study[]) {
    studies.map(study => this.studyCheckboxes.push({study: study, selected: false}));
  }

  setServerStudies(serverUrl: string, studies: Study[]) {
    studies.map(study => this.serverStudy.push({serverUrl: serverUrl, study: study}));
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

  // functions for filter by LocationDbId

  setFilterByLocation() {
    this.locationFilterSelected = true;
    this.showAllStudiesWithoutFilter = false;
     this.fetchStudiesByStudyDbIdFromSelectedServerTrials();
  }

  fetchStudiesByStudyDbIdFromSelectedServerTrials() {
     this.globals.selectedServerTrials.map(selectedTrial => this.studyService.getStudyByStudyDbId(selectedTrial.serverUrl, selectedTrial.trial.studyDbId)
      .subscribe(fetchedStudies => {
        // this.setStudyLocationCheckboxes(fetchedStudies);
       this.setServerStudiesLocation(selectedTrial.serverUrl, fetchedStudies);
      }));
  }

 /* setStudyLocationCheckboxes(studies: Study[]) {
    for (const study of studies) {
      if (!this.filteredStudiesByLocation.some((item) => item.study.locationName === study.locationName)) {
        studies.map(locationStudy => this.studyLocationCheckboxes.push({study: locationStudy, selected: false}));

      }
    }
    console.log(this.studyLocationCheckboxes);
  }*/

  setServerStudiesLocation(serverUrl: string, studies: Study[]) {
    for (const study of studies) {
      if (!this.filteredStudiesByLocation.some((item) => item.study.locationName === study.locationName)) {
        this.filteredStudiesByLocation.push({serverUrl: serverUrl, study: study});
      }
    }
    console.log(this.filteredStudiesByLocation);
  }



}
