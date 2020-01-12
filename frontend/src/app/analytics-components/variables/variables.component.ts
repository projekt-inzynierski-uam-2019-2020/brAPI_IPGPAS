import {Component, OnInit} from '@angular/core';
import {Globals} from '../../globals';
import {GermplasmService} from '../../call-services/germplasm/germplasm-service';
import {VariableStudy} from './variableStudy';
import {VariableSelect} from './variableSelect';
import {VariableSelectValues} from './VariableSelectValues';
import {GermplasmTableData} from './studyIdVariableValues';
import {StatisticVariable} from './statisticVariable';
import {StudyStatisticVariables} from './studyStatisticVariables';

@Component({
  selector: 'app-variables',
  templateUrl: './variables.component.html',
  styleUrls: ['./variables.component.scss']
})
export class VariablesComponent implements OnInit {

  globals: Globals;
  germplasmService: GermplasmService;

  variable: VariableSelect[] = [];
  variablesStudy: VariableStudy[] = [];
  isLoading = true;
  values: string[] = [];
  variableNames: any[] = [];
  statisticVariables: StatisticVariable[];
  studiesStatisticVariables: StudyStatisticVariables[] = [];
  selectedStudiesStatisticsVariables: StudyStatisticVariables[] = [];
  selectedVariables: StatisticVariable[] = [];


  constructor(globals: Globals, germplasmService: GermplasmService) {
    this.globals = globals;
    this.germplasmService = germplasmService;
  }


  ngOnInit() {
    this.fetchVariablesBySelectedStudiesDbId();
    this.globals.selectedVariablesValues = [];
  }

  fetchVariablesBySelectedStudiesDbId() {
    let loadingCounter = 0;

    this.globals.selectedStudiesDbId.map(selectedStudy => this.germplasmService.getVariablesByStudyDbId(selectedStudy.serverUrl, selectedStudy.study.studyDbId)
      .subscribe(variableJSON => {

        for (const variable of variableJSON.observationVariableNames) {
          if (!this.variable.some((item => item.variableName === variable))) {
            this.variable.push({serverUrl: selectedStudy.serverUrl, variableName: variable, selected: false, study: selectedStudy.study});

          }
        }
        this.statisticVariables = [];
        const studyStatisticVariables: StudyStatisticVariables = new StudyStatisticVariables();

        for (const observationVariable of variableJSON.observationVariableNames) {
          const statisticVariable: StatisticVariable = new StatisticVariable();
          statisticVariable.variableName = observationVariable;
          this.statisticVariables.push(statisticVariable);
        }

        for (let i = 1; i < variableJSON.data.length; i++) {
          this.values = [];
          this.variableNames = [];

          for (let j = variableJSON.data[0].length - variableJSON.observationVariableNames.length; j < variableJSON.data[0].length; j++) {
            this.statisticVariables[j - variableJSON.data[0].length + variableJSON.observationVariableNames.length].data.push(variableJSON.data[i][j]);
            this.statisticVariables[j - variableJSON.data[0].length + variableJSON.observationVariableNames.length].germplasms.push(variableJSON.data[i][17]);
            this.statisticVariables[j - variableJSON.data[0].length + variableJSON.observationVariableNames.length].selected = false;

          }
          studyStatisticVariables.studyId = variableJSON.data[i][4];
          studyStatisticVariables.studyName = variableJSON.data[i][5];
          studyStatisticVariables.statisticVariables = this.statisticVariables;
        }
        this.studiesStatisticVariables.push(studyStatisticVariables);
        console.log(this.studiesStatisticVariables);

        this.variablesStudy.push({
          variable: variableJSON.observationVariableNames,
          study: selectedStudy.study,
          serverUrl: selectedStudy.serverUrl,
          selected: false
        });

        loadingCounter = loadingCounter + 1;
        if (loadingCounter === this.globals.selectedStudiesDbId.length) {
          this.isLoading = false;
        }
      }));
  }

  checkVariableFunction(variableStudy: VariableStudy, variableName: string) {

    for (const variable of variableStudy.variable) {
      if (variable === variableName) {
        return true;
      }
    }
  }

  setSelectedVariables() {
    const selectedVariables = this.variable.filter(studyColumns => studyColumns.selected).map(studyColumns => studyColumns);

    for (const studyStatisticVariable of this.studiesStatisticVariables){
      for (const selectedVariable of selectedVariables){
        console.log(selectedVariable.study.studyDbId);
        if (studyStatisticVariable.studyId.toString() === selectedVariable.study.studyDbId.toString()) {
          for (const variableName of studyStatisticVariable.statisticVariables) {
            if (selectedVariable.variableName === variableName.variableName){
              this.selectedVariables.push(variableName);
            }
          }
          if (!this.selectedStudiesStatisticsVariables.some((item => item.studyId === studyStatisticVariable.studyId))) {
            this.selectedStudiesStatisticsVariables.push({
              studyId: studyStatisticVariable.studyId,
              statisticVariables: this.selectedVariables,
              studyName: studyStatisticVariable.studyName,
            });
          }
        }
      }
    }
    this.globals.studyStatisticVariables = this.selectedStudiesStatisticsVariables;
    console.log(this.selectedStudiesStatisticsVariables);
  }


}
