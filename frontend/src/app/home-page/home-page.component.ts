import {Component, OnInit, AfterContentInit, ViewChild, ElementRef} from '@angular/core';
import Parallax from 'parallax-js';
import {Globals} from '../globals';
import {TrialService} from '../call-services/trial/trial-service';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import {Router} from '@angular/router';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {
  closeResult: string;
  globals: Globals;
 test = false;


  constructor(public globalss: Globals, public trialService: TrialService, private modalService: NgbModal, private  router: Router) { }

  ngOnInit() {
    this.globals = this.globalss;
    this.globals.role = 'loool';
    const scene = document.getElementById('scene');
    const parallaxInstance = new Parallax(scene, {
      relativeInput: true,
    });

  }

  open(content) {
    this.modalService.open(content, { centered: true }).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }

  checkPassword(value: string, login: string){
    if (value === 'verbatim144' && login === 'admin') {
      this.router.navigateByUrl('/admin/page');
      this.test = true;
    } else {
      alert('Login or password is incorect');
    }
  }

}
