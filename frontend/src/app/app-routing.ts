import {RouterModule, Routes} from '@angular/router';
import {HomePageComponent} from './home-page/home-page.component';
import {DatabasesPageComponent} from './databases-page/databases-page.component';
import {ServerPageComponent} from './server-page/server-page.component';
import {AdminMainPageComponent} from './admin-main-page/admin-main-page.component';
import {TrialComponent} from './analytics-components/trial/trial.component';
import {TrialsResolve} from './call-services/trial/trials-resolve.service';
import {VariablesComponent} from './analytics-components/variables/variables.component';
import {ServerStatusComponent} from './server-status/server-status.component';


const routes: Routes = [
  {path: 'home', component: HomePageComponent},
  {path: 'servers', component: ServerPageComponent},
  {path: 'databases', component: DatabasesPageComponent},
  {path: 'admin/page', component: AdminMainPageComponent},
  {path: 'servers/trial', component: TrialComponent, resolve: {dataResolve: TrialsResolve}},
  {path: 'servers/variable', component: VariablesComponent},
  {path: 'status', component: ServerStatusComponent},
  {path: '', component: HomePageComponent},

  {path: '**', component: HomePageComponent}
];

export const routing = RouterModule.forRoot(routes) ;
