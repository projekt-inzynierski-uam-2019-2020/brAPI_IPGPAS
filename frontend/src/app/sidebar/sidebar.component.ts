import { Component, OnInit } from '@angular/core';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import {Router} from '@angular/router';
import {first} from 'rxjs/operators';
import {AuthService} from '../services/auth/auth.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {
  closeResult: string;
  result = false;
  public email: string;
  public password: string;
  test = false;
  form: FormGroup;

  constructor(private modalService: NgbModal, private  router: Router, private auth: AuthService) {}

  ngOnInit() {
    this.form = new FormGroup({
      'email': new FormControl(this.email, [
        Validators.required
      ]),
      'password': new FormControl(this.password, [
        Validators.required
      ])
    });
  }

  getToken(): string {
    return localStorage.getItem('access_token');
  }

  open(content) {
    console.log(this.getToken());
    if (this.getToken() !== null) {
      this.router.navigate(['admin/page']); this.result = true;
    } else {
      console.log('nom')
      this.modalService.open(content, {centered: true}).result.then((result) => {
        this.closeResult = `Closed with: ${result}`;
      }, (reason) => {
        this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
      });
    }
  }

  public submit() {
    this.auth.login(this.email, this.password)
      .pipe(first())
      .subscribe(
        result => {
          this.router.navigate(['admin/page']), this.result = true;
        },
        err => document.getElementById('incorrectPassword').innerHTML = 'Email or password <br> is incorrect.'
      );
    setTimeout(() => {
      if (this.result === true) {
        this.test = true;
      }
    }, 10);
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

}
