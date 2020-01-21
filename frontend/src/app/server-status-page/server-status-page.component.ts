import { Component, OnInit } from '@angular/core';
import {Globals} from '../globals';
import {Server} from '../services/servers-service/servers';
import {ServersFetchingService} from '../services/servers-service/servers-fetching.service';
import {Router} from '@angular/router';
import {ServerStatus} from '../services/servers-service/serverStatus';

@Component({
  selector: 'app-server-status-page',
  templateUrl: './server-status-page.component.html',
  styleUrls: ['./server-status-page.component.scss']
})
export class ServerStatusPageComponent implements OnInit {

  servers: ServerStatus[];
  serverService: ServersFetchingService;

  isLoading = true;


  ngOnInit() {
    this.serverService.getAllStatusServers()
      .subscribe(data => {
        this.servers = data;
        if (data.length === 0){
          this.isLoading = false;
        } else {
            this.isLoading = false;
          }
      });
  }

  constructor(serverService: ServersFetchingService) {
    this.serverService = serverService;
  }
}
