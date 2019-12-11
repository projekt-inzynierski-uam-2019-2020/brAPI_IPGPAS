import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BrApiDetailPayloadResponse} from './BrApiDetailPayloadResponse';
import {CallsService} from './calls.service';
import {ServerDataComponent} from '../server-data-page/server-data.component';
import {Globals} from '../globals';


@Injectable()
export class Servers {

  serverName: string;
  lengthCalls: number;
  serversArray = ['https://test-server.brapi.org/brapi/v1/'];
  serversArrayT = [];
  studiesRequests = [];
  testArray = [];
  testLocationArray = [];
  brApiDetailPayloadResponse: BrApiDetailPayloadResponse;
  brApiDetailPayloadResponse2: BrApiDetailPayloadResponse;
  brApiDetailPayloadResponse3: BrApiDetailPayloadResponse;
  global: Globals;

  constructor(private http: HttpClient, private  callService: CallsService, private globals: Globals) {
  }



  getSelectedCallStudies() {
    this.global = this.globals;
    for (let i = 0; i < this.globals.studiesIdArray.length; i++) {
      this.testArray[i] = this.globals.serversArray2 + 'studies' + '?studyDbId=' + this.globals.studiesIdArray[i];
    }
    for (let i = 0; i < this.globals.studiesIdArray.length; i++) {
      this.callService.getSelectedCall(this.testArray[i])
        .subscribe(
          call => {
            this.brApiDetailPayloadResponse2 = call;
            this.putStudiesArray(this.brApiDetailPayloadResponse2);
          }
        );
    }
  }

  getSelectedCallLocation() {
    this.global = this.globals;
    for (let i = 0; i < this.globals.locationDbId.length; i++) {
      this.testLocationArray[i] = this.globals.serversArray2 + 'studies' + '?locationDbId=' + this.globals.locationDbId[i];
    }
    for (let i = 0; i < this.globals.locationDbId.length; i++) {
      this.callService.getSelectedCall(this.testLocationArray[i])
        .subscribe(
          call => {
            this.brApiDetailPayloadResponse3 = call;
            this.putLocationArray(this.brApiDetailPayloadResponse3);
          }
        );
    }
  }


  putLocationArray(brApiDetailPayloadResponse2) {

    for (let j = 0; j < this.brApiDetailPayloadResponse2.result.data.length; j++) {
      this.globals.locationsArray.push(
        {
          locationName: this.brApiDetailPayloadResponse2.result.data[j].locationName,
          studyName: this.brApiDetailPayloadResponse2.result.data[j].studyName,
          programName: this.brApiDetailPayloadResponse2.result.data[j].programName,
        });
    }



  }

  putStudiesArray(brApiDetailPayloadResponse2) {

      for (let j = 0; j < this.brApiDetailPayloadResponse2.result.data.length; j++) {
        this.globals.studiesArray.push(
          {
            studyName: this.brApiDetailPayloadResponse2.result.data[j].studyName,
            studyType: this.brApiDetailPayloadResponse2.result.data[j].studyType,
            startDate: this.brApiDetailPayloadResponse2.result.data[j].startDate,
            endDate: this.brApiDetailPayloadResponse2.result.data[j].endDate
          });
      }



  }

  getArray() {
    // console.log(this.trialName[1]);
    for (let i = 0; i < this.globals.trialName.length; i++) {
      console.log(this.globals.trialName[i]);
    }
  }


  getServerList(listServers) {
    this.serversArrayT = listServers;
  }

  pushLength() {
    return this.lengthCalls;
  }


  getCallsLength() {
    this.lengthCalls = this.brApiDetailPayloadResponse.result.data.length;
    return this.lengthCalls;
  }


}
