import {Component, OnInit, AfterContentInit, ViewChild, ElementRef} from '@angular/core';
import Parallax from 'parallax-js';
import {Globals} from '../globals';
import {TrialService} from '../call-services/trial/trial-service';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import {Router} from '@angular/router';
import {AuthService} from '../services/auth/auth.service';
import {first} from 'rxjs/operators';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {
  closeResult: string;
  globals: Globals;
 test = false;
 result = false;
  public email: string;
  public password: string;
  public error: string;



  constructor(public globalss: Globals, public trialService: TrialService, private modalService: NgbModal, private  router: Router, private auth: AuthService) { }

  ngOnInit() {
    this.globals = this.globalss;
    this.globals.role = 'loool';
    const scene = document.getElementById('scene');
    const parallaxInstance = new Parallax(scene, {
      relativeInput: true,
    });

  }

  public submit() {
    this.auth.login(this.email, this.password)
      .pipe(first())
      .subscribe(

        result => { this.router.navigate(['admin/page']), this.result = true} ,
        err => this.error = 'Could not authenticate'
      );
    setTimeout(() => {
    if (this.result === true) {
     this.test = true;
    }
  }, 100);
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
