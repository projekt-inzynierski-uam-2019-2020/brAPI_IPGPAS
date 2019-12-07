import {Component, OnChanges, OnDestroy, OnInit, SimpleChanges} from '@angular/core';
import {CallsService} from '../calls/calls.service';
import {Server} from '../services/servers-service/servers';
import {Servers} from '../calls/server';
import {ServerPageComponent} from '../server-page/server-page.component';
import {Globals} from '../globals';
import {Subject} from 'rxjs';
import {load} from '@angular/core/src/render3/instructions';
import {routing} from '../app-routing';


@Component({
  selector: 'app-chart-page',
  templateUrl: './server-data.component.html',
  styleUrls: ['./server-data.component.scss'],

})
export class ServerDataComponent implements OnInit {
  checkboxes = [];
  staticCheckboxes = [];
  studiesCheckboxes = [];
  filtersCheckboxes = [];
  isTable = false;
  acceptButtonTable = false;
  array;
  initialized = false;
  trialName;
  globals: Globals;
  isDisplay = {
    trials: true,
    studyFilters: false,
    study: false,

  };
  name = false;
  isShow = false;
  acceptButtonS = false;
  firstAcceptButton = true;
  isStudy = false;
  isLocation = false;
  isTrials = true;

  constructor(private callsService: CallsService, private serverrs: Servers, private serverspage: ServerPageComponent, private global: Globals) {
  }

  ngOnInit() {
    this.globals = this.global;
    this.name = true;
    console.log(this.globals.serverArray.length);
    for (let i = 0; i < 3; i++) {
      this.staticCheckboxes.push({study: 'Study', selecteed: false});
      this.staticCheckboxes.push({study: 'Location', selecteed: false});
      this.staticCheckboxes.push({study: 'Season', selecteed: true});
    }
  }


  showStudyFilters() {
    this.isTrials = false;
    this.isDisplay.trials = false;
    this.isDisplay.studyFilters = true;
    this.isDisplay.study = false;
    this.isShow = false;
    this.acceptButtonS = true;
    this.firstAcceptButton = false;
    console.log(this.globals.serverArray.length);
    const result = this.checkboxes.filter((ch) => {
      return ch.selected;
    })
      .map((ch) => {
        return ch.studies;
      });
    for (let i = 0; i < result.length; i++) {
      for (let j = 0; j < result[i].length; j++) {

        this.globals.studiesIdArray.push(result[i][j].studyDbId);
      }

    }
    console.log(this.globals.studiesIdArray);
    this.serverrs.getSelectedCallStudies();
  }

  showLocationFiletrs() {
    const result = this.checkboxes.filter((ch) => {
      return ch.selected;
    })
      .map((ch) => {
        return ch.studies;
      });
    for (let i = 0; i < result.length; i++) {
      for (let j = 0; j < result[i].length; j++) {

        this.globals.locationDbId.push(result[i][j].locationDbId);
      }

    }
    console.log(this.globals.locationDbId);
    this.serverrs.getSelectedCallLocation();
  }

  check() {
    console.log(this.globals.serverArray.length);
    for (let i = 0; i < this.globals.serverArray.length; i++) {
      this.checkboxes.push({value: 1, selected: false});
      if (this.globals.serverArray.length !== 0) {
        this.name = false;
      }
    }
  }

  filterButton() {
    console.log(this.globals.locationsArray);
    console.log(this.globals.serversArray);
    console.log(this.global.studiesArray[0].studyName);
    for (let i = 0; i < this.globals.locationDbId.length; i++) {
      this.filtersCheckboxes.push({
        studyName: this.globals.studiesArray[i].studyName,
        locationName: this.globals.studiesArray[i].locationName,
        startDate: this.globals.studiesArray[i].programName,
        locationId: this.globals.studiesArray[i].locationDbId,
        selected: false
      });
    }
  }

  trialsFunction() {
    for (let i = 0; i < this.globals.serverArray.length; i++) {
      this.checkboxes.push({
        trialName: this.globals.serverArray[i].trialName,
        commonCropName: this.globals.serverArray[i].commonCropName,
        programName: this.globals.serverArray[i].programName,
        studies: this.globals.serverArray[i].studyDbId,
        selected: false
      });
    }
    this.isShow = true;
  }

  acceptButtonTablee(){
    this.isTable = true;
    this.isStudy = false;
    this.isLocation = false;
    this.isDisplay.studyFilters = false;
  }

  acceptButtonSF() {
    this.isDisplay.studyFilters = false;
    this.acceptButtonS = false;
    this.acceptButtonTable = true;

    for (let i = 0; i < this.globals.locationDbId.length; i++) {
      this.filtersCheckboxes.push({
        studyName: this.globals.locationsArray[i].studyName,
        locationName: this.globals.locationsArray[i].locationName,
        startDate: this.globals.locationsArray[i].programName,
        locationId: this.globals.locationsArray[i].locationDbId,
        selected: false
      });
    }

    console.log(this.global.studiesArray);
    console.log(this.globals.studiesIdArray);

    for (let i = 0; i < this.globals.studiesIdArray.length; i++) {
      this.studiesCheckboxes.push({
        studyName: this.globals.studiesArray[i].studyName,
        studyType: this.globals.studiesArray[i].studyType,
        startDate: this.globals.studiesArray[i].startDate,
        endDate: this.globals.studiesArray[i].endDate,
        selected: false
      });
    }


    const result = this.staticCheckboxes.filter((ch) => {
      return ch.selected;
    })
      .map((ch) => {
        return ch.study;
      });

    for (let i = 0; i < result.length; i++) {
      console.log(result[i]);
    }

    if (result[0] === 'Study') {
      this.isStudy = true;
    } else {
      this.isLocation = true;
    }
  }
}
