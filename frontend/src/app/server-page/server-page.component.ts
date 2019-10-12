import {Component, OnInit} from '@angular/core';
import {Server} from '../calls/server';
import {FormArray, FormBuilder, FormControl, FormGroup} from '@angular/forms';


@Component({
  selector: 'app-server-page',
  templateUrl: './server-page.component.html',
  styleUrls: ['./server-page.component.scss']
})
export class ServerPageComponent {
  checkboxes = [
    {
      value: 'https://test-server.brapi.org/',
      selected: false
    },
    {
      value: 'http://35.242.244.53:8080',
      selected: false
    },
  ];


  constructor(private server: Server, private formBuilder: FormBuilder) {
  }

  public getSelected() {
    let result = this.checkboxes.filter((ch) => {
      return ch.selected;
    })
      .map((ch) => {
        return ch.value;
      });
    console.log(result);
  }

}
