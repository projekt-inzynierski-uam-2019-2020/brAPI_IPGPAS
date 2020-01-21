import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import {UserToken} from './userToken';

@Injectable()
export class AuthService {
  constructor(private http: HttpClient) { }



  login(email: string, password: string): Observable<boolean> {
    const payload = new HttpParams()
      .set('email', email)
      .set('password', password);
    return this.http.post<{data: UserToken}>('http://localhost:3000/users/authenticate', payload)
      .pipe(
        map(result => {
          localStorage.setItem('access_token', result.data.token);
          return true;
        })
      );
  }

  logout() {
    localStorage.removeItem('access_token');
  }

  public get loggedIn(): boolean {
    return (localStorage.getItem('access_token') !== null);
  }
}
