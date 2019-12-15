import { Component, OnInit } from '@angular/core';
import {Globals} from '../../globals';
import {GermplasmService} from '../../call-services/germplasm/germplasm-service';
import {VariablesTable} from '../../call-models/response/VariablesTable';

@Component({
  selector: 'app-variables',
  templateUrl: './variables.component.html',
  styleUrls: ['./variables.component.scss']
})
export class VariablesComponent implements OnInit {

  globals: Globals;
  germplasmService: GermplasmService;
  parsedCsv: string[][];


  constructor(globals: Globals, germplasmService: GermplasmService) {
    this.globals = globals;
    this.germplasmService = germplasmService;
  }


  ngOnInit() {
    this.fetchVariablesByStudyDbId();
  }

  fetchVariablesByStudyDbId() {
    const csv = [];
    const csvSeparator = ';';

    this.globals.selectedGermplasms.map(selectedStudy => this.germplasmService.getVariablesByStudyDbId(selectedStudy.serverUrl, selectedStudy.study.studyDbId)
      .subscribe(fetchedVariables => {
       const lines = fetchedVariables.split('\n');
        lines.forEach(element => {
          const cols: string[] = element.split(csvSeparator);
          csv.push(cols);
        });
        this.parsedCsv = csv;
        console.log(this.parsedCsv);
        console.log(this.parsedCsv[1][0]);
        console.log(this.parsedCsv[1][0]);
        const splitted = this.parsedCsv[1][0].split(',', this.parsedCsv[1][0].length);
        console.log(splitted);
        console.log(splitted[0]);

}
