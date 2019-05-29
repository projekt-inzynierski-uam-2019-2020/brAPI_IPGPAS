import {Component, OnInit} from '@angular/core';
import {BrApiDetailPayloadResponse} from '../../calls/BrApiDetailPayloadResponse';
import {CallsService} from '../../calls/calls.service';
import {Chart} from 'chart.js';

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
  canvasShow = false;

  constructor(private callService: CallsService) {
  }

  ngOnInit() {
    this.getSelectedCall();
  }


  getSelectedCall() {
    return this.callService.getSelectedCall('trials')
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
    this.BarChart = new Chart('barChart', {
      type: 'bar',
      data: {
        labels: this.trialName,
        datasets: [{
          label: '# of Trials',
          data: this.sumOfTrialNames,
          backgroundColor: [
            'rgba(255, 99, 132, 0.2)',
            'rgba(54, 162, 235, 0.2)',
            'rgba(255, 206, 86, 0.2)',
            'rgba(29, 255, 140, 0.2)',
            'rgba(215, 117, 44, 0.2)',
            'rgba(7, 19, 157, 0.2)',
            'rgba(239, 5, 0, 0.2)',
            'rgba(243, 216, 31, 0.2)',
            'rgba(200, 145, 31, 0.2)',
          ],
          borderColor: [
            'rgba(255, 99, 132, 1)',
            'rgba(54, 162, 235,1)',
            'rgba(255, 206, 86, 1)',
            'rgba(29, 255, 140, 1)',
            'rgba(215, 117, 44, 1)',
            'rgba(7, 19, 157, 1)',
            'rgba(239, 5, 0, 1)',
            'rgba(243, 216, 31, 1)',
            ' rgba(200, 145, 31, 1)',
          ],
          borderWidth: 1
        }]
      },
      options: {
        title: {
          text: '# of Trials Name in Trials',
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
          label: 'Number of Trials',
          data: this.sumOfTrialNames,
          fill: false,
          lineTension: 0.2,
          borderColor: 'red',
          borderWidth: 1
        }]
      },
      options: {
        title: {
          text: '# of Trials Name in Trials',
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
