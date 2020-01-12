import {Component, OnInit} from '@angular/core';
import {Globals} from '../../globals';
import {GermplasmService} from '../../call-services/germplasm/germplasm-service';
import {VariableStudy} from './variableStudy';
import {VariableSelect} from './variableSelect';
import {VariableSelectValues} from './VariableSelectValues';
import {GermplasmTableData} from './studyIdVariableValues';
import {StatisticVariable} from './statisticVariable';

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
  statisticVariable: StatisticVariable[] = [];
  germplasmId : string;


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

        for (const observationVariable of variableJSON.observationVariableNames) {
          const statisticVariable: StatisticVariable = new StatisticVariable();
          statisticVariable.variableName = observationVariable;
          this.statisticVariable.push(statisticVariable);
        }


        for (let i = 1; i < variableJSON.data.length; i++) {
          this.values = [];
          this.variableNames = [];


          for (let j = variableJSON.data[0].length - variableJSON.observationVariableNames.length; j < variableJSON.data[0].length; j++) {
           for (const statisticVariable of this.statisticVariable) {
             statisticVariable.data.push(variableJSON.data[i][j]);
             statisticVariable.germplasms.push(variableJSON.data[i][17]);
           }
          }
        }

        console.log(this.statisticVariable);




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

    console.log(selectedVariables);
  }


}
