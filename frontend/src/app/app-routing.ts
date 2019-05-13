import {RouterModule, Routes} from '@angular/router';
import {LandingPageComponent} from './landing-page/landing-page.component';
import {NgModule} from '@angular/core';


const routes: Routes = [
  {path: '', redirectTo: '', pathMatch: 'full'},
  {path: 'home', component: LandingPageComponent},

  {path: '**', redirectTo: '/home'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export  class AppRouting {}

