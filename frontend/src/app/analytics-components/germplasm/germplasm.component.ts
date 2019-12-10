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
    this.globals.selectedServerStudies.map(selectedStudy => this.germplasmService.getGermplasmByStudyDbId(selectedStudy.serverUrl, selectedStudy.study.studyDbId)
      .subscribe(fetchedGermplasm => {
        console.log(fetchedGermplasm);
        this.setGermplasmCheckboxes(fetchedGermplasm, selectedStudy.study, selectedStudy.serverUrl) ;
        if (selectedStudy === this.globals.selectedServerStudies[length]) {
          this.isLoading = false;
        }
      }));
  }

  setGermplasmCheckboxes(germplasms: Germplasm[], study: Study, serverUrl: string) {

    this.serverStudy.push({serverUrl: serverUrl, study: study});
    this.studyColumns.push({germplasm: germplasms, study: study, selected: false});

    for (const germplasm of germplasms) {
      this.germplasmCheckboxes.push({germplasm: germplasm, selected: false, study: study});

      if (!this.germplasm.some((item) => item.germplasmDbId === germplasm.germplasmDbId)) {
        this.germplasm.push(germplasm);
      }
    }
  }

  checkGermplasmFunction(studyColumn: StudyColumn, germplasm: Germplasm) {
    for (const germplasmFroStudy of studyColumn.germplasm) {
      if (germplasm.germplasmName === germplasmFroStudy.germplasmName) {
        this.studySelectedId.push({studyName: studyColumn.study.studyName, studyDbId: studyColumn.study.studyDbId});
        return true;
      }
    }


  }

  setSelectedGermplasms() {
    const selectedStudies = this.studyColumns.filter(studyColumns => studyColumns.selected).map(studyColumns => studyColumns.study);

      this.globals.selectedGermplasms = this.serverStudy
        .filter(serverStudy => {
          for (const selectedStudy of selectedStudies) {
            if (Object.is(serverStudy.study, selectedStudy)) {
              return true;
            }
        }
    });

    console.log(this.globals.selectedGermplasms);
  }


}
