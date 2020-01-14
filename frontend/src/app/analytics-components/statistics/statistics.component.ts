import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ChartService} from '../../services/chart-service/chart-service';
import {Globals} from '../../globals';
import {VariableAverageValue} from './VariableAverageValue';
import {GermplasmValues} from './germplasmValues';
import {PdfService} from '../../services/pdf-service/pdf-service';
import {Chart} from 'chart.js';

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.scss'],
  providers: [PdfService]
})
export class StatisticsComponent implements OnInit {

  globals: Globals;
  public pieChartLabels: string[];
  public pieChartData: number[];
  allVariablesIds: string[] = [];
  allValues: number[] = [];
  variables: string[] = [];

  values: number[] = [];
  value: number;

  germplasm: string[] = [];
  valuesGermplasm: number[] = [];
  studyName: string;

  variableAverageValue: VariableAverageValue[] = [];

  isVariablesVisible = false;
  isChartsVisible = false;
  isLineChartVisible = false;
  isColumnChartVisible = false;
  isColumn2ChartVisible = false;
  variableStudy: string[] = [];
  isStatisticsVisible = false;
  germplasms: GermplasmValues[] = [];
  selectedStudy = null;


  numberofValues = [0, 0, 0, 0, 0, 0];

  dataValues: number[];
  stringLimits: string[];

  BarChart: Chart;
  BarChart2: Chart;
  LineChart: Chart;

  @ViewChild('myCanvas') myCanvas: ElementRef;
  public context: CanvasRenderingContext2D;

  constructor(private chartService: ChartService, globals: Globals, private pdfService: PdfService) {
    this.globals = globals;

  }

  ngOnInit() {
    this.allValues = [];
    this.allVariablesIds = [];
    this.globals.variables.map(variable => this.pieChartLabels = variable.variableIds);
    this.pieChartData = [10, 23, 45, 34];

  }


  getVariablesFunction(studyName: string) {
    this.variables = [];
    this.variableAverageValue = [];

    for (const studyStatics of this.globals.studyVariables) {
      if (studyName === studyStatics.studyName) {
        this.studyName = studyName;
        for (const variable of studyStatics.variables) {
          this.variables.push(variable.variableName);
          this.value = 0;
          for (const valueVariable of variable.data) {
            if (valueVariable !== null) {
              this.value = this.value + parseFloat(valueVariable);
            }
          }
          this.values.push(this.value / variable.data.length);
          this.variableAverageValue.push({variables: this.variables, averageValue: this.values});

        }
      }

    }
  }

  getOneVariable(variable: string) {
    this.germplasm = [];
    this.valuesGermplasm = [];
    for (const studyVariables of this.globals.studyVariables) {
      if (this.studyName === studyVariables.studyName) {
        for (const variableG of studyVariables.variables) {
          if (variableG.variableName === variable) {
            for (let i = 0; i < variableG.germplasmName.length; i++) {
              this.germplasm.push(variableG.germplasmName[i]);
              this.valuesGermplasm.push(parseFloat(variableG.data[i]));
            }
          }
        }
      }

      this.germplasms.push({values: this.valuesGermplasm, germplasms: this.germplasm});

    }
    this.getHistogramVariable(variable);

  }

