import {Component, OnInit} from '@angular/core';
import {Globals} from '../../globals';
import {GermplasmService} from '../../call-services/germplasm/germplasm-service';
import {VariableStudy} from './variableStudy';
import {VariableSelect} from './variableSelect';
import {visitRootRenderNodes} from '@angular/core/src/view/util';

@Component({
  selector: 'app-variables',
  templateUrl: './variables.component.html',
  styleUrls: ['./variables.component.scss']
})
export class VariablesComponent implements OnInit {

  globals: Globals;
  germplasmService: GermplasmService;

  variable: VariableSelect[] = [];
  variableStudy: VariableStudy[] = [];
  variableUniqStudy: VariableStudy[] = [];
  variablesStudy: VariableStudy[] = [];
  isLoading = true;



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
            this.variable.push({serverUrl: selectedStudy.serverUrl, variableName: variable, selected: false});

          }
        }

        for ( let j = 1; j < variableJSON.data.length; j++) {
          for ( let i = variableJSON.data[0].length - variableJSON.observationVariableNames.length; i < variableJSON.data[0].length; i++ ) {
            console.log(variableJSON.data[j][i]);
          }

        }

        this.variablesStudy.push({variable: variableJSON.observationVariableNames, study: selectedStudy.study, serverUrl: selectedStudy.serverUrl, selected: false});

        for (const studyV of this.variableStudy) {
          if (!this.variableUniqStudy.some((item => item.study.studyName === studyV.study.studyName))) {
            this.variableUniqStudy.push({
              study: studyV.study,
              variable: studyV.variable,
              selected: false,
              serverUrl: selectedStudy.serverUrl
            });
          }
        }

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
    const selectedStudies = this.variable.filter(studyColumns => studyColumns.selected).map(studyColumns => studyColumns);
    console.log(selectedStudies);
  }


}
