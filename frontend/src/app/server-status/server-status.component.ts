import {Component, OnInit} from '@angular/core';
import {ServersFetchingService} from '../services/servers-service/servers-fetching.service';
import {Server} from '../services/servers-service/servers';
import {Globals} from '../globals';
import {Router} from '@angular/router';

@Component({
  selector: 'app-server-status',
  templateUrl: './server-status.component.html',
  styleUrls: ['./server-status.component.scss']
})
export class ServerStatusComponent implements OnInit {
  globals: Globals;

  servers: Server[];
  serverService: ServersFetchingService;
  serverCheckboxes = [];

  isLoading = true;


  ngOnInit() {
    // this.serverService.getAllServersStatus()
    //   .subscribe(data => {
    //     this.servers = data;
    //     for (const server of this.servers) {
    //       this.serverCheckboxes.push({value: server.ipAddress, selected: false});
    //     }
    //     this.isLoading = false;
    //   });
  }

  constructor(serverService: ServersFetchingService, globals: Globals, private router: Router) {
    this.serverService = serverService;
    this.globals = globals;
  }


}
