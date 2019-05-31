import {Component, OnInit} from '@angular/core';
import { Server} from '../calls/server';


@Component({
  selector: 'app-server-page',
  templateUrl: './server-page.component.html',
  styleUrls: ['./server-page.component.css']
})
export class ServerPageComponent implements OnInit {

  constructor(private server: Server) {

  }

  ngOnInit() {
  }

  setServerVariable(flag: boolean) {
    if(flag === true){
      this.server.serverName = 'https://test-server.brapi.org/brapi/v1/';
    } else {
      this.server.serverName = 'http://104.248.254.181:8080/brapi/v1/';
    }

  }


}
