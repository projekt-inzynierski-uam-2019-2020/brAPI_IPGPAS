import {RouterModule, Routes} from '@angular/router';
import {HomePageComponent} from './home-page/home-page.component';
import {LoginPageComponent} from './login-page/login-page.component';


const routes: Routes = [
  {path: 'home', component: HomePageComponent},
  {path: 'login', component: LoginPageComponent},
  {path: '', component: HomePageComponent},

  {path: '**', component: HomePageComponent}
];

export const routing = RouterModule.forRoot(routes) ;

