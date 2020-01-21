import {Component, OnInit} from '@angular/core';
import {Globals} from '../../globals';
import {Study} from '../../call-models/study';
import {StudyRow} from './studyRow';
import {StudyService} from '../../call-services/study/study-service';
import {ServerStudy} from './serverStudy';
import {Router} from '@angular/router';
import {Season} from './season';
import {Location} from './location';

@Component({
  selector: 'app-study',
  templateUrl: './study.component.html',
  styleUrls: ['./study.component.scss']
})
export class StudyComponent implements OnInit {
  globals: Globals;
  studyService: StudyService;

  studyRows: StudyRow[] = [];
  filteredStudyRows: StudyRow[] = [];
  seasons: Season[] = [];
  locations: Location[] = [];
  serverStudies: ServerStudy[] = [];

  isLoading = true;
  filtersShow = false;
  isChecked = false;


  constructor(globals: Globals, studyService: StudyService, private router: Router) {
    this.globals = globals;
    this.studyService = studyService;
  }

  ngOnInit() {
    this.fetchStudiesFromSelectedServerTrials();
  }

  fetchStudiesFromSelectedServerTrials() {
    let loadingCounter = 0;
    this.globals.selectedServerTrials.map(selectedTrial => this.studyService.getStudyByTrialDbId(selectedTrial.serverUrl, selectedTrial.trial.trialDbId)
      .subscribe(fetchedStudies => {
        if (fetchedStudies === null) {
          alert('Server ' + selectedTrial.serverUrl + 'not respond')
          loadingCounter = loadingCounter + 1;
        } else {
          if (fetchedStudies === undefined) {
            loadingCounter = loadingCounter + 1;
          } else {
            this.setStudyRows(fetchedStudies);
            this.setServerStudies(selectedTrial.serverUrl, fetchedStudies);
            this.setFilterSeasons(fetchedStudies);
            this.setFilterLocations(fetchedStudies);
            loadingCounter = loadingCounter + 1;
          }
        }
        if (loadingCounter === this.globals.selectedServerTrials.length) {
          this.isLoading = false;
        }
      }));

  }

  setStudyRows(studies: Study[]) {
    studies.map(study => this.studyRows.push({study: study, selected: false}));
    studies.map(study => this.filteredStudyRows.push({study: study, selected: false}));
  }

  setServerStudies(serverUrl: string, studies: Study[]) {
    studies.map(study => this.serverStudies.push({serverUrl: serverUrl, study: study}));

  }

  setFilterSeasons(studies: Study[]) {
    for (const study of studies) {
      for (const season of study.seasons) {
        if (!this.seasons.some(item => item.year === season.year)) {
          if (season.year) {
            this.seasons.push({year: season.year, selected: false});
          }
        }
      }
    }
    console.log(this.seasons);
  }

  setFilterLocations(studies: Study[]) {
    for (const study of studies) {
      if (!this.locations.some(item => item.location === study.locationName)) {
        if (study.locationName) {
          this.locations.push({location: study.locationName, selected: false});
        }
      }
    }
  }

  applyFilters() {
    const selectedSeasons = this.seasons
      .filter(seasonCheckbox => seasonCheckbox.selected)
      .map(seasonCheckbox => seasonCheckbox.year);

    const selectedLocations = this.locations
      .filter(locationCheckbox => locationCheckbox.selected)
      .map(locationCheckbox => locationCheckbox.location);

    if (selectedSeasons.length === 0 && selectedLocations.length === 0) {
      this.filteredStudyRows = this.studyRows;
    } else {
      if (selectedSeasons.length === 0 && selectedLocations.length !== 0) {
        this.filteredStudyRows = this.studyRows;
        this.filteredStudyRows = this.filteredStudyRows
          .filter(studyRow => {
            for (const location of selectedLocations) {
              if (location === studyRow.study.locationName) {
                return true;
              }
            }
          });
      } else {
        if (selectedSeasons.length !== 0 && selectedLocations.length === 0) {
          this.filteredStudyRows = this.studyRows;
          this.filteredStudyRows = this.studyRows
            .filter(studyRow => {
              for (const season of selectedSeasons) {
                for (const seasonRowSeason of studyRow.study.seasons) {
                  if (season === seasonRowSeason.year) {
                    return true;
                  }
                }
              }
            });
        } else {
          this.filteredStudyRows = this.studyRows;
          this.filteredStudyRows = this.studyRows
            .filter(studyRow => {
              for (const location of selectedLocations) {
                if (location === studyRow.study.locationName) {
                  for (const season of selectedSeasons) {
                    for (const seasonRowSeason of studyRow.study.seasons) {
                      if (season === seasonRowSeason.year) {
                        return true;
                      }
                    }
                }
              }
              }
            });
        }
      }
    }


  }

  filterStudyRowsBySeasons() {
    const selectedSeasons = this.seasons
      .filter(seasonCheckbox => seasonCheckbox.selected)
      .map(seasonCheckbox => seasonCheckbox.year);

    if (selectedSeasons.length === 0) {
      this.filteredStudyRows = this.studyRows;
    } else {
      this.filteredStudyRows = this.studyRows
        .filter(studyRow => {
          for (const season of selectedSeasons) {
            for (const seasonRowSeason of studyRow.study.seasons) {
              if (season === seasonRowSeason.year) {
                return true;
              }
            }
          }
        });
    }
  }

  setSelectedServerStudiesFromSelectedFilteredRows() {
    const selectedStudyRows = this.filteredStudyRows
      .filter(studyRow => studyRow.selected)
      .map(studyRow => studyRow.study);


    this.globals.selectedServerStudies = this.serverStudies
      .filter(selectedStudy => {
        for (const selectedStudyRow of selectedStudyRows) {
          if (Object.is(selectedStudyRow, selectedStudy.study)) {
            return true;
          }
        }
      });
    console.log(this.globals.selectedServerStudies);
    this.globals.selectedServerStudies.length > 0 ? this.router.navigate(['/servers/germplasm']) : alert('You have to select study first.');
  }

  checkValue(event: any) {
    if (event === 'checked') {
      for (const trialBox of this.filteredStudyRows) {
        trialBox.selected = true;
      }
    } else {
      for (const trialBox of this.filteredStudyRows) {
        trialBox.selected = false;
      }
    }
  }

}
