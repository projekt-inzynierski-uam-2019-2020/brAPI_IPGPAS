import { Component, OnInit } from '@angular/core';
import {Globals} from '../../globals';
import {GermplasmService} from '../../call-services/germplasm/germplasm-service';
import {Variable} from '../../call-models/variable';
import {ServerStudyVariables} from './serverStudyVariables';

@Component({
  selector: 'app-variables',
  templateUrl: './variables.component.html',
  styleUrls: ['./variables.component.scss']
})
export class VariablesComponent implements OnInit {

  globals: Globals;
  germplasmService: GermplasmService;
  studiesCsv: string[] = [];
  studiesCsvHeaders: string[] = [];
  variables: Variable[] = [];
  serverStudyVariables: ServerStudyVariables[] = [];


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
          for (let i = 15; i < splittedValueRow.length; i++){
            variable.variableValues.push(splittedValueRow[i]);
            console.log(splittedValueRow[i]);

          }
          this.variables.push(variable);
        }
        this.globals.variables = this.variables;
        this.variables.map( variable => this.serverStudyVariables.push({study: selectedStudy.study, serverUrl: selectedStudy.serverUrl, variable: variable}));

        console.log(this.globals.variables);
      }));

  }






}
