import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {BrApiDetailPayloadResponse} from '../BrApiDetailPayloadResponse';



@Injectable({
  providedIn: 'root'  // <- ADD THIS
})
export class CommoncropnamesService {

  constructor(private http: HttpClient) {
  }

  private cropsUrl = 'https://test-server.brapi.org/brapi/v1/commoncropnames';

  public getCrops() {
    return this.http.get<BrApiDetailPayloadResponse>(this.cropsUrl);
  }


}
