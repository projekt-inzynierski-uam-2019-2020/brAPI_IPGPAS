import {Component, OnInit} from '@angular/core';
import {CallsService} from '../calls/calls.service';
import { Server} from '../calls/server';

@Component({
  selector: 'app-chart-page',
  templateUrl: './server-data.component.html',
  styleUrls: ['./server-data.component.scss'],

})
export class ServerDataComponent implements OnInit {
  constructor(private callsService: CallsService, private server: Server) {
  }

  ngOnInit() {
    this.server.serverName = 'https://test-server.brapi.org/brapi/v1/';
  }


}
