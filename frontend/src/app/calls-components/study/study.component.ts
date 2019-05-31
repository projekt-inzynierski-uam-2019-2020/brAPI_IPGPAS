import {Component, OnInit} from '@angular/core';
import {CallsService} from '../../calls/calls.service';
import {BrApiDetailPayloadResponse} from '../../calls/BrApiDetailPayloadResponse';
import {Chart} from 'chart.js';
import {Server} from '../../calls/server';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-study',
  templateUrl: './study.component.html',
  styleUrls: ['./study.component.css']

})
export class StudyComponent implements OnInit {
  brApiDetailPayloadResponse: BrApiDetailPayloadResponse;
  lengthCalls: number;
  cropNames: string[] = [];
  sumOfCropNames: number[] = [];
  marked: false;
  LineChart = [];
  BarChart = [];
  canvasShow = false;
  backgroundChartColor = [];
  backgroundBorderChartColor = [];
  arrayOfRgba: string[] = [];
  arrayOfRgbaBorder: string[] = [];
  private messageSubscription: Subscription;;

  constructor(private callsService: CallsService, private server: Server) {
  }

  ngOnInit() {
    this.getSelectedCall();
  }

  getSelectedCall() {
    return this.callsService.getSelectedCall(this.server.serverName + 'studies')
      .subscribe(
        calls => {
          this.brApiDetailPayloadResponse = calls;
        }
      );
  }

  getUniqCommonCropNames() {
    this.cropNames = [];
    for (let i = 0; i < this.getCallsLength(); i++) {
      if (!this.cropNames.includes(this.brApiDetailPayloadResponse.result.data[i].commonCropName)) {
        this.cropNames.push(this.brApiDetailPayloadResponse.result.data[i].commonCropName);
      }
    }
    return this.cropNames;
  }

  getSumOfEachCropName() {
    for (let i = 0; i < this.cropNames.length; i++) {
      this.sumOfCropNames.push(0);
    }
    for (let i = 0; i < this.getCallsLength(); i++) {
      for (let j = 0; j < this.cropNames.length; j++) {
        if (this.cropNames[j] === this.brApiDetailPayloadResponse.result.data[i].commonCropName) {
          this.sumOfCropNames[j] = this.sumOfCropNames[j] + 1;
        }
      }
    }
    this.canvasShow = true;
  }

  columnChart() {
    this.backgroundChartColor = ['rgba(255, 99, 132, 0.3)', 'rgba(54, 162, 235, 0.3)', 'rgba(255, 206, 86, 0.3)', 'rgba(29, 255, 140, 0.3)', 'rgba(215, 117, 44, 0.3)', 'rgba(7, 19, 157, 0.3)', 'rgba(239, 5, 0, 0.3)', 'rgba(243, 216, 31, 0.3)', 'rgba(200, 145, 31, 0.3)', 'rgba(215, 117, 44, 0.3)'];
    this.backgroundBorderChartColor = [' rgba(255, 99, 132, 1)', 'rgba(54, 162, 235,1)', 'rgba(255, 206, 86, 1)', 'rgba(29, 255, 140, 1)', 'rgba(215, 117, 44, 1)', 'rgba(7, 19, 157, 1)', 'rgba(239, 5, 0, 1)', 'rgba(243, 216, 31, 1)', 'gba(200, 145, 31, 1)', 'rgba(215, 117, 44, 1)'];
    for (let i = 0; i < this.cropNames.length; i++) {
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
        labels: this.cropNames,
        datasets: [{
          label: '# of Votes',
          data: this.sumOfCropNames,
          backgroundColor: this.arrayOfRgba,
          borderColor: this.arrayOfRgbaBorder,
          borderWidth: 1
        }]
      },
      options: {
        title: {
          text: '# of CommonCropName in Studies',
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
        labels: this.cropNames,
        datasets: [{
          label: 'Number of Items',
          data: this.sumOfCropNames,
          fill: false,
          lineTension: 0.2,
          borderColor: 'red',
          borderWidth: 1
        }]
      },
      options: {
        title: {
          text: '# of CommonCropName in Studies',
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

  getCallsLength() {
    this.lengthCalls = this.brApiDetailPayloadResponse.result.data.length;
    return this.lengthCalls;
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
