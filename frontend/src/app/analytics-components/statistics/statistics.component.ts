import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ChartService} from '../../services/chart-service/chart-service';
import {Globals} from '../../globals';
import {VariableAverageValue} from './VariableAverageValue';
import {GermplasmValues} from './germplasmValues';
import {PdfService} from '../../services/pdf-service/pdf-service';

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

  numberofValues = [0, 0, 0, 0, 0, 0];

  dataValues: number[];
  stringLimits: string[];

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
    this.initAverageStatistics();


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

  initAverageStatistics() {
    setTimeout(() => {
    this.chartService.chartColumnStyle(this.variableAverageValue[0].variables);
    this.chartService.columnChart(this.variableAverageValue[0].variables, this.variableAverageValue[0].averageValue, 'Average of variables');
    this.chartService.lineChart(this.germplasm, this.valuesGermplasm, 'Values of Germplasms');
    this.pieChartData = this.variableAverageValue[0].averageValue;
    this.pieChartLabels = this.variableAverageValue[0].variables;
    this.chartService.columnChart2(this.stringLimits, this.numberofValues, 'Count of values in Variable');
    }, 100);
  }

  saveToPDF(elementId: string) {
    this.pdfService.donloadPDF(elementId);
  }
}
