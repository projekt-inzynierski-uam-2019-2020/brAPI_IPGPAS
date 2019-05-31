import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {BrApiDetailPayloadResponse} from './BrApiDetailPayloadResponse';



@Injectable({
  providedIn: 'root'  // <- ADD THIS
})
export class CallsService {

  constructor(private http: HttpClient) {
  }


  public getSelectedCall(selectedItem: string) {
    return this.http.get<BrApiDetailPayloadResponse>(selectedItem);
  }


}

