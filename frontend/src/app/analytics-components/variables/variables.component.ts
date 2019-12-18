import {Component, OnInit} from '@angular/core';
import {Globals} from '../../globals';
import {GermplasmService} from '../../call-services/germplasm/germplasm-service';
import {Variable} from '../../call-models/variable';
import {ServerStudyVariables} from './serverStudyVariables';
import {Study} from '../../call-models/study';
import {VariableCheckbox} from './VariableCheckbox';
import {VariableStudy} from './variableStudy';
import {VariableSelect} from './variableSelect';
import {injectTemplateRef} from '@angular/core/src/render3';
import {ValuesStudy} from './valuesStudy';
import {VariableIdSum} from './variableIdSum';
import {VariableIdValues} from './variableIdValues';

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
  valuesSumVarStudy: VariableStudy[] = [];
  variableIdSum: VariableIdSum[] = [];
  varUniqArSum: VariableIdSum[] = [];
  valuesStudy: ValuesStudy[] = [];
  listOfVariablesIdsOnly: VariableSelect[] = [];
  serverStudyVariables: ServerStudyVariables[] = [];
  variableChecboxes: VariableCheckbox[] = [];
  countvalues: number[] = [];
  variableIdValues: VariableIdValues[] = [];
  variabeIdReadyValues: VariableIdValues[] = [];
  i = 0;


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
        console.log(this.variables);

      }));

  }

  setVariablesCheckboxes(study: Study, serverUrl: string, variables: Variable[]) {

    for (const studyV of this.variableStudy) {
      if (!this.variableUniqStudy.some((item => item.study.studyName === studyV.study.studyName))) {
        this.variableUniqStudy.push({study: studyV.study, variable: studyV.variable, selected: false, serverUrl: serverUrl});
      }
    }

    variables.map(variable => this.serverStudyVariables.push({study: study, serverUrl: serverUrl, variable: variable}));

    console.log(variables);

    for (const variable of variables) {
      this.variableChecboxes.push({study: study, selected: false, variable: variable});

      for (let i = 0; i < variable.variableIds.length; i++) {
        if (!this.listOfVariablesIdsOnly.some((item => item.variableId === variable.variableIds[i]))) {
          this.listOfVariablesIdsOnly.push({
            variableId: variable.variableIds[i],
            selected: false,
            serverUrl: serverUrl,
            variabelValue: variable.variableValues[i]
          });

        }

      }
    }
    for (const variab of variables) {
      for (let i = 0; i < variab.variableIds.length; i++) {
        if (this.listOfVariablesIdsOnly.some((item => item.variableId === variab.variableIds[i]))) {
          if (variab.variableValues[i]) {
            if (variab.variableValues[i] !== '""') {
              if (!isNaN(Number(variab.variableValues[i].replace(/"/gi, '')))) {
                if (!this.variableIdSum.some((item => item.variableId === variab.variableIds[i] && item.sumValue === (Number(variab.variableValues[i].replace(/"/gi, '')))))) {
                  this.variableIdSum.push({
                    variableId: variab.variableIds[i],
                    sumValue: Number(variab.variableValues[i].replace(/"/gi, ''))
                  });
                }
              }
            }
          }
        }
      }
    }

    console.log(this.listOfVariablesIdsOnly);


    for (let i = 0; i < this.listOfVariablesIdsOnly.length; i++) {
      this.countvalues = [];
      for (let j = 0; j < this.variableIdSum.length; j++) {
        if (this.listOfVariablesIdsOnly[i].variableId === this.variableIdSum[j].variableId) {
          this.countvalues.push(this.variableIdSum[j].sumValue);
        }
      }
      this.variableIdValues.push({variableId: this.listOfVariablesIdsOnly[i].variableId, values: this.countvalues});
    }

    console.log(this.variableIdValues.length);
    console.log(this.listOfVariablesIdsOnly.length);


    console.log(this.variableIdSum);
    console.log(this.variableIdValues);
    console.log(this.variableIdSum.length);

    for (let i = 0; i < this.variableIdSum.length; i++) {
      if (!this.varUniqArSum.some((item => item.variableId === this.variableIdSum[i].variableId))) {
        this.varUniqArSum.push({variableId: this.variableIdSum[i].variableId, sumValue: this.variableIdSum[i].sumValue});
      }
    }
    console.log(this.varUniqArSum);
  }

  checkVariableFunction(variableRow: VariableStudy, idOfVariable: string) {
    for (const variable of variableRow.variable.variableIds) {
      if (variable === idOfVariable) {
        return true;

      }
    }
  }

  setSelectedGermplasms() {

    for (let i = this.variableIdValues.length - this.listOfVariablesIdsOnly.length; i < this.variableIdValues.length; i++) {
      this.variabeIdReadyValues.push(this.variableIdValues[i]);
    }
    console.log(this.variabeIdReadyValues);


    const selectedVariables = this.listOfVariablesIdsOnly.filter(studyColumns => studyColumns.selected).map(studyColumns => studyColumns);

    for (const variableReady of this.variabeIdReadyValues) {
      for (const selectendVariable of selectedVariables) {
        if (selectendVariable.variableId === variableReady.variableId) {
          this.globals.selectedVariablesValues.push({variable: selectendVariable, values: variableReady.values});
        }
      }
    }


    console.log(this.globals.selectedVariablesValues);

  }


}
