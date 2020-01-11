import {Component, OnInit} from '@angular/core';
import {Globals} from '../../globals';
import {GermplasmService} from '../../call-services/germplasm/germplasm-service';
import {Variable} from '../../call-models/variable';
import {ServerStudyVariables} from './serverStudyVariables';
import {Study} from '../../call-models/study';
import {VariableCheckbox} from './VariableCheckbox';
import {VariableStudy} from './variableStudy';
import {VariableSelect} from './variableSelect';
import {VariableIdSum} from './variableIdSum';
import {VariableIdValues} from './variableIdValues';
import {StudyColumns} from './studyColumns';

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


  constructor(globals: Globals, germplasmService: GermplasmService) {
    this.globals = globals;
    this.germplasmService = germplasmService;
  }


  ngOnInit() {
    this.fetchVariablesBySelectedStudiesDbId();
    this.globals.selectedVariablesValues = [];
  }

  fetchVariablesBySelectedStudiesDbId() {
    this.globals.selectedStudiesDbId.map(selectedStudy => this.germplasmService.getVariablesByStudyDbId(selectedStudy.serverUrl, selectedStudy.study.studyDbId)
      .subscribe(variableJSON => {

        for (const variable of variableJSON.observationVariableNames) {
          if (!this.variable.some((item => item.variableName === variable))) {
            this.variable.push({serverUrl: selectedStudy.serverUrl, variableName: variable, selected: false});
            this.variableStudy.push({serverUrl: selectedStudy.serverUrl, variable: variable, study: selectedStudy.study, selected: false});
          }
        }

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

        console.log(this.variableUniqStudy);
        console.log(this.variableStudy);
        console.log(this.variable);
        console.log(variableJSON.observationVariableNames);
        console.log(variableJSON.observationVariableNames.length);
        console.log(variableJSON.data);

      }));
  }

  checkVariableFunction(variableRow: VariableStudy, idOfVariable: string) {
    for (const variable of variableRow.variable.variableIds) {
      if (variable === idOfVariable) {
        return true;
      }
    }
  }


}
