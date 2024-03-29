import {Injectable} from '@angular/core';
import {Chart} from 'chart.js';

@Injectable()
export class ChartService {
  BuubbleChart = [];
  backgroundChartColor = [];
  backgroundBorderChartColor = [];
  BarChart: Chart;
  arrayOfRgba: string[] = [];
  arrayOfRgbaBorder: string[] = [];


  chartColumnStyle(nameObject: any[]) {
    this.backgroundChartColor = ['rgba(255, 99, 132, 0.3)', 'rgba(54, 162, 235, 0.3)', 'rgba(255, 206, 86, 0.3)', 'rgba(29, 255, 140, 0.3)', 'rgba(215, 117, 44, 0.3)', 'rgba(7, 19, 157, 0.3)', 'rgba(239, 5, 0, 0.3)', 'rgba(243, 216, 31, 0.3)', 'rgba(200, 145, 31, 0.3)', 'rgba(215, 117, 44, 0.3)'];
    this.backgroundBorderChartColor = ['rgba(255, 99, 132, 1)', 'rgba(54, 162, 235,1)', 'rgba(255, 206, 86, 1)', 'rgba(29, 255, 140, 1)', 'rgba(215, 117, 44, 1)', 'rgba(7, 19, 157, 1)', 'rgba(239, 5, 0, 1)', 'rgba(243, 216, 31, 1)', 'rgba(200, 145, 31, 1)', 'rgba(215, 117, 44, 1)'];
    for (let i = 0; i < nameObject.length; i++) {
      if (i > this.backgroundChartColor.length) {
        this.arrayOfRgba[i] = this.backgroundChartColor[i % 10];
        this.arrayOfRgbaBorder[i] = this.backgroundBorderChartColor[i % 10];
      } else {
        this.arrayOfRgba[i] = this.backgroundChartColor[i];
        this.arrayOfRgbaBorder[i] = this.backgroundBorderChartColor[this.arrayOfRgba[i]];
      }
    }
  }



  columnChart(labels: any[], data: any[], text: string) {
   // this.BarChart.destroy();
    // @ts-ignore
    this.BarChart = new Chart('barChart', {
      type: 'bar',
      data: {
        labels: labels,
        datasets: [{
          label: 'average of variable',
          data: data,
          backgroundColor: this.backgroundChartColor,
          borderColor: this.backgroundBorderChartColor,
          borderWidth: 1,
        }]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        legend: {
          labels: {
            // This more specific font property overrides the global property
            fontColor: '#c7c7c7'
          }
        },
        title: {
          text: text,
          fontFamily: 'Verdana',
          fontSize: 18,
          fontStyle: 'normal',
          fontColor: '#c7c7c7',
          display: true
        },
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true,
              fontColor: '#c7c7c7'
            }
          }],
          xAxes: [{
            ticks: {
              fontColor: '#c7c7c7'
            }
          }]
        }
      }
    });
  }

  columnChart2(labels: any[], data: any[], text: string) {

    this.BarChart.update();
    // @ts-ignore
    this.BarChart = new Chart('barChart2', {
      type: 'bar',
      data: {
        labels: labels,
        datasets: [{
          label: 'average of variable',
          data: data,
          backgroundColor: this.backgroundChartColor,
          borderColor: this.backgroundBorderChartColor,
          borderWidth: 1,
        }]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        legend: {
          labels: {
            // This more specific font property overrides the global property
            fontColor: '#c7c7c7'
          }
        },
        title: {
          text: text,
          fontFamily: 'Verdana',
          fontSize: 18,
          fontStyle: 'normal',
          fontColor: '#c7c7c7',
          display: true
        },
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true,
              fontColor: '#c7c7c7'
            }
          }],
          xAxes: [{
            ticks: {
              fontColor: '#c7c7c7'
            }
          }]
        }
      }
    });
  }

  lineChart(labels: any[], data: number[], text: string) {

    // @ts-ignore
    this.LineChart = new Chart('lineChart', {
      type: 'line',
      data: {
        labels: labels,
        datasets: [{
          label: 'Germplasm-Value',
          data: data,
          fill: false,
          lineTension: 0.2,
          borderColor: 'transparent',
          pointBackgroundColor: 'white',
          borderWidth: 1
        }]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        legend: {
          labels: {
            // This more specific font property overrides the global property
            fontColor: '#c7c7c7'
          }
        },
        title: {
          text: text,
          fontFamily: 'Verdana',
          fontSize: 18,
          fontStyle: 'normal',
          fontColor: '#c7c7c7',
          display: true
        },
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true,
              fontColor: '#c7c7c7'
            }
          }],
          xAxes: [{
            ticks: {
              fontColor: '#c7c7c7'
            }
          }]
        },
      }
    });
  }

}
