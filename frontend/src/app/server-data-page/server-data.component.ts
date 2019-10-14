import {Component, OnInit} from '@angular/core';
import {CallsService} from '../calls/calls.service';

@Component({
  selector: 'app-chart-page',
  templateUrl: './server-data.component.html',
  styleUrls: ['./server-data.component.scss'],

})
export class ServerDataComponent implements OnInit {
  constructor(private callsService: CallsService) {
  }

  ngOnInit() {
  }

}
