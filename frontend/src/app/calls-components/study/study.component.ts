import {Component, OnInit} from '@angular/core';
import {CallsService} from '../../calls/calls.service';
import {BrApiDetailPayloadResponse} from '../../calls/BrApiDetailPayloadResponse';
import {Chart} from 'chart.js';
import {Server} from '../../calls/server';
import * as jsPDF from 'jspdf';
import {ChartService} from '../../services/chart-service/chart-service';
import {PdfService} from '../../services/pdf-service/pdf-service';



@Component({
  selector: 'app-study',
  templateUrl: './study.component.html',
  styleUrls: ['./study.component.css'],
  providers: [ChartService, PdfService],

})
export class StudyComponent implements OnInit {
  brApiDetailPayloadResponse: BrApiDetailPayloadResponse;
  lengthCalls: number;
  cropNames: string[] = [];
  sumOfCropNames: number[] = [];
  marked: false;
  canvasShow = false;
  public pieChartLabels: string[];
  public pieChartData: number[];
  public pieChartType: string = 'pie';
  doc = new jsPDF();

  constructor(private callsService: CallsService, private server: Server, private chartService: ChartService, private pdfService: PdfService) {
  }

  ngOnInit() {
    this.getSelectedCall();
  }


  getSelectedCall() {
    return this.callsService.getSelectedCall(this.server.serverName + 'studies')
      .subscribe(
        calls => {
          this.brApiDetailPayloadResponse = calls;
        }
      );
  }

  getUniqCommonCropNames() {
    this.cropNames = [];
    for (let i = 0; i < this.getCallsLength(); i++) {
      if (!this.cropNames.includes(this.brApiDetailPayloadResponse.result.data[i].commonCropName)) {
        this.cropNames.push(this.brApiDetailPayloadResponse.result.data[i].commonCropName);
      }
    }
    return this.cropNames;
  }

  getSumOfEachCropName() {
    for (let i = 0; i < this.cropNames.length; i++) {
      this.sumOfCropNames.push(0);
    }
    for (let i = 0; i < this.getCallsLength(); i++) {
      for (let j = 0; j < this.cropNames.length; j++) {
        if (this.cropNames[j] === this.brApiDetailPayloadResponse.result.data[i].commonCropName) {
          this.sumOfCropNames[j] = this.sumOfCropNames[j] + 1;
        }
      }
    }
    this.canvasShow = true;
  }

  showColumnChart() {
    this.chartService.chartColumnStyle(this.cropNames);
    this.chartService.columnChart(this.cropNames, this.sumOfCropNames, '# of CommonCropName in Studies');
  }

  showLineChart() {
    this.chartService.lineChart(this.cropNames, this.sumOfCropNames, '# of CommonCropName in Studies');
  }

  showPieChart() {
    this.pieChartLabels = this.cropNames;
    this.pieChartData = this.sumOfCropNames;
  }

  saveToPDF(elementId: string){
    this.pdfService.donloadPDF(elementId);
  }

  getCallsLength() {
    this.lengthCalls = this.brApiDetailPayloadResponse.result.data.length;
    return this.lengthCalls;
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
