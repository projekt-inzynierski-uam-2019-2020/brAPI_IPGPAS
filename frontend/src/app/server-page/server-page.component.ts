import {Component, OnInit} from '@angular/core';
import {ServersFetchingService} from '../services/servers-service/servers-fetching.service';
import {Server} from '../services/servers-service/servers';
import {Globals} from '../globals';
import {Router} from '@angular/router';

@Component({
  selector: 'app-server-page',
  templateUrl: './server-page.component.html',
  styleUrls: ['./server-page.component.scss']
})
export class ServerPageComponent implements OnInit {
  globals: Globals;

  servers: Server[];
  serverService: ServersFetchingService;
  serverCheckboxes = [];

  isLoading = true;


  ngOnInit() {
    this.serverService.getAllServers()
      .subscribe(data => {
        this.servers = data;
        for (const server of this.servers) {
          this.serverCheckboxes.push({value: server.ipAddress, selected: false});
        }
        this.isLoading = false;
      });
  }

  constructor(serverService: ServersFetchingService, globals: Globals, private router: Router) {
    this.serverService = serverService;
    this.globals = globals;
  }


  public setSelectedCheckboxes() {
    this.globals.selectedServers = [];

    const result = this.serverCheckboxes.filter(checkbox => checkbox.selected)
      .map(checkbox => checkbox.value);

    for (const value of result) {
      this.globals.selectedServers.push(value);
    }

    this.globals.selectedServers.length > 0 ? this.router.navigate(['/servers/trial']) : alert('You have to select server first.');
  }

}
