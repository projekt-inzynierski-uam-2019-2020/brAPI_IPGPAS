import {Component, OnInit} from '@angular/core';
import {Globals} from '../../globals';
import {Study} from '../../call-models/study';
import {StudyCheckbox} from './studyCheckbox';
import {StudyService} from '../../call-services/study/study-service';
import {ServerStudy} from './serverStudy';
import {LocationStudy} from './locationStudy';
import {SeasonStudy} from './seasonStudy';
import {StudyForSeasons} from './studyForSeasons';
import {SeasonCheckbox} from './seasonCheckbox';
import index from '@angular/cli/lib/cli';
import {Router} from '@angular/router';

@Component({
  selector: 'app-study',
  templateUrl: './study.component.html',
  styleUrls: ['./study.component.scss']
})
export class StudyComponent implements OnInit {
  globals: Globals;
  studyCheckboxes: StudyCheckbox[] = [];
  studyLocationCheckboxes: StudyCheckbox[] = [];
  studySeasonCheckboxes: SeasonCheckbox[] = [];
  serverStudy: ServerStudy[] = [];
  locationStudy: LocationStudy[] = [];
  seasonStudy: SeasonStudy[] = [];
  studyForSeasons = [];

  studyService: StudyService;
  selectedLocationStudy: LocationStudy[] = [];
  selectedSeasonStudy: SeasonStudy[] = [];

  isShowAllStudies = true;
  isLocationFilterShow = false;
  isShowSeasons = false;

  locationFiltersShow = false;
  seasonFiltersShow = false;

  isLoading = true;
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
        this.setStudyCheckboxes(fetchedStudies);
        this.setServerStudies(selectedTrial.serverUrl, fetchedStudies);
        loadingCounter =  loadingCounter + 1;
        if (loadingCounter === this.globals.selectedServerTrials.length) {
          this.isLoading = false;
        }
      }));

  }

  setStudyCheckboxes(studies: Study[]) {
    studies.map(study => this.studyCheckboxes.push({study: study, selected: false}));
  }

  setServerStudies(serverUrl: string, studies: Study[]) {
    studies.map(study => this.serverStudy.push({serverUrl: serverUrl, study: study}));
    studies.map(study => this.studyForSeasons.push({study: study}));

    for (const study of this.serverStudy) {
      if (!this.locationStudy.some((item) => item.study.locationName === study.study.locationName)) {
        if (study.study.locationName) {
          this.locationStudy.push({locationName: study.study.locationName, study: study.study, serverUrl: study.serverUrl});
          this.studyLocationCheckboxes.push({study: study.study, selected: false});
        }
      }
    }

    for (const study of this.studyForSeasons) {
      if (study.study.seasons) {
        for (const season of study.study.seasons) {
          if (!this.seasonStudy.some((item) => item.study === study)) {
            this.seasonStudy.push({season: season, year: season.year, study: study, serverUrl: serverUrl});
            if (!this.studySeasonCheckboxes.some((item) => item.season.year === season.year)) {
              this.studySeasonCheckboxes.push({study: study, selected: false, season: season});
            }
          }
        }
      }
    }

  }

  setSelectedServerStudies() {
    const selectedStudies = this.studyCheckboxes.filter(studyCheckbox => studyCheckbox.selected).map(studyCheckbox => studyCheckbox.study);
    this.globals.selectedServerStudies = this.serverStudy
      .filter(serverStudy => {
        for (const selectedStudy of selectedStudies) {
          if (Object.is(serverStudy.study, selectedStudy)) {
            return true;
          }
        }
      });

    this.globals.selectedServerStudies.length > 0 ? this.router.navigate(['/servers/germplasm']) : alert('You have to select study first.');
  }

  setLocationStudy() {
    this.isShowAllStudies = true;
    this.isShowSeasons = false;
    this.isLocationFilterShow = false;
    this.studyCheckboxes = [];
    const selectedLocation = this.studyLocationCheckboxes.filter(studyLocationCheckboxes => studyLocationCheckboxes.selected).map(studyLocationCheckboxes => studyLocationCheckboxes.study);

    this.selectedLocationStudy = this.locationStudy
      .filter(location => {
        for (const locationStudyy of selectedLocation) {
          if (Object.is(location.study, locationStudyy)) {
            return true;
          }
        }
      });

    for (const study of this.serverStudy) {
      if (this.selectedLocationStudy.some((item) => item.locationName === study.study.locationName)) {
        if (!this.studyCheckboxes.some((item) => item.study === study.study)) {
          this.studyCheckboxes.push({study: study.study, selected: false});
        }
      }
    }
  }


  setSeasonStudy() {
    this.isShowAllStudies = true;
    this.isLocationFilterShow = false;
    this.studyCheckboxes = [];
    this.isShowSeasons = false;
    const selectedLocation = this.studySeasonCheckboxes.filter(studySeasonCheckboxes => studySeasonCheckboxes.selected).map(studySeasonCheckboxes => studySeasonCheckboxes.study);

    this.selectedSeasonStudy = this.seasonStudy
      .filter(location => {
        for (const locationStudyy of selectedLocation) {
          if (Object.is(location.study, locationStudyy)) {
            return true;
          }
        }
      });


    for (const study of this.studyForSeasons) {
      for (const season of study.study.seasons) {
        if (this.selectedSeasonStudy.some((item) => item.season === season)) {
            if (!this.studyCheckboxes.some((item) => item.study === study.study)) {
              this.studyCheckboxes.push({study: study.study, selected: false});
            }
          }

      }
    }
  }

  checkValue(event: any) {
    if (event === 'checked') {
      for (const trialBox of this.studyCheckboxes) {
        trialBox.selected = true;
      }
    } else {
      for (const trialBox of this.studyCheckboxes) {
        trialBox.selected = false;
      }
    }
  }


}
