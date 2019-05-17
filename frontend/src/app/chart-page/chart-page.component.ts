import { Component, OnInit } from '@angular/core';
import {Chart} from 'chart.js';

@Component({
  selector: 'app-chart-page',
  templateUrl: './chart-page.component.html',
  styleUrls: ['./chart-page.component.css']
})
export class ChartPageComponent implements OnInit {
  LineChart = [];
  BarChart = [];

  constructor() { }

  ngOnInit() {
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

    this.BarChart = new Chart('barChart', {
      type: 'bar',
      data: {
        labels: ['Test1', 'Test1', 'Test3', 'Test4'],
        datasets: [{
          label: '# of Votes',
          data: [9, 7, 3, 10],
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
          text: 'BrApi Example 2',
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

}
