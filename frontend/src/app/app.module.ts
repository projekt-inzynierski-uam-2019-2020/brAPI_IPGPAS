import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import {routing} from './app-routing';
import { HomePageComponent } from './home-page/home-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import {CommonModule} from '@angular/common';
import { RegistrationPageComponent } from './registration-page/registration-page.component';
import { ServerPageComponent } from './server-page/server-page.component';
import { ServerDataComponent } from './server-data-page/server-data.component';
import {HttpClientModule} from '@angular/common/http';
import { ChartPageComponent } from './chart-page/chart-page.component';

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
    ChartPageComponent
  ],
  imports: [
    HttpClientModule,
    CommonModule,
    routing,
    BrowserModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
