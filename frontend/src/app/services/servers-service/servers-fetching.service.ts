import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Server} from './servers';
import {Injectable} from '@angular/core';
import {ServerStatus} from './serverStatus';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class ServersFetchingService {

  private serversUrl = 'https://teamprojectuam.tk/api';

  private token = localStorage.getItem('access_token');

  private headers_object = new HttpHeaders({
    'Content-Type': 'application/x-www-form-urlencoded',
    'x-access-token': this.token
  });


  private httpOptions = {
    headers: this.headers_object
  };


  constructor(private http: HttpClient) {
  }

  getAllServers(): Observable<Server[]> {
      return this.http.get<Server[]>(this.serversUrl + '/get_servers');
  }

  getAllStatusServers(): Observable<ServerStatus[]> {
    return this.http.get<ServerStatus[]>(this.serversUrl + '/status');
  }

  createServer(server: Server) {
    const payload = new HttpParams()
      .set('name', server.name)
      .set('ipAddress', server.ipAddress)
      .set('description', server.description);
      return this.http.post<Server>(this.serversUrl + '/servers/', payload, this.httpOptions);
  }

  updateServer(server: Server, serverId) {
    const payload = new HttpParams()
      .set('name', server.name)
      .set('ipAddress', server.ipAddress)
      .set('description', server.description);
    return this.http.put<Server>(this.serversUrl + '/servers/' + serverId, payload, this.httpOptions);
  }

  public deleteServer(server) {
    return this.http.delete(this.serversUrl + '/servers/' + server, this.httpOptions);
  }
}
