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
  serversArray = ['https://test-server.brapi.org/brapi/v1/trials', 'https://yambase.org/brapi/v1/trials'];
  serversArrayT = [];
  brApiDetailPayloadResponse: BrApiDetailPayloadResponse;
  global: Globals;

  constructor(private http: HttpClient, private  callService: CallsService, private globals: Globals) {
  }

  getSelectedCall() {
    this.global = this.globals;

    this.serversArrayT = this.global.serversArray;
    console.log(this.serversArrayT.length);
    for (let i = 0; i < this.global.serversArray.length; i++) {
      this.serversArrayT[i] = this.serversArrayT[i] + 'trials';
      console.log(this.serversArrayT);
    }
    for (let i = 0; i < this.serversArrayT.length; i++) {
      this.callService.getSelectedCall(this.serversArrayT[i])
        .subscribe(
          call => {
            this.brApiDetailPayloadResponse = call;
            this.putArray(this.brApiDetailPayloadResponse);
            this.getArray();
          }
        );
    }
  }

  putArray(brApiDetailPayloadResponse) {
    console.log(brApiDetailPayloadResponse);
    for (let i = 0; i < this.serversArrayT.length; i++) {
      for (let j = 0; j < this.getCallsLength(); j++) {
        for (let k = 0; k < this.brApiDetailPayloadResponse.result.data[j].studies.length; k++) {
          this.globals.serverArray.push({
            trialName: this.brApiDetailPayloadResponse.result.data[j].trialName,
            commonCropName: this.brApiDetailPayloadResponse.result.data[j].commonCropName,
            programName: this.brApiDetailPayloadResponse.result.data[j].programName,
            studyDbId: this.brApiDetailPayloadResponse.result.data[j].studies[k].studyDbId
          });
        }
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
