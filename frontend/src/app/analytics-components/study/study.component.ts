import {Component, OnInit} from '@angular/core';
import {Globals} from '../../globals';
import {Study} from '../../call-models/study';
import {StudyCheckbox} from './studyCheckbox';
import {StudyService} from '../../call-services/study/study-service';
import {ServerStudy} from './serverStudy';

@Component({
  selector: 'app-study',
  templateUrl: './study.component.html',
  styleUrls: ['./study.component.scss']
})
export class StudyComponent implements OnInit {
  globals: Globals;
  studyCheckboxes: StudyCheckbox[] = [];
  serverStudy: ServerStudy[] = [];

  studyService: StudyService;

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
    console.log(this.globals.selectedServerStudies);
  }

}
