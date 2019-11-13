import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BrApiDetailPayloadResponse} from './BrApiDetailPayloadResponse';
import {CallsService} from './calls.service';
import {ServerDataComponent} from '../server-data-page/server-data.component';


@Injectable()
export class Servers {

  serverName: string;
  lengthCalls: number;
  serversArray = ['https://test-server.brapi.org/brapi/v1/trials', 'https://yambase.org/brapi/v1/trials']
  serversArrayT = [];
  trialName = [];
  programName = [];
  brApiDetailPayloadResponse: BrApiDetailPayloadResponse;

  constructor(private http: HttpClient, private  callService: CallsService) {
  }

  getSelectedCall() {
    for (let i = 0; i < this.serversArrayT.length; i++) {
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
    for (let j = 0; j < this.getCallsLength(); j++) {
      this.trialName.push(this.brApiDetailPayloadResponse.result.data[j].trialName);
    }
    return this.trialName;
  }

  getArray() {
    // console.log(this.trialName[1]);
   for(let i = 0; i < this.trialName.length ;i++){
     console.log(this.trialName[i]);
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
