import {Component, OnInit} from '@angular/core';
import {CallsService} from '../../calls/calls.service';
import {BrApiDetailPayloadResponse} from '../../calls/BrApiDetailPayloadResponse';
import {Chart} from 'chart.js';

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

  constructor(private callsService: CallsService) {
  }

  ngOnInit() {
    this.getSelectedCall();
  }

  getSelectedCall() {
    return this.callsService.getSelectedCall('studies')
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
    this.BarChart = new Chart('barChart', {
      type: 'bar',
      data: {
        labels: this.cropNames,
        datasets: [{
          label: '# of Votes',
          data: this.sumOfCropNames,
          backgroundColor: [
            'rgba(255, 99, 132, 0.2)',
            'rgba(54, 162, 235, 0.2)',
            'rgba(255, 206, 86, 0.2)',
            'rgba(75, 192, 192, 0.2)'
          ],
          borderColor: [
            'rgba(255, 99, 132, 1)',
            'rgba(54, 162, 235,1)',
            'rgba(255, 206, 86, 1)',
            'rgba(75, 192, 192, 1)'
          ],
          borderWidth: 1
        }]
      },
      options: {
        title: {
          text: '# of Study Type Name in CommonCropNames',
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
          text: 'BrApi Example 1',
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
