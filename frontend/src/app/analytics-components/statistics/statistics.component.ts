import {Component, OnInit} from '@angular/core';
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

  barCharVisibility = false;

  variableAverageValue: VariableAverageValue[] = [];

  showAverageStats: false;
  public pieChartType = 'pie';

  isVariablesVisible = false;
  isStatisticsVisible = false;
  variableStudy: string[] = [];
  germplasms: GermplasmValues[] = [];

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


  }


  initAverageStatistics() {
    this.chartService.chartColumnStyle(this.variableAverageValue[0].variables);
    this.chartService.columnChart(this.variableAverageValue[0].variables, this.variableAverageValue[0].averageValue, 'Average of variables');
    this.chartService.lineChart(this.germplasm, this.valuesGermplasm, 'Values of Germplasms');
    this.pieChartData = this.variableAverageValue[0].averageValue;
    this.pieChartLabels = this.variableAverageValue[0].variables;
  }

  saveToPDF(elementId: string) {
    this.pdfService.donloadPDF(elementId);
  }

}
