import {Injectable} from '@angular/core';
import {Chart} from 'chart.js';

@Injectable()
export class ChartService {
  BarChart = [];
  LineChart = [];
  backgroundChartColor = [];
  backgroundBorderChartColor = [];
  arrayOfRgba: string[] = [];
  arrayOfRgbaBorder: string[] = [];


  chartColumnStyle(nameObject: string[]) {
    this.backgroundChartColor = ['rgba(255, 99, 132, 0.3)', 'rgba(54, 162, 235, 0.3)', 'rgba(255, 206, 86, 0.3)', 'rgba(29, 255, 140, 0.3)', 'rgba(215, 117, 44, 0.3)', 'rgba(7, 19, 157, 0.3)', 'rgba(239, 5, 0, 0.3)', 'rgba(243, 216, 31, 0.3)', 'rgba(200, 145, 31, 0.3)', 'rgba(215, 117, 44, 0.3)'];
    this.backgroundBorderChartColor = [' rgba(255, 99, 132, 1)', 'rgba(54, 162, 235,1)', 'rgba(255, 206, 86, 1)', 'rgba(29, 255, 140, 1)', 'rgba(215, 117, 44, 1)', 'rgba(7, 19, 157, 1)', 'rgba(239, 5, 0, 1)', 'rgba(243, 216, 31, 1)', 'gba(200, 145, 31, 1)', 'rgba(215, 117, 44, 1)'];
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

  columnChart(labels: string[], data: number[]) {

    this.BarChart = new Chart('barChart', {
      type: 'bar',
      data: {
        labels: labels,
        datasets: [{
          label: '# of Items',
          data: data,
          backgroundColor: this.backgroundChartColor,
          borderColor: this.backgroundBorderChartColor,
          borderWidth: 1
        }]
      },
      options: {
        title: {
          text: '# of CommonCropName in Studies',
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
  

}
