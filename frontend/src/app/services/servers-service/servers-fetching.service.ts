import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Server} from './servers';
import {Status} from './status';
import {Injectable} from '@angular/core';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class ServersFetchingService {
  private serversUrl = 'https://teamprojectuam.tk/api/servers';
  private serverStatus = 'https://teamprojectuam.tk/api/status';

  constructor(private http: HttpClient) {
  }

  getAllServers(): Observable<Server[]> {
    return this.http.get<Server[]>(this.serversUrl);
  }

  getAllServersStatus(): Observable<Status[]> {
    return this.http.get<Status[]>(this.serverStatus);
  }

  createServer(server: Server) {
    return this.http.post<Server>(this.serversUrl + '/create', server);
  }

  updateServer(server: Server, serverId) {
    return this.http.put<Server>(this.serversUrl + '/' + serverId + '/update', server, httpOptions);
  }

  public deleteServer(server) {
    return this.http.delete(this.serversUrl + '/' + server + '/delete');
  }
}