  getHistogramVariable(variable: string) {
    for (const studyVariables of this.globals.studyVariables) {
      if (this.studyName === studyVariables.studyName) {
        for (const variableG of studyVariables.variables) {
          this.dataValues = [];
          this.numberofValues = [0, 0, 0, 0, 0 , 0];
          this.stringLimits = [];
          let max = 0;
          if (variableG.variableName === variable) {
            this.dataValues.push(0);
            for (let i = 0; i < variableG.data.length; i++) {
              if (parseFloat(variableG.data[i]) > max) {
                max = parseFloat(variableG.data[i]);
              }
            }
            this.dataValues.push(max / 5);
            this.dataValues.push((max / 5) + (max / 5));
            this.dataValues.push((max / 5) + (max / 5) +  (max / 5));
            this.dataValues.push((max / 5) + (max / 5) + (max / 5) + (max / 5));
            this.dataValues.push(max);




            this.stringLimits.push('0');
            this.stringLimits.push('0 -' +  String((max / 5).toFixed(1)) );
            this.stringLimits.push(String((max / 5).toFixed(1)) + ' - ' + String(((max / 5) + (max / 5)).toFixed(1)));
            this.stringLimits.push( String(((max / 5) + (max / 5)).toFixed(1))  + ' - ' + String(((max / 5) + (max / 5)  + (max / 5)).toFixed(1)));
            this.stringLimits.push( String(((max / 5) + (max / 5)  + (max / 5)).toFixed(1))  + ' - ' + String(((max / 5) + (max / 5)  + (max / 5) + (max / 5)).toFixed(1)));
            this.stringLimits.push( String(((max / 5) + (max / 5)  + (max / 5) + (max / 5)).toFixed(1))  + ' - ' + String(max));



            for (let i = 0; i < variableG.data.length; i++) {
              if(parseFloat(variableG.data[i]) === 0) {
                this.numberofValues[0] = this.numberofValues[0] + 1;
              } else {
                if (parseFloat(variableG.data[i]) < max / 5) {
                  this.numberofValues[1] = this.numberofValues[1] + 1;
                } else {
                  if (parseFloat(variableG.data[i]) < ((max / 5) + (max / 5))) {
                    this.numberofValues[2] = this.numberofValues[2] + 1;
                  } else {
                    if (parseFloat(variableG.data[i]) < ((max / 5) + (max / 5) +  (max / 5))) {
                      this.numberofValues[3] = this.numberofValues[3] + 1;
                    } else {
                      if (parseFloat(variableG.data[i]) < ((max / 5) + (max / 5) +  (max / 5) ) +  (max / 5)) {
                        this.numberofValues[4] = this.numberofValues[4] + 1;
                      } else {
                        if (parseFloat(variableG.data[i]) <= ((max / 5) + (max / 5) +  (max / 5) ) +  (max / 5) + (max / 5)) {
                          this.numberofValues[5] = this.numberofValues[5] + 1;

                        }
                        }
                      }
                  }
              }


              }
            }



          }
        }
      }
    }
  }


  columnChart(labels: any[], data: any[], text: string) {

    // @ts-ignore
    this.BarChart = new Chart('barChart', {
      type: 'bar',
      data: {
        labels: labels,
        datasets: [{
          label: 'average of variable',
          data: data,
          backgroundColor: this.chartService.backgroundChartColor,
          borderColor: this.chartService.backgroundBorderChartColor,
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

    // @ts-ignore
    this.BarChart2 = new Chart('barChart2', {
      type: 'bar',
      data: {
        labels: labels,
        datasets: [{
          label: 'average of variable',
          data: data,
          backgroundColor: this.chartService.backgroundChartColor,
          borderColor: this.chartService.backgroundBorderChartColor,
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

  initAverageStatistics() {
    setTimeout(() => {
    if (this.BarChart) { this.BarChart.destroy(); }
    if (this.BarChart2) { this.BarChart2.destroy(); }
    if (this.LineChart) { this.LineChart.destroy(); }
    this.chartService.chartColumnStyle(this.variableAverageValue[0].variables);
    this.columnChart(this.variableAverageValue[0].variables, this.variableAverageValue[0].averageValue, 'Average of variables');
    this.lineChart(this.germplasm, this.valuesGermplasm, 'Values of Germplasms');
    this.pieChartData = this.variableAverageValue[0].averageValue;
    this.pieChartLabels = this.variableAverageValue[0].variables;
    this.columnChart2(this.stringLimits, this.numberofValues, 'Count of values in Variable');
    }, 100);
  }

  saveToPDF(elementId: string) {
    this.pdfService.donloadPDF(elementId);
  }
}
