import {Component, OnInit} from '@angular/core';
import {ChartService} from '../../services/chart-service/chart-service';
import {Globals} from '../../globals';

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.scss']
})
export class StatisticsComponent implements OnInit {

  globals: Globals;
  public pieChartLabels: string[];
  public pieChartData: number[];
  public pieChartType = 'pie';
  allVariablesIds: string[] = [];
  allValues: number[] = [];
  test = [1, 2, 3, 4];

  constructor(private chartService: ChartService, globals: Globals) {
    this.globals = globals;

  }

  ngOnInit() {
    this.allValues = [];
    this.allVariablesIds = [];
    this.globals.variables.map(variable => this.pieChartLabels = variable.variableIds);
    this.globals.variables.map(variable => this.pieChartData = [23, 12, 23, 1]);
    this.pieChartData = [10, 23, 45, 34];
    this.initStatistics();
  }

  initStatistics() {
    console.log(this.globals.variables[1].year);

    for (const variable of this.globals.selectedVariablesValues) {
        this.allVariablesIds.push(variable.variable.variableId);
    }

    for (const variable of this.globals.selectedVariablesValues) {
      let average = 0;
      for (let i = 0; i < variable.values.length; i ++) {
        average = average + variable.values[i];
      }
      this.allValues.push(average / variable.values.length);
    }

    console.log(this.allValues);
    console.log(this.allVariablesIds);
    this.chartService.chartColumnStyle(this.allVariablesIds);
    this.chartService.columnChart(this.allVariablesIds, this.allValues, 'Average of variables');
  }

}
