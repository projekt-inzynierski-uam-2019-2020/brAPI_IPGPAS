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
  studiesIdArray = [1001, 1002, 1003];
  studiesRequests = [];
  testArray = [];
  brApiDetailPayloadResponse: BrApiDetailPayloadResponse;
  brApiDetailPayloadResponse2: BrApiDetailPayloadResponse;
  global: Globals;
  studiesArray = [];

  constructor(private http: HttpClient, private  callService: CallsService, private globals: Globals) {
  }

  getSelectedCall() {
    this.global = this.globals;

    this.serversArrayT = this.global.serversArray;
    console.log('check 2' + this.serversArrayT);
    for (let i = 0; i < this.global.serversArray.length; i++) {
      this.serversArrayT[i] = this.serversArrayT[i] + 'trials';
      console.log(this.serversArrayT);
    }
    for (let i = 0; i < this.serversArrayT.length; i++) {
      this.callService.getSelectedCall(this.serversArrayT[i])
        .subscribe(
          call => {
            this.brApiDetailPayloadResponse = call;
            console.log('check' + this.brApiDetailPayloadResponse)
            this.putArray(this.brApiDetailPayloadResponse);
            this.getArray();
          }
        );
    }
  }

  getSelectedCallStudies() {
    this.global = this.globals;
    for (let i = 0; i < this.studiesIdArray.length; i++) {
      this.testArray[i] = 'https://test-server.brapi.org/brapi/v1/' + 'studies' + '?studyDbId=' + this.studiesIdArray[i];
      console.log(this.testArray[i]);
    }
    for (let i = 0; i < this.studiesIdArray.length; i++) {
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
    console.log(this.studiesArray);
  }

  getSelectedCallLocation(){

  }

  putStudiesArray(brApiDetailPayloadResponse2) {
    for (let i = 0; i < this.studiesIdArray.length; i++) {
      for (let j = 0; j < this.brApiDetailPayloadResponse2.result.data.length; j++) {
        this.studiesArray.push(
          {
            studyName: this.brApiDetailPayloadResponse2.result.data[j].studyName,
            studyType: this.brApiDetailPayloadResponse2.result.data[j].studyType,
            startDate: this.brApiDetailPayloadResponse2.result.data[j].startDate,
            endDate: this.brApiDetailPayloadResponse2.result.data[j].endDate
          });
      }
    }

  }

  putArray(brApiDetailPayloadResponse) {
    for (let i = 0; i < this.serversArrayT.length; i++) {
      for (let j = 0; j < this.getCallsLength(); j++) {
          this.globals.serverArray.push({
            trialName: this.brApiDetailPayloadResponse.result.data[j].trialName,
            commonCropName: this.brApiDetailPayloadResponse.result.data[j].commonCropName,
            programName: this.brApiDetailPayloadResponse.result.data[j].programName,
            studyDbId: this.brApiDetailPayloadResponse.result.data[j].studies
          });
      }
    }
    console.log(this.globals.serverArray);
    return this.globals.trialName;
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
