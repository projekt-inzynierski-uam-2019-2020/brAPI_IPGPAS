import { Component, OnInit } from '@angular/core';
import {Globals} from '../../globals';
import {GermplasmService} from '../../call-services/germplasm/germplasm-service';

@Component({
  selector: 'app-germplasm',
  templateUrl: './germplasm.component.html',
  styleUrls: ['./germplasm.component.scss']
})
export class GermplasmComponent implements OnInit {

  globals: Globals;
  germplasmService: GermplasmService;

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
      }));
  }

}
