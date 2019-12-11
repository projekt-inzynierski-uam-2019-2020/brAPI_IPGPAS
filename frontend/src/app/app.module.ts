import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {NavbarComponent} from './navbar/navbar.component';
import {SidebarComponent} from './sidebar/sidebar.component';
import {routing} from './app-routing';
import {HomePageComponent} from './home-page/home-page.component';
import {CommonModule} from '@angular/common';
import {DatabasesPageComponent} from './databases-page/databases-page.component';
import {ServerDataComponent} from './server-data-page/server-data.component';
import {ServerPageComponent} from './server-page/server-page.component';
import {HttpClientModule} from '@angular/common/http';
import {TrialsComponent} from './calls-components/trials/trials.component';
import {Servers} from './calls/server';
import {SeasonsComponent} from './calls-components/seasons/seasons.component';
import {ChartsModule} from 'ng2-charts-x';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AdminMainPageComponent} from './admin-main-page/admin-main-page.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {ServersFetchingService} from './services/servers-service/servers-fetching.service';
import {Globals} from './globals';
import {TrialService} from './call-services/trial/trial-service';
import { TrialComponent } from './analytics-components/trial/trial.component';
import {ServersService} from './servers-services/servers-service';
import {FetchBrapiResultService} from './fetch-services/fetch-brapi-result-service';
import {RouterModule} from '@angular/router';
import {TrialsResolve} from './call-services/trial/trials-resolve.service';
import {StudyComponent} from './analytics-components/study/study.component';
import {StudyService} from './call-services/study/study-service';
import { GermplasmComponent } from './analytics-components/germplasm/germplasm.component';
import {GermplasmService} from './call-services/germplasm/germplasm-service';
import { VariablesComponent } from './analytics-components/variables/variables.component';
import { LoadingSpinnerComponent } from './loading-spinner/loading-spinner.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    SidebarComponent,
    HomePageComponent,
    ServerPageComponent,
    ServerDataComponent,
    DatabasesPageComponent,
    StudyComponent,
    TrialsComponent,
    SeasonsComponent,
    AdminMainPageComponent,
    TrialsComponent,
    TrialComponent,
    GermplasmComponent,
    VariablesComponent,
    LoadingSpinnerComponent
  ],
  imports: [
    HttpClientModule,
    CommonModule,
    RouterModule.forRoot([ {path: 'home', component: HomePageComponent},
      {path: 'servers', component: ServerPageComponent},
      {path: 'servers/data', component: ServerDataComponent},
      {path: 'databases', component: DatabasesPageComponent},
      {path: 'servers/trials', component: TrialsComponent},
      {path: 'servers/seasons', component: SeasonsComponent},
      {path: 'admin/page', component: AdminMainPageComponent},
      {path: 'servers/trial', component: TrialComponent, resolve: TrialsResolve},
      {path: 'servers/study', component: StudyComponent},
      {path: 'servers/germplasm', component: GermplasmComponent},
      {path: 'servers/variables', component: VariablesComponent},
      {path: '', component: HomePageComponent},

      {path: '**', component: HomePageComponent}]),
    BrowserModule,
    ChartsModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule,
    NgbModule,
  ],
  // tslint:disable-next-line:max-line-length
  providers: [ServersFetchingService, Servers, ServerPageComponent, Globals, TrialService, ServersService, TrialsResolve, FetchBrapiResultService, TrialsResolve, StudyService, GermplasmService],
  bootstrap: [AppComponent],

})
export class AppModule {
}
