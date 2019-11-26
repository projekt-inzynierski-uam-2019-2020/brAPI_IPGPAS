import { Component, OnInit } from '@angular/core';
import {Globals} from '../../globals';

@Component({
  selector: 'app-study',
  templateUrl: './study.component.html',
  styleUrls: ['./study.component.scss']
})
export class StudyComponent implements OnInit {
  checkboxes = [];
  globals: Globals
  isShowStudies = false;
  isShowCommon = false;
  isQuestion = true;

  constructor(globals: Globals) {
    this.globals = globals;
  }

  ngOnInit() {
  }

  function() {
    this.isShowStudies = true;
    this.isQuestion = false;
    for ( const study of this.globals.studyArray) {
      this.checkboxes.push({
        commonCropName: study.commonCropName,
        programName: study.programName,
        studyName: study.studyName,
        selected: false
      });
    }
  }

  filterByCommonCrops(){
    this.isShowCommon = true;
    this.isQuestion = false;
    for ( const study of this.globals.commonCropsArray) {
      this.checkboxes.push({
        commonCropName: study.commonCropName,
        programName: study.programName,
        studyName: study.studyName,
        selected: false
      });
    }
  }

}
