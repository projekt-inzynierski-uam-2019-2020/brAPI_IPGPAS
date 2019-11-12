import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BrApiDetailPayloadResponse} from './BrApiDetailPayloadResponse';
import {CallsService} from './calls.service';

@Injectable()
export class Servers {

  serverName: string;
  lengthCalls: number;
  serversArray = ['https://test-server.brapi.org/brapi/v1/trials', '']


  private test_server_url = 'https://test-server.brapi.org/brapi/v1/trials';
  brApiDetailPayloadResponse: BrApiDetailPayloadResponse;

  constructor(private http: HttpClient, private  callService: CallsService) { }

  getSelectedCall() {
    return this.callService.getSelectedCall(this.test_server_url)
      .subscribe(
        call => {
          this.brApiDetailPayloadResponse = call;
          for (let i = 0; i < this.getCallsLength(); i++) {
            console.log(this.brApiDetailPayloadResponse.result.data[i].trialName);
            console.log(this.brApiDetailPayloadResponse.result.data[i].commonCropName);
            console.log(this.brApiDetailPayloadResponse.result.data[i].programName);
          }
        }
      );
  }

  getCallsLength() {
    this.lengthCalls = this.brApiDetailPayloadResponse.result.data.length;
    return this.lengthCalls;
  }

}
