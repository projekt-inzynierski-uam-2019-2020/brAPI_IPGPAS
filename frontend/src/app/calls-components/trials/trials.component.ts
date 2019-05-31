import {Component, OnInit} from '@angular/core';
import {BrApiDetailPayloadResponse} from '../../calls/BrApiDetailPayloadResponse';
import {CallsService} from '../../calls/calls.service';
import {Chart} from 'chart.js';
import {Server} from '../../calls/server';

@Component({
  selector: 'app-trials',
  templateUrl: './trials.component.html',
  styleUrls: ['./trials.component.css']
})
export class TrialsComponent implements OnInit {
  brApiDetailPayloadResponse: BrApiDetailPayloadResponse;
  lengthCall: number;
  trialName: string[] = [];
  sumOfTrialNames: number[] = [];
  marked: false;
  LineChart = [];
  BarChart = [];
  backgroundChartColor = [];
  backgroundBorderChartColor = [];
  arrayOfRgba: string[] = [];
  arrayOfRgbaBorder: string[] = [];
  canvasShow = false;

  constructor(private callService: CallsService,  private server: Server) {
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

  columnChart() {
    this.backgroundChartColor = ['rgba(255, 99, 132, 0.3)', 'rgba(54, 162, 235, 0.3)', 'rgba(255, 206, 86, 0.3)', 'rgba(29, 255, 140, 0.3)', 'rgba(215, 117, 44, 0.3)', 'rgba(7, 19, 157, 0.3)', 'rgba(239, 5, 0, 0.3)', 'rgba(243, 216, 31, 0.3)', 'rgba(200, 145, 31, 0.3)', 'rgba(215, 117, 44, 0.3)'];
    this.backgroundBorderChartColor = [' rgba(255, 99, 132, 1)', 'rgba(54, 162, 235,1)', 'rgba(255, 206, 86, 1)', 'rgba(29, 255, 140, 1)', 'rgba(215, 117, 44, 1)', 'rgba(7, 19, 157, 1)', 'rgba(239, 5, 0, 1)', 'rgba(243, 216, 31, 1)', 'gba(200, 145, 31, 1)', 'rgba(215, 117, 44, 1)'];
    for (let i = 0; i < this.trialName.length; i++) {
      if (i > this.backgroundChartColor.length) {
        this.arrayOfRgba[i] = this.backgroundChartColor[i % 10];
        this.arrayOfRgbaBorder[i] = this.backgroundBorderChartColor[i % 10];
      } else {
        this.arrayOfRgba[i] = this.backgroundChartColor[i];
        this.arrayOfRgbaBorder[i] = this.backgroundBorderChartColor[this.arrayOfRgba[i]];
      }
    }
    this.BarChart = new Chart('barChart', {
      type: 'bar',
      data: {
        labels: this.trialName,
        datasets: [{
          label: '# of Items',
          data: this.sumOfTrialNames,
          backgroundColor: this.arrayOfRgba,
          borderColor: this.arrayOfRgbaBorder,
          borderWidth: 2
        }]
      },
      options: {
        title: {
          text: '# of TrialName in Trials',
          fontFamily: 'Verdana',
          fontSize: 15,
          fontStyle: 'normal',
          display: true
        },
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true
            }
          }]
        }
      }
    });
  }

  lineChart() {
    this.LineChart = new Chart('lineChart', {
      type: 'line',
      data: {
        labels: this.trialName,
        datasets: [{
          label: '# of Items',
          data: this.sumOfTrialNames,
          fill: false,
          lineTension: 0.2,
          borderColor: 'red',
          borderWidth: 1
        }]
      },
      options: {
        title: {
          text: '# of TrialName in Trials',
          fontFamily: 'Verdana',
          fontSize: 15,
          fontStyle: 'normal',
          display: true
        },
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true
            }
          }]
        }
      }
    });
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
    this.lineChart();
    this.columnChart();
    if (!this.marked) {
      this.canvasShow = false;
    }
  }

}
