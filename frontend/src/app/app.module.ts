import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {NavbarComponent} from './navbar/navbar.component';
import {SidebarComponent} from './sidebar/sidebar.component';
import {HomePageComponent} from './home-page/home-page.component';
import {CommonModule} from '@angular/common';
import {DatabasesPageComponent} from './databases-page/databases-page.component';
import {ServerPageComponent} from './server-page/server-page.component';
import {HttpClientModule} from '@angular/common/http';
import {ChartsModule} from 'ng2-charts-x';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AdminMainPageComponent} from './admin-main-page/admin-main-page.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {ServersFetchingService} from './services/servers-service/servers-fetching.service';
import {Globals} from './globals';
import {TrialService} from './call-services/trial/trial-service';
import {TrialComponent} from './analytics-components/trial/trial.component';
import {ServersService} from './servers-services/servers-service';
import {FetchBrapiResultService} from './fetch-services/fetch-brapi-result-service';
import {RouterModule} from '@angular/router';
import {TrialsResolve} from './call-services/trial/trials-resolve.service';
import {StudyComponent} from './analytics-components/study/study.component';
import {StudyService} from './call-services/study/study-service';
import {GermplasmComponent} from './analytics-components/germplasm/germplasm.component';
import {GermplasmService} from './call-services/germplasm/germplasm-service';
import {VariablesComponent} from './analytics-components/variables/variables.component';
import { JwtModule } from '@auth0/angular-jwt';

import {StatisticsComponent} from './analytics-components/statistics/statistics.component';
import {ChartService} from './services/chart-service/chart-service';

import {LoadingSpinnerComponent} from './loading-spinner/loading-spinner.component';
import {AuthGuard} from './servers-services/auth.guard';
import {AuthService} from './servers-services/auth.service';



export function tokenGetter() {
  return localStorage.getItem('access_token');
}

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    SidebarComponent,
    HomePageComponent,
    ServerPageComponent,
    DatabasesPageComponent,
    StudyComponent,
    AdminMainPageComponent,
    TrialComponent,
    GermplasmComponent,
    VariablesComponent,
    StatisticsComponent,
    LoadingSpinnerComponent
  ],
  imports: [
    HttpClientModule,
    CommonModule,
    RouterModule.forRoot([{path: 'home', component: HomePageComponent},
      {path: 'servers', component: ServerPageComponent},
      {path: 'databases', component: DatabasesPageComponent},
      {path: 'admin/page', component: AdminMainPageComponent, canActivate: [AuthGuard]},
      {path: 'servers/trial', component: TrialComponent, resolve: TrialsResolve},
      {path: 'servers/study', component: StudyComponent},
      {path: 'servers/germplasm', component: GermplasmComponent},
      {path: 'servers/variables', component: VariablesComponent},
      {path: 'servers/statistics', component: StatisticsComponent},
      {path: '', component: HomePageComponent},


      {path: '**', component: HomePageComponent}]),
    BrowserModule,
    ChartsModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule,
    NgbModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        whitelistedDomains: ['localhost:3000'],
        blacklistedRoutes: ['localhost:3000/users/authenticate']
      }
    })
  ],

  providers: [
    AuthService,
    AuthGuard,
    ServersFetchingService,
    ServerPageComponent,
    Globals,
    TrialService,
    ServersService,
    TrialsResolve,
    FetchBrapiResultService,
    TrialsResolve,
    StudyService,
    GermplasmService,
    ChartService],

  bootstrap: [AppComponent],

})
export class AppModule {
}
