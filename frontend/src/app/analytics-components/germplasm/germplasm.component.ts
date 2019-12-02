import { Component, OnInit } from '@angular/core';
import {Globals} from '../../globals';
import {GermplasmService} from '../../call-services/germplasm/germplasm-service';
import {Germplasm} from '../../call-models/germplasm';
import {GermplasmCheckbox} from './germplasmCheckbox';

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
    console.log(this.globals.selectedServerStudies);
    this.globals.selectedServerStudies.map(selectedStudy => this.germplasmService.getGermplasmByStudyDbId(selectedStudy.serverUrl, selectedStudy.study.studyDbId)
      .subscribe(fetchedGermplasm => {
        console.log(fetchedGermplasm);
        this.setGermplasmCheckboxes(fetchedGermplasm);
      }));
  }

  setGermplasmCheckboxes(germplasms: Germplasm[]) {

    console.log(this.globals.selectedServerStudies);
    for (const selectedServer of this.globals.selectedServerStudies){
      for (const germplasm of germplasms) {
        if (!this.germplasmCheckboxes.some((item) => item.germplasm.germplasmDbId === germplasm.germplasmDbId && item.study.studyName === selectedServer.study.studyName)) {
          this.germplasmCheckboxes.push({germplasm: germplasm, selected: false, study: selectedServer.study});
        }
      }
    }

    console.log(this.germplasmCheckboxes);
  }


}
