import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {NavbarComponent} from './navbar/navbar.component';
import {SidebarComponent} from './sidebar/sidebar.component';
import {routing} from './app-routing';
import {HomePageComponent} from './home-page/home-page.component';
import {LoginPageComponent} from './login-page/login-page.component';
import {CommonModule} from '@angular/common';
import {RegistrationPageComponent} from './registration-page/registration-page.component';
import {DatabasesPageComponent} from './databases-page/databases-page.component';
import {ServerDataComponent} from './server-data-page/server-data.component';
import {ServerPageComponent} from './server-page/server-page.component';
import {HttpClientModule} from '@angular/common/http';
import {StudyComponent} from './calls-components/study/study.component';
import {TrialsComponent} from './calls-components/trials/trials.component';
import {Server} from './calls/server';
import {SeasonsComponent} from './calls-components/seasons/seasons.component';
import {ChartsModule} from 'ng2-charts-x';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AdminMainPageComponent} from './admin-main-page/admin-main-page.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    SidebarComponent,
    HomePageComponent,
    LoginPageComponent,
    RegistrationPageComponent,
    ServerPageComponent,
    ServerDataComponent,
    DatabasesPageComponent,
    StudyComponent,
    TrialsComponent,
    SeasonsComponent,
    AdminMainPageComponent,
  ],
  imports: [
    HttpClientModule,
    CommonModule,
    routing,
    BrowserModule,
    ChartsModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule,
    NgbModule,
  ],
  providers: [Server],
  bootstrap: [AppComponent],

})
export class AppModule {
}
