import {Component, OnInit} from '@angular/core';
import {CallsService} from '../calls/calls.service';
import {Server} from '../services/servers-service/servers';
import {Servers} from '../calls/server';
import {ServerPageComponent} from '../server-page/server-page.component';
import {Globals} from '../globals';


@Component({
  selector: 'app-chart-page',
  templateUrl: './server-data.component.html',
  styleUrls: ['./server-data.component.scss'],

})
export class ServerDataComponent implements OnInit {
  checkboxes = [
  ];
   array;
   trialName;
  globals: Globals;
  isDisplay = false;


  constructor(private callsService: CallsService, private serverrs: Servers, private serverspage: ServerPageComponent, private global: Globals) {
  }

  ngOnInit() {
    this.globals = this.global;

  }

}
