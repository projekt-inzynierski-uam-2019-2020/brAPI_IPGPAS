import {Component, OnInit} from '@angular/core';

import {FormArray, FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {style} from '@angular/animations';
import {ServersFetchingService} from '../services/servers-service/servers-fetching.service';
import {Server} from '../services/servers-service/servers';
import { Servers } from '../calls/server';
import {BrApiDetailPayloadResponse} from '../calls/BrApiDetailPayloadResponse';
import {HttpClient} from '@angular/common/http';
import {CallsService} from '../calls/calls.service';
import {ServerDataComponent} from '../server-data-page/server-data.component';
import {Globals} from '../globals';
import {routing} from '../app-routing';
import {Router} from '@angular/router';

@Component({
  selector: 'app-server-page',
  templateUrl: './server-page.component.html',
  styleUrls: ['./server-page.component.scss']
})
export class ServerPageComponent implements OnInit {
  isActive = false;
  isDisplay = false;
  servers: Server[];
 // server: Server = new Server();
  serverName: string;
  lengthCalls: number;
  serversArray = ['https://test-server.brapi.org/brapi/v1/trials', 'https://yambase.org/brapi/v1/trials']
  serversArrayT = [];
  trialName = [];
  programName = [];
  brApiDetailPayloadResponse: BrApiDetailPayloadResponse;
  checkName = [];
  globals: Globals;
  isLocation = false;
  isLoading = true;

  checkboxes = [
  ];

  ngOnInit(): void {
    this.globals = this.global;
    this.serverService.getAllServers()
      .subscribe(data => {
        this.servers = data;
        for (const server of this.servers) {
          this.checkboxes.push({value: server.ipAddress, selected: false});
        }
        this.isLoading = false;

      });

  }


  constructor(private formBuilder: FormBuilder, private serverService: ServersFetchingService, private http: HttpClient, private  callService: CallsService, private serverss: Servers, public global: Globals, private router: Router) {
  }


  public getSelected() {
    this.globals.selectedServers = [];
    const result = this.checkboxes.filter((checkbox) => checkbox.selected)
      .map((checkbox) => {
        return checkbox.value;
      });
    this.globals.selectedServer = result;
    for (let i = 0; i < result.length; i++) {

      this.globals.selectedServers.push(result[i]);
      this.globals.serversArray2.push(result[i]);
    }
    this.getServerList(result);
    this.serverss.getServerList(result);
    this.serverss.getSelectedCallStudies();
    this.isDisplay = true;


    this.globals.selectedServers.length > 0 ? this.router.navigate(['/servers/trial']) : alert('You have to select server first.');

  }


  getCallsLength() {
    this.lengthCalls = this.brApiDetailPayloadResponse.result.data.length;
    return this.lengthCalls;
  }


  getServerList(listServers) {
    this.serversArrayT = listServers;
  }
}
