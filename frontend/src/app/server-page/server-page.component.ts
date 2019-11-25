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


  checkboxes = [
  ];

  ngOnInit(): void {
    this.globals = this.global;
    console.log(this.globals.role);
    this.serverService.getAllServers()
      .subscribe(data => {
        this.servers = data;
        for (const server of this.servers) {
          this.checkboxes.push({value: server.ipAddress, selected: false});
        }

      });

  }


  constructor(private formBuilder: FormBuilder, private serverService: ServersFetchingService, private http: HttpClient, private  callService: CallsService, private serverss: Servers, public global: Globals) {
  }


  public getSelected() {
    const result = this.checkboxes.filter((checkbox) => checkbox.selected)
      .map((checkbox) => {
        return checkbox.value;
      });
    for (let i = 0; i < result.length; i++) {
      this.globals.selectedServers.push(result[i]);
      this.globals.serversArray2.push(result[i]);
    }
    console.log(this.globals.selectedServers);
    this.getServerList(result);
    this.serverss.getServerList(result);
    this.serverss.getSelectedCallStudies();
    this.isDisplay = true;

    console.log(this.globals.trialName[1]);

  }
  public toggleStyle(id) {
    const card = document.getElementById(`card${ id }`);
    const header = document.getElementById(`card-header${ id }`);
    const body = document.getElementById(`card-body${ id }`);

    if (this.isActive) {
      card.style.boxShadow = '0px 10px 20px rgba(0, 0, 0, 0.7)';
      header.style.backgroundColor = '#B6844A';
      body.style.backgroundColor = '#F5E0B7';
      this.checkboxes[id].selected = true;
      console.log('active' + id);
      this.isActive = !this.isActive;
    } else {
      card.style.boxShadow = 'none';
      header.style.backgroundColor = '#858585';
      body.style.backgroundColor = '#FFFFFF';
      this.checkboxes[id].selected = false;
      console.log('non active' + id);
      this.isActive = !this.isActive;
    }
  }


  putArray(brApiDetailPayloadResponse) {
    console.log(brApiDetailPayloadResponse);
    for(let i = 0; i < this.serversArrayT.length; i++) {
      for (let j = 0; j < this.getCallsLength(); j++) {
        this.trialName.push(this.brApiDetailPayloadResponse.result.data[j].trialName);
        this.programName.push(this.brApiDetailPayloadResponse.result.data[j].programName);
      }
    }
    return this.trialName;
  }

  getArray() {
    // console.log(this.trialName[1]);
    for(let i = 0; i < this.trialName.length; i++){
      console.log(this.trialName[i]);
    }
  }

  getCallsLength() {
    this.lengthCalls = this.brApiDetailPayloadResponse.result.data.length;
    return this.lengthCalls;
  }


  getServerList(listServers) {
    this.serversArrayT = listServers;
    console.log(this.serversArrayT);
  }
}
