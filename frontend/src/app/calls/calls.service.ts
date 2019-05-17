import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Calls} from './calls';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'  // <- ADD THIS
})
export class CallsService {

  constructor(private http: HttpClient) {
  }

  private callsUrl = 'https://test-server.brapi.org/brapi/v1/calls';

  public getCalls() {
    return this.http.get<Calls>(this.callsUrl);
  }
}

