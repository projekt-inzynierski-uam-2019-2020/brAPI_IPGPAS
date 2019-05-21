import {Component, OnInit} from '@angular/core';
import {Chart} from 'chart.js';
import {CallsService} from '../calls/calls.service';
import {BrApiDetailPayloadResponse} from '../calls/BrApiDetailPayloadResponse';

@Component({
  selector: 'app-chart-page',
  templateUrl: './chart-page.component.html',
  styleUrls: ['./chart-page.component.css']
})
export class ChartPageComponent implements OnInit {
  LineChart = [];
  BarChart = [];
  brApiDetailPayloadResponse: BrApiDetailPayloadResponse;
  length: number;
  typeOfName: '';
  numbersOfName = 1;

  constructor(private callsService: CallsService) {
  }

  ngOnInit() {
    this.getSelectedCall();


  }

  columnChart() {
    this.BarChart = new Chart('barChart', {
      type: 'bar',
      data: {
        labels: ['Yield study', 'Crossing Nursery'],
        datasets: [{
          label: '# of Votes',
          data: [this.numbersOfName, this.brApiDetailPayloadResponse.result.data.length - this.numbersOfName, 3, 10],
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
        labels: ['Test1', 'Test1', 'Test3', 'Test4', 'Test5', 'Test6', 'Test7'],
        datasets: [{
          label: 'Number of Items',
          data: [9, 3, 4, 5, 1, 8, 5],
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

  getSelectedCall() {
    return this.callsService.getSelectedCall('studies')
      .subscribe(
        calls => {
          this.brApiDetailPayloadResponse = calls;
          this.getValues();
          console.log(this.numbersOfName);
        }
      );
  }

  getValues() {
    for (let i = 0; i < this.brApiDetailPayloadResponse.result.data.length; i++) {
      console.log(this.brApiDetailPayloadResponse.result.data[i].studyTypeName);
      if (this.typeOfName === this.brApiDetailPayloadResponse.result.data[i].studyTypeName) {
        this.numbersOfName = this.numbersOfName + 1;
      } else {
        this.typeOfName = this.brApiDetailPayloadResponse.result.data[i].studyTypeName;
      }
    }
    return this.numbersOfName;
  }

  counter(i: number) {
    return new Array(i);
  }

}
