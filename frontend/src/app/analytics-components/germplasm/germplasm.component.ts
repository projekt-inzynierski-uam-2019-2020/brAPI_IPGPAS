import {Component, OnInit} from '@angular/core';
import {Globals} from '../../globals';
import {GermplasmService} from '../../call-services/germplasm/germplasm-service';
import {Germplasm} from '../../call-models/germplasm';
import {GermplasmCheckboxes} from './germplasmCheckbox';
import {Study} from '../../call-models/study';
import {StudyColumn} from './StudyColumn';
import {StudyCheckboxes} from './studyCheckboxes';
import {StudySelected} from './studySelected';
import {ServerStudy} from './serverStudy';

@Component({
  selector: 'app-germplasm',
  templateUrl: './germplasm.component.html',
  styleUrls: ['./germplasm.component.scss']
})
export class GermplasmComponent implements OnInit {

  globals: Globals;
  germplasmService: GermplasmService;
  germplasmCheckboxes: GermplasmCheckboxes[] = [];
  studyColumns: StudyColumn[] = [];
  studyIdColumns = [];
  germplasm: Germplasm[] = [];
  studySelectedId: StudyCheckboxes[] = [];
  test: StudySelected[] = [];
  serverStudy: ServerStudy[] = [];

  isLoading = true;

  constructor(globals: Globals, germplasmService: GermplasmService) {
    this.globals = globals;
    this.germplasmService = germplasmService;
  }

  ngOnInit() {
    this.fetchGermplasmFromSelectedServerStudies();
  }

  fetchGermplasmFromSelectedServerStudies() {
    let loadingCounter = 0;
    this.globals.selectedServerStudies.map(selectedStudy => this.germplasmService.getGermplasmByStudyDbId(selectedStudy.serverUrl, selectedStudy.study.studyDbId)
      .subscribe(fetchedGermplasm => {
        this.setGermplasmCheckboxes(fetchedGermplasm, selectedStudy.study, selectedStudy.serverUrl) ;
        loadingCounter = loadingCounter + 1;
        if (loadingCounter === this.globals.selectedServerStudies.length) {
          this.isLoading = false;
        }
      }));
  }

  setGermplasmCheckboxes(germplasms: Germplasm[], study: Study, serverUrl: string) {
    this.serverStudy.push({serverUrl: serverUrl, study: study});
    this.studyColumns.push({germplasms: germplasms, study: study, selected: false});

    console.log(this.studyColumns);

    for (const germplasm of germplasms) {
      this.germplasmCheckboxes.push({germplasm: germplasm, selected: false, study: study});

      if (!this.germplasm.some((item) => item.germplasmDbId === germplasm.germplasmDbId)) {
        this.germplasm.push(germplasm);
      }
    }
  }

  checkGermplasmFunction(studyColumn: StudyColumn, germplasm: Germplasm) {
    for (const germplasmFromStudy of studyColumn.germplasms) {
      if (germplasm.germplasmName === germplasmFromStudy.germplasmName) {
        this.studySelectedId.push({studyName: studyColumn.study.studyName, studyDbId: studyColumn.study.studyDbId});
        return true;
      }
    }
  }

  setSelectedGermplasms() {
    const selectedStudies = this.studyColumns.filter(studyColumns => studyColumns.selected).map(studyColumns => studyColumns.study);
      this.globals.selectedStudiesDbId = this.serverStudy
        .filter(serverStudy => {
          for (const selectedStudy of selectedStudies) {
            if (Object.is(serverStudy.study, selectedStudy)) {
              return true;
            }
        }
    });

  }


}
