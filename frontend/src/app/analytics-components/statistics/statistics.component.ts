import {Component, OnInit} from '@angular/core';
import {ChartService} from '../../services/chart-service/chart-service';
import {Globals} from '../../globals';
import {VariableAverageValue} from './VariableAverageValue';

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.scss']
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

  barCharVisibility = false;

  variableAverageValue: VariableAverageValue[] = [];

  showAverageStats: false;

  constructor(private chartService: ChartService, globals: Globals) {
    this.globals = globals;

  }

  ngOnInit() {
    this.allValues = [];
    this.allVariablesIds = [];
    this.globals.variables.map(variable => this.pieChartLabels = variable.variableIds);
    this.pieChartData = [10, 23, 45, 34];
    console.log(this.globals.studyStatisticVariables);

  }

  initStatistics() {

    for (const variable of this.globals.selectedVariablesValues) {
     //   this.allVariablesIds.push(variable.variable.variableId);
    }

    for (const variable of this.globals.selectedVariablesValues) {
      let average = 0;
      for (let i = 0; i < variable.values.length; i ++) {
        average = average + variable.values[i];
      }
      this.allValues.push(average / variable.values.length);
    }

    this.chartService.chartColumnStyle(this.allVariablesIds);
    this.chartService.columnChart(this.allVariablesIds, this.allValues, 'Average of variables');
  }

  getVariablesFunction(studyName: string){
    this.variables = [];
    for (const studyStaticsVariables of this.globals.studyStatisticVariables){
      if (studyName === studyStaticsVariables.studyName) {
        for (const variable of studyStaticsVariables.statisticVariables) {
          this.variables.push(variable.variableName);
          this.value = 0;
          for (const valueVariable of variable.data) {
            if (valueVariable !== null) {
              this.value = this.value + parseFloat(valueVariable);
            }
            console.log(this.value);
          }
          this.values.push(this.value / variable.data.length);
          this.variableAverageValue.push({variables: this.variables, averageValue: this.values});
        }
      }
    }
    this.initAverageStatistics();
    this.barCharVisibility = true;

    console.log(this.variableAverageValue);
  }

  initAverageStatistics(){

    this.chartService.chartColumnStyle(this.variableAverageValue[0].variables);
    this.chartService.columnChart(this.variableAverageValue[0].variables, this.variableAverageValue[0].averageValue, 'Average of variables');
  }

}
