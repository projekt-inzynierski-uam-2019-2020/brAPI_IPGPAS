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
  studiesArray = [];

  constructor(private http: HttpClient, private  callService: CallsService, private globals: Globals) {
  }



  getSelectedCallStudies() {
    this.global = this.globals;
    console.log(this.globals.studiesIdArray);
    for (let i = 0; i < this.globals.studiesIdArray.length; i++) {
      this.testArray[i] = this.globals.serversArray2 + 'studies' + '?studyDbId=' + this.globals.studiesIdArray[i];
      console.log(this.testArray[i]);
    }
    for (let i = 0; i < this.globals.studiesIdArray.length; i++) {
      console.log(this.testArray[i]);
      this.callService.getSelectedCall(this.testArray[i])
        .subscribe(
          call => {
            this.brApiDetailPayloadResponse2 = call;
            console.log(this.brApiDetailPayloadResponse2);
            this.putStudiesArray(this.brApiDetailPayloadResponse2);
          }
        );
    }
    console.log(this.brApiDetailPayloadResponse2);
  }

  getSelectedCallLocation() {
    this.global = this.globals;
    console.log(this.globals.locationDbId);
    for (let i = 0; i < this.globals.locationDbId.length; i++) {
      this.testLocationArray[i] = this.globals.serversArray2 + 'studies' + '?locationDbId=' + this.globals.locationDbId[i];
      console.log(this.testArray[i]);
    }
    for (let i = 0; i < this.globals.locationDbId.length; i++) {
      console.log(this.testLocationArray[i]);
      this.callService.getSelectedCall(this.testLocationArray[i])
        .subscribe(
          call => {
            this.brApiDetailPayloadResponse3 = call;
            console.log(this.brApiDetailPayloadResponse3);
            this.putLocationArray(this.brApiDetailPayloadResponse3);
          }
        );
    }
    console.log(this.brApiDetailPayloadResponse2);
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


    console.log(this.globals.studiesArray);

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


    console.log(this.globals.studiesArray);

  }

  getArray() {
    // console.log(this.trialName[1]);
    for (let i = 0; i < this.globals.trialName.length; i++) {
      console.log(this.globals.trialName[i]);
    }
  }


  getServerList(listServers) {
    this.serversArrayT = listServers;
    console.log(this.serversArrayT);
  }

  pushLength() {
    return this.lengthCalls;
  }


  getCallsLength() {
    this.lengthCalls = this.brApiDetailPayloadResponse.result.data.length;
    return this.lengthCalls;
  }


}
