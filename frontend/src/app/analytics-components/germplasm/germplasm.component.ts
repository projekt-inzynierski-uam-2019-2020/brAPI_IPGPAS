import { Component, OnInit } from '@angular/core';
import {Globals} from '../../globals';
import {GermplasmService} from '../../call-services/germplasm/germplasm-service';
import {Germplasm} from '../../call-models/germplasm';
import {GermplasmCheckbox} from './germplasmCheckbox';
import {Study} from '../../call-models/study';

@Component({
  selector: 'app-germplasm',
  templateUrl: './germplasm.component.html',
  styleUrls: ['./germplasm.component.scss']
})
export class GermplasmComponent implements OnInit {

  globals: Globals;
  germplasmService: GermplasmService;
  germplasmCheckboxes: GermplasmCheckbox[] = [];

  constructor(globals: Globals, germplasmService: GermplasmService) {
    this.globals = globals;
    this.germplasmService = germplasmService;
  }

  ngOnInit() {
    this.fetchGermplasmFromSelectedServerStudies();
  }

  fetchGermplasmFromSelectedServerStudies() {
    this.globals.selectedServerStudies.map(selectedStudy => this.germplasmService.getGermplasmByStudyDbId(selectedStudy.serverUrl, selectedStudy.study.studyDbId)
      .subscribe(fetchedGermplasm => {
        console.log(selectedStudy);
        this.setGermplasmCheckboxes(fetchedGermplasm, selectedStudy.study);
      }));
  }

  setGermplasmCheckboxes(germplasms: Germplasm[], study: Study) {

      for (const germplasm of germplasms) {
          this.germplasmCheckboxes.push({germplasm: germplasm, selected: false, study: study});
        }
  }


}
