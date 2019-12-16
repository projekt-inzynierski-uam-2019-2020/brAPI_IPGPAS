import {RouterModule, Routes} from '@angular/router';
import {HomePageComponent} from './home-page/home-page.component';
import {DatabasesPageComponent} from './databases-page/databases-page.component';
import {ServerDataComponent} from './server-data-page/server-data.component';
import {ServerPageComponent} from './server-page/server-page.component';
import {StudyComponent} from './calls-components/study/study.component';
import {TrialsComponent} from './calls-components/trials/trials.component';
import {SeasonsComponent} from './calls-components/seasons/seasons.component';
import {AdminMainPageComponent} from './admin-main-page/admin-main-page.component';
import {TrialComponent} from './analytics-components/trial/trial.component';
import {TrialsResolve} from './call-services/trial/trials-resolve.service';
import {VariablesComponent} from './analytics-components/variables/variables.component';



const routes: Routes = [
  {path: 'home', component: HomePageComponent},
  {path: 'servers', component: ServerPageComponent},
  {path: 'servers/data', component: ServerDataComponent},
  {path: 'databases', component: DatabasesPageComponent},
 //  {path: 'servers/study', component: StudyComponent},
  {path: 'servers/trials', component: TrialsComponent},
  {path: 'servers/seasons', component: SeasonsComponent},
  {path: 'admin/page', component: AdminMainPageComponent},
  {path: 'servers/trial', component: TrialComponent, resolve: {dataResolve: TrialsResolve}},
  {path: 'servers/study', component: StudyComponent},
  {path: 'servers/variable', component: VariablesComponent},
  {path: '', component: HomePageComponent},

  {path: '**', component: HomePageComponent}
];

export const routing = RouterModule.forRoot(routes) ;
