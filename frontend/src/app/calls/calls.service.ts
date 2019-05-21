import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {BrApiDetailPayloadResponse} from './BrApiDetailPayloadResponse';



@Injectable({
  providedIn: 'root'  // <- ADD THIS
})
export class CallsService {

  constructor(private http: HttpClient) {
  }

  private callsUrl = 'https://test-server.brapi.org/brapi/v1/calls';
  private url = 'https://test-server.brapi.org/brapi/v1';


  public getCalls() {
    return this.http.get<BrApiDetailPayloadResponse>(this.callsUrl);
  }

  public getSelectedCall(selectedItem: string) {
    return this.http.get<BrApiDetailPayloadResponse>(this.url + '/' + selectedItem);
  }


}

