import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {BrAPIDetailResponse} from '../call-models/response/brAPIDetailResponse';
import {catchError, delay, map, timeout} from 'rxjs/operators';
import {Observable, of, throwError} from 'rxjs';
import {IPost} from './Ipost';

@Injectable({
  providedIn: 'root'
})
export class FetchBrapiResultService {

  private url = 'http://jsonplaceholder.typicode.com/posts';

   public getTableVariables(brApiUrl: string) {
     return this.http.get(brApiUrl,  {responseType: 'text'});
   }

  constructor(private http: HttpClient) {
  }

  public getBrAPIDetailResult(brApiUrl: string)  {
     return this.http.get<BrAPIDetailResponse>(brApiUrl)
       .pipe(timeout(200000), map((response) => response.result),  catchError( error => {
         return of(null);
       }));

  }

  public getBrApiDetailTest(url: string) {
    return this.http.get<BrAPIDetailResponse>(url);

  }

  public getAllData(): Observable<IPost[]> {
    return this.http.get<IPost[]>(this.url).pipe(delay(1000));
  }
}
