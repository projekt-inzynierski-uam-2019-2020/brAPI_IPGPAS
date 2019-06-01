import {Component, OnInit} from '@angular/core';
import {BrApiDetailPayloadResponse} from '../../calls/BrApiDetailPayloadResponse';
import {CallsService} from '../../calls/calls.service';
import {Chart} from 'chart.js';
import {Server} from '../../calls/server';
import {ChartService} from '../../services/chart-service/chart-service';

@Component({
  selector: 'app-trials',
  templateUrl: './trials.component.html',
  styleUrls: ['./trials.component.css'],
  providers: [ChartService]
})
export class TrialsComponent implements OnInit {
  brApiDetailPayloadResponse: BrApiDetailPayloadResponse;
  lengthCall: number;
  trialName: string[] = [];
  sumOfTrialNames: number[] = [];
  marked: false;
  canvasShow = false;

  constructor(private callService: CallsService,  private server: Server, private chartService: ChartService) {
  }

  ngOnInit() {
    this.getSelectedCall();
  }


  getSelectedCall() {
    return this.callService.getSelectedCall(this.server.serverName + 'studies')
      .subscribe(
        call => {
          this.brApiDetailPayloadResponse = call;
        }
      );
  }

  getUniqTrialCall() {
    this.trialName = [];
    for (let i = 0; i < this.getCallLength(); i++) {
      if (!this.trialName.includes(this.brApiDetailPayloadResponse.result.data[i].trialName)) {
        this.trialName.push(this.brApiDetailPayloadResponse.result.data[i].trialName);
      }
    }
    return this.trialName;
  }


  getSumOfTrialName() {
    for (let i = 0; i < this.trialName.length; i++) {
      this.sumOfTrialNames.push(0);
    }
    for (let i = 0; i < this.getCallLength(); i++) {
      for (let j = 0; j < this.trialName.length; j++) {
        if (this.trialName[j] === this.brApiDetailPayloadResponse.result.data[i].trialName) {
          this.sumOfTrialNames[j] = this.sumOfTrialNames[j] + 1;
        }
      }
    }
    this.canvasShow = true;
    return this.sumOfTrialNames;
  }

  showColumnChart() {
    this.chartService.chartColumnStyle(this.trialName);
    this.chartService.columnChart(this.trialName, this.sumOfTrialNames, '# of TrialName in Trials');
  }

  showLineChart() {
    this.chartService.lineChart(this.trialName, this.sumOfTrialNames, '# of TrialName in Trials');
  }

  getCallLength() {
    this.lengthCall = this.brApiDetailPayloadResponse.result.data.length;
    return this.lengthCall;
  }

  counter(i: number) {
    return new Array(i);
  }

  toggleVisibility(e) {
    this.marked = e.target.checked;
    this.showLineChart()
    this.showColumnChart()
    if (!this.marked) {
      this.canvasShow = false;
    }
  }

}
