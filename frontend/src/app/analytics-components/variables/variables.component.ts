import {Component, OnInit} from '@angular/core';
import {Globals} from '../../globals';
import {GermplasmService} from '../../call-services/germplasm/germplasm-service';
import {Variable} from '../../call-models/variable';
import {ServerStudyVariables} from './serverStudyVariables';
import {Study} from '../../call-models/study';
import {VariableCheckbox} from './VariableCheckbox';
import {VariableRow} from './VariableRow';
import {StudyColumn} from '../germplasm/StudyColumn';
import {Germplasm} from '../../call-models/germplasm';
import {VariableStudy} from './variableStudy';
import {VariableSelect} from './variableSelect';

@Component({
  selector: 'app-variables',
  templateUrl: './variables.component.html',
  styleUrls: ['./variables.component.scss']
})
export class VariablesComponent implements OnInit {

  globals: Globals;
  germplasmService: GermplasmService;
  studiesCsv: string[] = [];
  variables: Variable[] = [];
  variableStudy: VariableStudy[] = [];
  variableUniqStudy: VariableStudy[] = [];
  listOfVariablesIdsOnly: VariableSelect[] = [];
  serverStudyVariables: ServerStudyVariables[] = [];
  variableChecboxes: VariableCheckbox[] = [];
  i = 0;


  constructor(globals: Globals, germplasmService: GermplasmService) {
    this.globals = globals;
    this.germplasmService = germplasmService;
  }


  ngOnInit() {
    this.fetchVariablesBySelectedStudiesDbId();
  }

  fetchVariablesBySelectedStudiesDbId() {
    this.globals.selectedStudiesDbId.map(selectedStudy => this.germplasmService.getVariablesByStudyDbId(selectedStudy.serverUrl, selectedStudy.study.studyDbId)
      .subscribe(studyCsv => {
        this.studiesCsv.push(studyCsv);

        const csvRows: string[] = studyCsv.split('\n');
        const headerColumns: string[] = csvRows[0].split(',');
        const valuesRows: string[] = [];


        const variableIds: string[] = [];

        for (let i = 15; i < headerColumns.length; i++) {
          variableIds.push(headerColumns[i]);
        }

        for (let i = 1; i < csvRows.length; i++) {
          valuesRows.push(csvRows[i]);
        }


        const splittedValuesRows: string[][] = [];
        for (const valueRow of valuesRows) {
          splittedValuesRows.push(valueRow.split(','));
        }

        for (const splittedValueRow of splittedValuesRows) {
          const variable: Variable = new Variable();
          variable.variableIds = variableIds;
          variable.year = splittedValueRow[0];
          variable.studyDbId = splittedValueRow[1];
          variable.studyName = splittedValueRow[2];
          variable.locationDbId = splittedValueRow[3];
          variable.locationName = splittedValueRow[4];
          variable.germplasmDbId = splittedValueRow[5];
          variable.germplasmName = splittedValueRow[6];
          variable.observationUnitDbId = splittedValueRow[7];
          variable.plotNumber = splittedValueRow[8];
          variable.replicate = splittedValueRow[9];
          variable.blockNumber = splittedValueRow[10];
          variable.observationTimestamp = splittedValueRow[11];
          variable.entryType = splittedValueRow[12];
          variable.X = splittedValueRow[13];
          variable.Y = splittedValueRow[14];
          variable.variableValues = [];
          for (let i = 15; i < splittedValueRow.length; i++) {
            variable.variableValues.push(splittedValueRow[i]);

          }
          this.variables.push(variable);
          this.variableStudy.push({study: selectedStudy.study, variable: variable, selected: false, serverUrl: selectedStudy.serverUrl});
        }
        this.globals.variables = this.variables;

        this.setVariablesCheckboxes(selectedStudy.study, selectedStudy.serverUrl, this.variables);

      }));

  }

  setVariablesCheckboxes(study: Study, serverUrl: string, variables: Variable[]) {

    for (const studyV of this.variableStudy) {
      if (!this.variableUniqStudy.some((item => item.study.studyName === studyV.study.studyName))){
        this.variableUniqStudy.push({study: studyV.study, variable: studyV.variable, selected: false, serverUrl: serverUrl});
      }
    }

    variables.map(variable => this.serverStudyVariables.push({study: study, serverUrl: serverUrl, variable: variable}));

    for (const variable of variables) {
      this.variableChecboxes.push({study: study, selected: false, variable: variable});

      for (const id of variable.variableIds) {
        if (!this.listOfVariablesIdsOnly.some((item => item.variableId === id))) {
          this.listOfVariablesIdsOnly.push({variableId: id, selected: false, serverUrl: serverUrl});
        }

      }
    }
  }

  checkVariableFunction(variableRow: VariableStudy, idOfVariable: string) {
    for (const variable of variableRow.variable.variableIds) {
        if (variable === idOfVariable) {
          return  true;

        }
    }
  }

  setSelectedGermplasms() {
    const selectedVariables = this.listOfVariablesIdsOnly.filter(studyColumns => studyColumns.selected).map(studyColumns => studyColumns);

   this.globals.selectedVariables = selectedVariables;

   console.log(this.globals.selectedVariables);

   }


}
