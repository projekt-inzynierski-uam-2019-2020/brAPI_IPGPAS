import {RouterModule, Routes} from '@angular/router';
import {HomePageComponent} from './home-page/home-page.component';
import {DatabasesPageComponent} from './databases-page/databases-page.component';
import {ServerPageComponent} from './server-page/server-page.component';
import {ServerStatusPageComponent} from './server-status-page/server-status-page.component';
import {AdminMainPageComponent} from './admin-main-page/admin-main-page.component';
import {TrialComponent} from './analytics-components/trial/trial.component';
import {TrialsResolve} from './call-services/trial/trials-resolve.service';
import {VariablesComponent} from './analytics-components/variables/variables.component';
import {AuthGuard} from './services/auth/auth.guard';


const routes: Routes = [
  {path: 'home', component: HomePageComponent},
  {path: 'servers', component: ServerPageComponent},
  {path: 'databases', component: DatabasesPageComponent},
  {path: 'admin/page', component: AdminMainPageComponent, canActivate: [AuthGuard]},
  {path: 'servers/trial', component: TrialComponent, resolve: {dataResolve: TrialsResolve}},
  {path: 'servers/variable', component: VariablesComponent},
  {path: 'servers/status', component: ServerStatusPageComponent},
  {path: '', component: HomePageComponent},

  {path: '**', component: HomePageComponent}
];

export const routing = RouterModule.forRoot(routes) ;
