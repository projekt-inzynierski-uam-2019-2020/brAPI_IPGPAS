import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BrAPIDetailResponse} from '../call-models/response/brAPIDetailResponse';
import {delay, map} from 'rxjs/operators';
import {Observable} from 'rxjs';
import {DetailResult} from '../call-models/response/detailResult';
import {IPost} from './Ipost';


@Injectable({
  providedIn: 'root'
})
export class FetchBrapiResultService {

  private url = 'http://jsonplaceholder.typicode.com/posts';

  constructor(private http: HttpClient) {
  }

  public getBrAPIDetailResult(brApiUrl: string) {
    return this.http.get<BrAPIDetailResponse>(brApiUrl).pipe(map((response) => response.result));
  }

  public getBrApiDetailTest(url: string) {
    return this.http.get<BrAPIDetailResponse>(url);

  }

  public getAllData(): Observable<IPost[]> {
    return this.http.get<IPost[]>(this.url).pipe(delay(1000));
  }
}