import { Component, OnInit } from '@angular/core';
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

  constructor( private chartService: ChartService, globals: Globals) {
    this.globals = globals;

  }

  ngOnInit() {
    this.globals.variables.map(variable =>  this.pieChartLabels = variable.variableIds);
    this.globals.variables.map(variable => this.pieChartData = [23, 12, 23 ,1]);
    this.pieChartData = [10, 23, 45, 34];
    this.initStatistics();
  }

  initStatistics() {
    console.log(this.globals.variables[1].year);
    this.globals.variables.map(variable => this.chartService.chartColumnStyle(variable.variableIds));
     this.globals.variables.map(variable => this.chartService.columnChart(variable.variableIds, variable.variableValues, 'dupa' ));
  }

}
