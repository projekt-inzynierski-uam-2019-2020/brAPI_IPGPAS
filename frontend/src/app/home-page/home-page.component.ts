import { Component, OnInit, AfterContentInit } from '@angular/core';
import Parallax from 'parallax-js';
import {Globals} from '../globals';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {

  globals: Globals;
  constructor(public globalss: Globals) { }

  ngOnInit() {
    this.globals = this.globalss;
    this.globals.role = 'loool';
    const scene = document.getElementById('scene');
    const parallaxInstance = new Parallax(scene, {
      relativeInput: true,
    });
  }

}
