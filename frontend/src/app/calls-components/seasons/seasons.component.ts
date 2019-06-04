import {Component, OnInit} from '@angular/core';
import {BrApiDetailPayloadResponse} from '../../calls/BrApiDetailPayloadResponse';
import {CallsService} from '../../calls/calls.service';
import {Server} from '../../calls/server';
import {ChartService} from '../../services/chart-service/chart-service';

@Component({
  selector: 'app-seasons',
  templateUrl: './seasons.component.html',
  styleUrls: ['./seasons.component.css'],
  providers: [ChartService],

})
export class SeasonsComponent implements OnInit {
  brApiDetailPayloadResponse: BrApiDetailPayloadResponse;
  lengthCalls: number;
  year: number[] = [];
  sumOfYears: number[] = [];
  marked: false;
  canvasShow = false;
  public pieChartLabels: number[];
  public pieChartData: number[];
  public pieChartType: string = 'pie';

  constructor(private callsService: CallsService, private server: Server, private chartService: ChartService) {
  }

  ngOnInit() {
    this.getSelectedCall();
  }

  getSelectedCall() {
    return this.callsService.getSelectedCall(this.server.serverName + 'seasons')
      .subscribe(
        calls => {
          this.brApiDetailPayloadResponse = calls;
        }
      );
  }

  getUniqCommonCropNames() {
    this.year = [];
    for (let i = 0; i < this.getCallsLength(); i++) {
      if (!this.year.includes(this.brApiDetailPayloadResponse.result.data[i].year)) {
        this.year.push(this.brApiDetailPayloadResponse.result.data[i].year);
      }
    }
    return this.year;
  }

  getSumOfEachCropName() {
    for (let i = 0; i < this.year.length; i++) {
      this.sumOfYears.push(0);
    }
    for (let i = 0; i < this.getCallsLength(); i++) {
      for (let j = 0; j < this.year.length; j++) {
        if (this.year[j] === this.brApiDetailPayloadResponse.result.data[i].year) {
          this.sumOfYears[j] = this.sumOfYears[j] + 1;
        }
      }
    }
    this.canvasShow = true;
  }

  showColumnChart() {
    this.chartService.chartColumnStyle(this.year);
    this.chartService.columnChart(this.year, this.sumOfYears, '# of Years in Seasons');
  }

  showLineChart() {
    this.chartService.lineChart(this.year, this.sumOfYears, '# of Years in Seasons');
  }

  showPieChart() {
    this.pieChartLabels = this.year;
    this.pieChartData = this.sumOfYears;
  }

  getCallsLength() {
    this.lengthCalls = this.brApiDetailPayloadResponse.result.data.length;
    return this.lengthCalls;
  }


  public chartClicked(e: any): void {
    console.log(e);
  }

  public chartHovered(e: any): void {
    console.log(e);
  }


  counter(i: number) {
    return new Array(i);
  }

  toggleVisibility(e) {
    this.marked = e.target.checked;
    this.showLineChart();
    this.showColumnChart();
    this.showPieChart();
    if (!this.marked) {
      this.canvasShow = false;
    }
  }
}
