import {Component, OnInit} from '@angular/core';
import {BrApiDetailPayloadResponse} from '../calls/BrApiDetailPayloadResponse';
import {CallsService} from '../calls/calls.service';
import {CommoncropnamesService} from '../calls/crops/commoncropnames.service';

@Component({
  selector: 'app-chart-page',
  templateUrl: './server-data.component.html',
  styleUrls: ['./server-data.component.css']
})
export class ServerDataComponent implements OnInit {
  brApiDetailPayloadResponse: BrApiDetailPayloadResponse;
  brApiDetailPayloadResponseCalls: BrApiDetailPayloadResponse;
  lengthCalls: number;
  lengthData: number;
  checkedCropNames = false;
  checkedCalls = false;
  checkedStudies = false;
  selectedOption: number;
  selectedItem: string;

  constructor(private callsService: CallsService) {
  }

  ngOnInit() {
    this.selectedOption = 4;
    this.getCalls();
  }
  selectChangeHandler (event: any) {
    this.selectedItem = event.target.value;
  }


  getCalls() {
    return this.callsService.getCalls()
      .subscribe(
        calls => {
          this.brApiDetailPayloadResponseCalls = calls;
        }
      );
  }

  getCallsLength() {
    this.lengthCalls = this.brApiDetailPayloadResponseCalls.result.data.length;
    return this.lengthCalls;
  }

  getDataLength() {
    this.lengthData = this.brApiDetailPayloadResponse.result.data.length;
    return this.lengthData;
  }

  counter(i: number) {
    return new Array(i);
  }

  getSelectedCall() {
    return this.callsService.getSelectedCall(this.selectedItem)
      .subscribe(
        calls => {
          this.brApiDetailPayloadResponse = calls;
          if (this.selectedItem === 'commoncropnames') {
            this.checkedCropNames = true;
            this.checkedCalls = false;
            this.checkedStudies = false;
          } else {
            if(this.selectedItem === 'calls') {
              this.checkedCalls = true;
              this.checkedCropNames = false;
              this.checkedStudies = false;
            } else{
              if(this.selectedItem === 'studies'){
                this.checkedCalls = false;
                this.checkedCropNames = false;
                this.checkedStudies = true;
              } else{
                if(this.selectedItem === 'trials'){
                  this.checkedCalls = false;
                  this.checkedCropNames = false;
                  this.checkedStudies = true;
                }
              }
            }
          }
        }
      );
  }
}
