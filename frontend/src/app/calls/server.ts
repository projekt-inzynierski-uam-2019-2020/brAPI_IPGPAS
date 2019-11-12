import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BrApiDetailPayloadResponse} from './BrApiDetailPayloadResponse';
import {CallsService} from './calls.service';

@Injectable()
export class Servers {

  serverName: string;
  lengthCalls: number;
  serversArray = ['https://test-server.brapi.org/brapi/v1/trials', 'https://yambase.org/brapi/v1/trials']


  private test_server_url = 'https://test-server.brapi.org/brapi/v1/trials';
  brApiDetailPayloadResponse: BrApiDetailPayloadResponse;

  constructor(private http: HttpClient, private  callService: CallsService) {
  }

  getSelectedCall() {
    for (let i = 0; i < this.serversArray.length; i++) {
      this.callService.getSelectedCall(this.serversArray[i])
        .subscribe(
          call => {
            this.brApiDetailPayloadResponse = call;
            for (let j = 0; j < this.getCallsLength(); j++) {
              console.log(this.brApiDetailPayloadResponse.result.data[j].trialName);
              console.log(this.brApiDetailPayloadResponse.result.data[j].programName);
            }
          }
        );
    }
  }



  getCallsLength() {
    this.lengthCalls = this.brApiDetailPayloadResponse.result.data.length;
    return this.lengthCalls;
  }

}
