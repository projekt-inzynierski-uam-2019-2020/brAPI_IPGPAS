import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ChartService} from '../../services/chart-service/chart-service';
import {Globals} from '../../globals';
import {VariableAverageValue} from './VariableAverageValue';
import {GermplasmValues} from './germplasmValues';
import {PdfService} from '../../services/pdf-service/pdf-service';
import {Chart} from 'chart.js';
import {ChartData} from './chartData';
import {Data} from './data';

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
  germplasm2: string[] = [];
  valuesGermplasm2: number[] = [];
  studyName: string;

  xy: Data[] = [];

  chartData: ChartData[] = [];
  chartDataData: ChartData[] = [];
  dataForChart: number[];

  variableAverageValue: VariableAverageValue[] = [];

  isVariablesVisible = false;
  isChartsVisible = false;
  isLineChartVisible = false;
  isColumnChartVisible = false;
  isLine2ChartVisible = false;
  isLine4ChartVisible = false;
  isColumn2ChartVisible = false;
  variableStudy: string[] = [];
  isStatisticsVisible = false;
  germplasms: GermplasmValues[] = [];
  currentStudy: string;
  currentVariable: string;
  isColumnChartEmpty: boolean;
  isLineChartEmpty: boolean;
  isColumnChart2Empty: boolean;
  isLineChart3Empty: boolean;
  isLineChart4Empty: boolean;
  currentCorrelationVar: string;

  correlationData1: number[] = [];
  correlationData2: number[] = [];
  namesOfCorData: string[] = [];

  barData: ChartData[] = [];

  numberofValues = [0, 0, 0, 0, 0, 0];
  backgroundBorderChartColor = ['rgba(255, 99, 132, 1)', 'rgba(54, 162, 235,1)', 'rgba(128, 114, 254, 1)', 'rgba(29, 255, 140, 1)', 'rgba(215, 117, 44, 1)', 'rgba(7, 19, 157, 1)', 'rgba(239, 5, 0, 1)', 'rgba(243, 216, 31, 1)', 'rgba(200, 145, 31, 1)', 'rgba(49, 152, 42, 1)', 'rgba(21, 10, 10, 1)', 'rgba(133, 130, 155, 1)'];
  arrayOfRgba: string[] = [];

  stringLimits: string[];

  BarChart: Chart;
  BarChart2: Chart;
  LineChart: Chart;

  data1Co: number[] = [];
  data2Co: number[] = [];
  stringData: string[] = [];

  sortedData1Co: number[] = [];
  sortedData2Co: number[] = [];
  correlationVariables: string[] = [];

  valuesGerplasms: number [] = [];

  @ViewChild('myCanvas') myCanvas: ElementRef;

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
    this.values = [];
    this.currentStudy = studyName;


    for (const studyStatics of this.globals.studyVariables) {
      if (studyName === studyStatics.studyName) {
        this.studyName = studyName;
        for (const variable of studyStatics.variables) {
          this.variables.push(variable.variableName);
          this.value = 0;
          let counter = 0;
          for (const valueVariable of variable.data) {
            if (valueVariable !== null && valueVariable !== '') {
              if (!isNaN(parseFloat(valueVariable))) {
                this.value = this.value + parseFloat(valueVariable);
                counter = counter + 1;
              }
            }
          }
          this.values.push(this.value / counter);
          this.variableAverageValue.push({variables: this.variables, averageValue: this.values});
        }
      }

    }
  }

  getOneVariable(variable: string) {
    this.germplasm = [];
    this.valuesGermplasm = [];
    this.chartData = [];
    this.valuesGerplasms = [];



    this.correlationVariables = [];
    this.currentVariable = variable;
    let i = 0;
    for (const studyVariables of this.globals.studyVariables) {
      if (this.studyName === studyVariables.studyName) {
        this.chartColumnStyle(studyVariables.variables);
        for (const variableG of studyVariables.variables) {
          if (variableG.variableName !== variable) {
            this.correlationVariables.push(variableG.variableName);
          }
          this.dataForChart = [];
          for (let i = 0; i < variableG.germplasmName.length; i++) {
            this.dataForChart.push(parseFloat(variableG.data[i]));
            if (!isNaN(parseFloat(variableG.data[i]))){
              this.valuesGerplasms.push(parseFloat(variableG.data[i]));
            }
          }
          this.chartData.push({
            label: variableG.variableName,
            data: this.dataForChart,
            backgroundColor: this.arrayOfRgba[i],
            borderColor: 'transparent',
            fill: false
          });
          i++;

          if (variableG.variableName === variable) {
            for (let i = 0; i < variableG.germplasmName.length; i++) {
              this.germplasm.push(variableG.germplasmName[i]);
              this.valuesGermplasm.push(parseFloat(variableG.data[i]));
            }
          }
          if (variableG.variableName === 'LD') {
            for (let i = 0; i < variableG.germplasmName.length; i++) {
              this.germplasm2.push(variableG.germplasmName[i]);
              this.valuesGermplasm2.push(parseFloat(variableG.data[i]));
            }
          }

        }
      }


      this.germplasms.push({values: this.valuesGermplasm, germplasms: this.germplasm});

    }
    this.getHistogramVariable(variable);




    if ( this.valuesGerplasms.length > 0 ) { this.isLineChartEmpty = false; } else { this.isLineChartEmpty = true; }

    const averageValues = [];
    for ( let i = 0; i < this.variableAverageValue[0].averageValue.length; i++ ) {
      if ( !isNaN(this.variableAverageValue[0].averageValue[i]) ) {
        averageValues.push(this.variableAverageValue[0].averageValue[i]);
      }
    }
    if ( averageValues.length > 0 ) { this.isColumnChartEmpty = false; } else { this.isColumnChartEmpty = true; }

    const chartData = [];
    for ( let i = 0; i < this.chartData.length; i++ ) {
      for ( let j = 0; j < this.chartData[i].data.length; j++) {
        if ( !isNaN(this.chartData[i].data[j]) ) {
          chartData.push(this.numberofValues[i]);
        }
      }
    }
    if ( chartData.length > 0 ) { this.isLineChart3Empty = false; } else { this.isLineChart3Empty = true; }

    const numberOfValues = [];
    for ( let i = 0; i < this.numberofValues.length; i++ ) {
      if ( !isNaN(this.numberofValues[i]) && this.numberofValues[i] !== 0) {
        numberOfValues.push(this.numberofValues[i]);
      }
    }
    if ( numberOfValues.length > 0 ) { this.isColumnChart2Empty = false; } else { this.isColumnChart2Empty = true; }
    if ( numberOfValues.length > 0 ) { this.isLineChart3Empty = false; } else { this.isLineChart3Empty = true; }


  }

  getHistogramVariable(variable: string) {
    this.chartDataData = [];
    this.numberofValues = [0, 0, 0, 0, 0, 0];
    this.stringLimits = [];
    let max = 0;
    for (const studyVariables of this.globals.studyVariables) {
      if (this.studyName === studyVariables.studyName) {
        for (const variableG of studyVariables.variables) {
          if (variableG.variableName === variable) {
            for (let i = 0; i < variableG.data.length; i++) {
              if (!isNaN(parseFloat(variableG.data[i]))) {
                if (parseFloat(variableG.data[i]) > max) {
                  max = parseFloat(variableG.data[i]);
                }
              }
            }

            if (max !== 0) {
              this.stringLimits.push('0');
              this.stringLimits.push('0 -' + String((max / 5).toFixed(1)));
              this.stringLimits.push(String((max / 5).toFixed(1)) + ' - ' + String(((max / 5) + (max / 5)).toFixed(1)));
              this.stringLimits.push(String(((max / 5) + (max / 5)).toFixed(1)) + ' - ' + String(((max / 5) + (max / 5) + (max / 5)).toFixed(1)));
              this.stringLimits.push(String(((max / 5) + (max / 5) + (max / 5)).toFixed(1)) + ' - ' + String(((max / 5) + (max / 5) + (max / 5) + (max / 5)).toFixed(1)));
              this.stringLimits.push(String(((max / 5) + (max / 5) + (max / 5) + (max / 5)).toFixed(1)) + ' - ' + String(max));
            } else {
              this.stringLimits.push('0');
            }


            for (let i = 0; i < variableG.data.length; i++) {
              if (!isNaN(parseFloat(variableG.data[i]))) {
                ;
                if (parseFloat(variableG.data[i]) === 0) {
                  this.numberofValues[0] = this.numberofValues[0] + 1;
                } else {
                  if (parseFloat(variableG.data[i]) < max / 5) {
                    this.numberofValues[1] = this.numberofValues[1] + 1;
                  } else {
                    if (parseFloat(variableG.data[i]) < ((max / 5) + (max / 5))) {
                      this.numberofValues[2] = this.numberofValues[2] + 1;
                    } else {
                      if (parseFloat(variableG.data[i]) < ((max / 5) + (max / 5) + (max / 5))) {
                        this.numberofValues[3] = this.numberofValues[3] + 1;
                      } else {
                        if (parseFloat(variableG.data[i]) < ((max / 5) + (max / 5) + (max / 5)) + (max / 5)) {
                          this.numberofValues[4] = this.numberofValues[4] + 1;
                        } else {
                          if (parseFloat(variableG.data[i]) <= ((max / 5) + (max / 5) + (max / 5)) + (max / 5) + (max / 5)) {
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
  }

  getCorrelation(variable: string) {
    this.correlationData1 = [];
    this.correlationData2 = [];
    this.namesOfCorData = [];
    this.currentCorrelationVar = variable;
    for (const studyVariables of this.globals.studyVariables) {
      if (this.studyName === studyVariables.studyName) {
        this.chartColumnStyle(studyVariables.variables);
        for (const variableG of studyVariables.variables) {
          if (variableG.variableName === variable) {
            for (let i = 0; i < variableG.germplasmName.length; i++) {
              this.correlationData1.push(parseFloat(variableG.data[i]));
              this.namesOfCorData.push(variableG.germplasmName[i]);
            }
          }
          if (variableG.variableName === this.currentVariable) {
            for (let i = 0; i < variableG.germplasmName.length; i++) {
              this.correlationData2.push(parseFloat(variableG.data[i]));
            }
          }
        }
      }
    }

    const corelationData1 = [];
    for ( let i = 0; i < this.correlationData1.length; i++ ) {
      if ( !isNaN(this.correlationData1[i]) ) {
        corelationData1.push(this.correlationData1[i]);
      }
    }
    const corelationData2 = [];
    for ( let i = 0; i < this.correlationData2.length; i++ ) {
      if ( !isNaN(this.correlationData2[i]) ) {
        corelationData2.push(this.correlationData2[i]);
      }
    }
    if ( corelationData1.length > 0 && corelationData2.length > 0) { this.isLineChart4Empty = false; } else { this.isLineChart4Empty = true; }

  }


  initAverageStatistics() {
    setTimeout(() => {
      if (this.BarChart) {
        this.BarChart.destroy();
      }
      if (this.BarChart2) {
        this.BarChart2.destroy();
      }
      if (this.LineChart) {
        this.LineChart.destroy();
      }

      this.chartService.chartColumnStyle(this.variableAverageValue[0].variables);
      this.columnChart(this.variableAverageValue[0].variables, this.variableAverageValue[0].averageValue, 'Variables average');
      this.lineChart(this.germplasm, this.valuesGermplasm, 'Variable values');
      this.pieChartData = this.variableAverageValue[0].averageValue;
      this.pieChartLabels = this.variableAverageValue[0].variables;
      this.columnChart2(this.stringLimits, this.numberofValues, 'Variable histogram');
      this.lineChart3(this.germplasm, this.valuesGermplasm, this.valuesGermplasm2, 'Variables Values');
      this.lineChart4(this.namesOfCorData, this.correlationData1, this.correlationData2, 'Correlation', this.currentVariable, this.currentCorrelationVar);
    }, 100);
  }

  saveToPDF(elementId: string) {
    this.pdfService.donloadPDF(elementId);
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

  lineChart3(labels: any[], data: number[], data2: number[], text: string) {


    // @ts-ignore
    this.LineChart = new Chart('lineChart3', {
      type: 'line',
      data: {

        labels: labels,
        datasets: this.chartData,
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,

        legend: {
          position: 'top',
          labels: {
            // This more specific font property overrides the global property
            fontColor: '#c7c7c7'
          }
        },
        hover: {
          mode: 'index'
        },

        scales: {
          xAxes: [{
            display: true,
            scaleLabel: {
              display: true,
              labelString: 'Germplasm'
            },
            ticks: {
              beginAtZero: true,
              fontColor: '#c7c7c7'
            }
          }],
          yAxes: [{
            display: true,
            scaleLabel: {
              display: true,
              labelString: 'Value'
            },
            ticks: {
              beginAtZero: true,
              fontColor: '#c7c7c7'
            }
          }]
        },
        title: {
          display: true,
          text: text,
          fontFamily: 'Verdana',
          fontSize: 18,
          fontStyle: 'normal',
          fontColor: '#c7c7c7',
        }
      }
    });
  }


  lineChart4(labels: any[], data: number[], data2: number[], text: string, firstVar: string, secondVar: string) {

    const storage = [];
    this.data1Co = [];
    this.data2Co = [];
    this.stringData = [];
    this.xy = [];
    for (let i = 0; i < labels.length; i++) {
        if (!isNaN(data[i] && data2[i])) {
            this.data1Co.push(data[i]);
            this.data2Co.push(data2[i]);
            this.stringData.push(labels[i]);
        }
    }

    for (let i = 0; i < this.data1Co.length; i++) {
      let json = {x: this.data1Co[i], y: this.data2Co[i]};
      storage.push(json);
    }

    this.xy.push({x: this.data1Co, y: this.data2Co});




    for (let i = 0; i < this.data1Co.length - 1 ; i++) {
        if (this.data1Co[i] > this.data1Co[i + 1]) {
          this.sortedData1Co[i] = this.data1Co[i + 1];
          this.sortedData2Co[i] = this.data2Co[i + 1];
          this.sortedData1Co[i + 1] = this.data1Co[i];
          this.sortedData2Co[i + 1] = this.data2Co[i];
        } else {
          this.sortedData1Co[i] = this.data1Co[i];
          this.sortedData2Co[i] = this.data2Co[i];
        }
    }



    // @ts-ignore
    this.LineChart = new Chart('lineChart4', {
      type: 'scatter',
      data: {
        labels: storage,
        datasets: [{
          label: 'Correlation',
          borderColor: 'transparent',
          backgroundColor: 'rgba(29, 255, 140, 1)',
          data: storage,
          fill: false,
        }]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,

        legend: {
          position: 'top',
          labels: {
            // This more specific font property overrides the global property
            fontColor: '#c7c7c7'
          }
        },
        hover: {
          mode: 'index'
        },

        scales: {
          xAxes: [{
            display: true,
            scaleLabel: {
              display: true,
              labelString: secondVar
            },
            ticks: {
              beginAtZero: true,
              fontColor: '#c7c7c7'
            }
          }],
          yAxes: [{
            display: true,
            scaleLabel: {
              display: true,
              labelString: firstVar
            },
            ticks: {
              beginAtZero: true,
              fontColor: '#c7c7c7'
            }
          }]
        },
        title: {
          display: true,
          text: text + ' of ' + firstVar + ' - ' + secondVar,
          fontFamily: 'Verdana',
          fontSize: 18,
          fontStyle: 'normal',
          fontColor: '#c7c7c7',
        }
      }
    });
  }

  chartColumnStyle(nameObject: any[]) {
    this.arrayOfRgba = [];
    this.backgroundBorderChartColor = ['rgba(255, 99, 132, 1)', 'rgba(54, 162, 235,1)', 'rgba(255, 206, 86, 1)', 'rgba(29, 255, 140, 1)', 'rgba(215, 117, 44, 1)', 'rgba(7, 19, 157, 1)', 'rgba(239, 5, 0, 1)', 'rgba(243, 216, 31, 1)', 'rgba(200, 145, 31, 1)', 'rgba(215, 117, 44, 1)'];
    for (let i = 0; i < nameObject.length; i++) {
      if (i > this.backgroundBorderChartColor.length) {
        this.arrayOfRgba[i] = this.backgroundBorderChartColor[i % 10];
      } else {
        this.arrayOfRgba[i] = this.backgroundBorderChartColor[i];
      }
    }
  }


}
