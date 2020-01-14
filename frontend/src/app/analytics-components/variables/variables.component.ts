import {Component, OnInit} from '@angular/core';
import {Globals} from '../../globals';
import {GermplasmService} from '../../call-services/germplasm/germplasm-service';
import {VariableStudy} from './variableStudy';
import {VariableSelect} from './variableSelect';
import {VariableSelectValues} from './VariableSelectValues';
import {GermplasmTableData} from './studyIdVariableValues';
import {StatisticVariable} from './statisticVariable';
import {StudyStatisticVariables} from './studyStatisticVariables';
import {StudyNameStaticVariable} from './studyNameStaticVariable';
import {StatisticStudyVariable} from './statisticStudyVariable';
import {StatisticsStudyVariables} from './statisticsStudyVariables';
import {StudyVariables} from './studyVariables';
import {Router} from '@angular/router';

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
  selectedStudiesStatisticsVariables2: StudyStatisticVariables[] = [];
  selectedVariables: StatisticVariable[] = [];
  studyNameStaticVariable: StudyNameStaticVariable[] = [];
  studyStatisticVariablesFinal: StudyStatisticVariables[] = [];
  statisticStudyVariable: StatisticStudyVariable[] = [];
  statisticStudyVariable2: StatisticStudyVariable[] = [];
  statisticStudyVariables: StatisticStudyVariable[] = [];

  variablesStat: string[];
  studyVariables: StudyVariables[] = [];
  uniqStudies: string[];



  constructor(globals: Globals, germplasmService: GermplasmService, private router: Router) {
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
            this.statisticVariables[j - variableJSON.data[0].length + variableJSON.observationVariableNames.length].germplasmName.push(variableJSON.data[i][18]);
            this.statisticVariables[j - variableJSON.data[0].length + variableJSON.observationVariableNames.length].studyName.push(variableJSON.data[i][5]);

            this.statisticVariables[j - variableJSON.data[0].length + variableJSON.observationVariableNames.length].selected = false;

          }
          studyStatisticVariables.studyId = variableJSON.data[i][4];
          studyStatisticVariables.studyName = variableJSON.data[i][5];
          studyStatisticVariables.statisticVariables = this.statisticVariables;
        }
        this.studiesStatisticVariables.push(studyStatisticVariables);

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


    for (const studyVar of this.studiesStatisticVariables) {
      for (const data of studyVar.statisticVariables) {
        for (const selectedVariable of selectedVariables) {
          if (data.variableName === selectedVariable.variableName) {
            if (!this.statisticStudyVariable.some((item => item.variableName === data.variableName && item.studyName === studyVar.studyName))) {
              this.statisticStudyVariable.push({
                studyName: studyVar.studyName,
                data: data.data,
                germplasms: data.germplasms,
                selected: false,
                variableName: data.variableName,
                germplasmName: data.germplasmName
              });
            }
          }
        }
      }
    }

    for (let i = 0; i < this.statisticStudyVariable.length; i++) {
      for (let j = 1; j < this.statisticStudyVariable.length; j++) {

        if (j === 1) {
          if (this.statisticStudyVariable[i].studyName === this.statisticStudyVariable[j - 1].studyName) {
            this.statisticStudyVariables.push(this.statisticStudyVariable[j-1]);
          }
        }
        if (this.statisticStudyVariable[i].studyName === this.statisticStudyVariable[j].studyName) {
          this.statisticStudyVariables.push(this.statisticStudyVariable[j]);
        }
      }
      if (!this.studyVariables.some((item => item.studyName === this.statisticStudyVariable[i].studyName))) {
        this.studyVariables.push({studyName: this.statisticStudyVariable[i].studyName, variables: this.statisticStudyVariables});
      }

      this.statisticStudyVariables = [];

    }


    this.globals.studyStatisticVariables = this.selectedStudiesStatisticsVariables;
    this.globals.studyVariables = this.studyVariables;

    console.log(this.globals.studyVariables);
    this.globals.studyVariables.length > 0 ? this.router.navigate(['/servers/statistics']) : alert('You have to select variable first.');
  }


}
