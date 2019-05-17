import {RouterModule, Routes} from '@angular/router';
import {HomePageComponent} from './home-page/home-page.component';
import {LoginPageComponent} from './login-page/login-page.component';
import {RegistrationPageComponent} from './registration-page/registration-page.component';
import {ServerPageComponent} from './server-page/server-page.component';
import {ServerDataComponent} from './server-data-page/server-data.component';
import {ChartPageComponent} from './chart-page/chart-page.component';


const routes: Routes = [
  {path: 'home', component: HomePageComponent},
  {path: 'login', component: LoginPageComponent},
  {path: 'registration', component: RegistrationPageComponent},
  {path: 'servers', component: ServerPageComponent},
  {path: 'servers/data', component: ServerDataComponent},
  {path: 'servers/data/chart', component: ChartPageComponent},
  {path: '', component: HomePageComponent},

  {path: '**', component: HomePageComponent}
];

export const routing = RouterModule.forRoot(routes) ;

