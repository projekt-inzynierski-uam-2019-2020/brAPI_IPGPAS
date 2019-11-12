import {Component, OnInit} from '@angular/core';

import {FormArray, FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {style} from '@angular/animations';
import {ServersService} from '../services/servers-service/servers.service';
import {Server} from '../services/servers-service/servers';
import { Servers } from '../calls/server';

@Component({
  selector: 'app-server-page',
  templateUrl: './server-page.component.html',
  styleUrls: ['./server-page.component.scss']
})
export class ServerPageComponent implements OnInit {
  isActive = false;
  servers: Server[];
  server: Server = new Server();

  checkboxes = [
  ];

  ngOnInit(): void {
    this.serverrs.getSelectedCall();
    this.serverService.getAllServers()
      .subscribe(data => {
        this.servers = data;
        for (let i = 0; i < this.servers.length; i++) {
          this.checkboxes.push({value: this.servers[i].ipAddress, selected: false});
        }
      });
  }



  constructor(private formBuilder: FormBuilder, private serverService: ServersService, private serverrs: Servers) {
  }

  public getSelected() {
    const result = this.checkboxes.filter((ch) => {
      return ch.selected;
    })
      .map((ch) => {
        return ch.value;
      });
    console.log(result);
  }
  public toggleStyle(id) {
    const card = document.getElementById(`card${ id }`);
    const header = document.getElementById(`card-header${ id }`);
    const body = document.getElementById(`card-body${ id }`);

  /*  if (!isActive) {
      isActive = true;
    } else {
        isActive = false;
      }*/

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
}
