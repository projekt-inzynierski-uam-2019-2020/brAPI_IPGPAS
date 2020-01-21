import {Component, OnInit} from '@angular/core';
import {ServersFetchingService} from '../services/servers-service/servers-fetching.service';
import {Status} from '../services/servers-service/status';
import {Globals} from '../globals';
import {Router} from '@angular/router';

@Component({
  selector: 'app-server-status',
  templateUrl: './server-status.component.html',
  styleUrls: ['./server-status.component.scss']
})
export class ServerStatusComponent implements OnInit {
  globals: Globals;

  status: Status[];
  serverService: ServersFetchingService;
  serverCheckboxes = [];

  isLoading = true;


  ngOnInit() {
    this.serverService.getAllServersStatus()
      .subscribe(data => {
        this.status = data;
        for (const server of this.status) {
          this.serverCheckboxes.push({value: server.ipAddress, selected: false});
        }
        this.isLoading = false;
      });
  }

  constructor(serverService: ServersFetchingService, globals: Globals, private router: Router) {
    this.serverService = serverService;
    this.globals = globals;
  }


}
