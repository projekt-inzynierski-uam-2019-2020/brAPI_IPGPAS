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

  constructor(private chartService: ChartService, globals: Globals,  private pdfService: PdfService) {
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

    for (const studyStaticsVariables of this.globals.studyStatisticVariables) {
      if (studyName === studyStaticsVariables.studyName) {
        this.studyName = studyName;
        for (const variable of studyStaticsVariables.statisticVariables) {
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

  getOneVariable(variable: string){
    this.germplasm = [];
    this.valuesGermplasm = [];
    for (const studyStaticsVariables of this.globals.studyStatisticVariables) {
      if (this.studyName === studyStaticsVariables.studyName) {
        for (const variableGerm of studyStaticsVariables.statisticVariables) {
          if (variableGerm.variableName === variable){
            for (let i = 0; i < variableGerm.germplasmName.length; i++){
              if (variableGerm.data[i] !== null){
                this.germplasm.push(variableGerm.germplasmName[i]);
                this.valuesGermplasm.push(parseFloat(variableGerm.data[i]));
              }
            }
          }
        }
      }
    }
    this.germplasms.push({values: this.valuesGermplasm, germplasms: this.germplasm});

  }


  initAverageStatistics() {
    console.log(this.germplasms);
    console.log(this.valuesGermplasm);
    this.chartService.chartColumnStyle(this.variableAverageValue[0].variables);
    this.chartService.columnChart(this.variableAverageValue[0].variables, this.variableAverageValue[0].averageValue, 'Average of variables');
    this.chartService.lineChart(this.germplasm, this.valuesGermplasm, 'Values of Germplasms');
    this.pieChartData = this.variableAverageValue[0].averageValue;
    this.pieChartLabels = this.variableAverageValue[0].variables;
  }

  saveToPDF(elementId: string){
    this.pdfService.donloadPDF(elementId);
  }

}
