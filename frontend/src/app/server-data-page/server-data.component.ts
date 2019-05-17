import {Component, OnInit} from '@angular/core';
import {Calls} from '../calls/calls';
import {CallsService} from '../calls/calls.service';

@Component({
  selector: 'app-chart-page',
  templateUrl: './server-data.component.html',
  styleUrls: ['./server-data.component.css']
})
export class ServerDataComponent implements OnInit {
  calls: Calls;


  constructor(private callsService: CallsService) {
  }

  ngOnInit() {
    this.getCalls();
  }

  getCalls() {
    return this.callsService.getCalls()
      .subscribe(
        calls => {
          this.calls = calls;
        }
      );
  }

  counter(i: number) {
    return new Array(i);
  }

}
