import {RouterModule, Routes} from '@angular/router';
import {HomePageComponent} from './home-page/home-page.component';
import {LoginPageComponent} from './login-page/login-page.component';
import {RegistrationPageComponent} from './registration-page/registration-page.component';
import {DatabasesPageComponent} from './databases-page/databases-page.component';


const routes: Routes = [
  {path: 'home', component: HomePageComponent},
  {path: 'login', component: LoginPageComponent},
  {path: 'registration', component: RegistrationPageComponent},
  {path: '', component: HomePageComponent},
  {path: 'databases', component: DatabasesPageComponent},

  {path: '**', component: HomePageComponent}
];

export const routing = RouterModule.forRoot(routes) ;
