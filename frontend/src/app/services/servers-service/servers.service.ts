import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Server} from './servers';
import {Injectable} from '@angular/core';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class ServersService {
  private serversUrl = 'http://localhost:3000/servers';

  constructor(private http: HttpClient) {
  }

  getAllServers(): Observable<Server[]> {
    return this.http.get<Server[]>(this.serversUrl);
  }

  createServer(server: Server) {
    return this.http.post<Server>(this.serversUrl + '/create', server);
  }

  public deleteUser(user) {
    return this.http.delete(this.serversUrl + '/' + user + '/delete');
  }
}
