import {Component, OnInit} from '@angular/core';
import {BrApiDetailPayloadResponse} from '../calls/BrApiDetailPayloadResponse';
import {CallsService} from '../calls/calls/calls.service';

@Component({
  selector: 'app-chart-page',
  templateUrl: './server-data.component.html',
  styleUrls: ['./server-data.component.css']
})
export class ServerDataComponent implements OnInit {
  brApiDetailPayloadResponse: BrApiDetailPayloadResponse;
  length: number;

  constructor(private callsService: CallsService) {
  }

  ngOnInit() {
    this.getCalls();
  }

  getCalls() {
    return this.callsService.getCalls()
      .subscribe(
        calls => {
          this.brApiDetailPayloadResponse = calls;
        }
      );
  }

  getCallsLength() {
    length = this.brApiDetailPayloadResponse.result.data.length;
    return length;
  }

  counter(i: number) {
    return new Array(i);
  }


}
