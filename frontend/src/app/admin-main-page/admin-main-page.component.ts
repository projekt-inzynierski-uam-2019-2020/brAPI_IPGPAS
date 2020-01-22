import {Component, Inject, OnInit, VERSION, ViewChild} from '@angular/core';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {catchError, debounceTime} from 'rxjs/operators';
import {of, Subject} from 'rxjs';
import {ServersFetchingService} from '../services/servers-service/servers-fetching.service';
import {Server} from '../services/servers-service/servers';
import {Router} from '@angular/router';
import * as http from 'http';
import {HttpClient} from '@angular/common/http';
import {FormControl, FormGroup, Validators} from '@angular/forms';


@Component({
  selector: 'app-admin-main-page',
  templateUrl: './admin-main-page.component.html',
  styleUrls: ['./admin-main-page.component.scss']
})
export class AdminMainPageComponent implements OnInit {
  private _success = new Subject<string>();
  closeResult: string;
  staticAlertClosed = false;
  successMessage: string;
  servers: Server[];
  server: Server = new Server();
  id: string;
  name: string;
  ipAddress: string;
  description: string;
  personalEmail: 'SDF';
  existServer = false;
  errors = '';
  createServerr = false;
  form2: FormGroup;

  private headerDict = {
    'Content-Type': 'application/json',
    'Accept': 'application/json',
    'Access-Control-Allow-Headers': 'Content-Type',
  };

  private requestOptions = {
    headers: new Headers(this.headerDict),
  };

  ngOnInit(): void {

    setTimeout(() => this.staticAlertClosed = true, 20000);
    this._success.subscribe((message) => this.successMessage = message);
    this._success.pipe(
      debounceTime(5000)
    ).subscribe(() => this.successMessage = null);
    this.getServers();

    this.form2 = new FormGroup({
      'name': new FormControl(this.name, [
        Validators.required
      ]),
      'ipAddress': new FormControl(this.ipAddress, [
        Validators.required
      ]),
      'description': new FormControl(this.description, [
        Validators.required
      ])
    });
  }

  getServers() {
    this.serverService.getAllServers()
      .subscribe(data => {
        this.servers = data;
      });
  }

  createServer(): void {
    this.http.get(`${this.server.ipAddress + '/brapi/v1/trials'}`, {observe: 'response'})
      .subscribe(response => {
          if (response.status === 200) {
            this.serverService.createServer(this.server)
              .subscribe(data => {
                this.getServers();
                this.createServerr = true;
                setTimeout(() => {
                  this.refresh();
                }, 1000);
              });
          }
        },
        error => {
          this.errors = error;
          document.getElementById('incorrectAddress').innerHTML = 'Server link is incorrect.';
        });
  }

  refresh(): void {
    window.location.reload();
  }

  updateServer(server: Server): void {
    this.http.get(`${this.server.ipAddress + '/brapi/v1/trials'}`, {observe: 'response'})
      .subscribe(response => {
        if (response.status === 200) {
          this.serverService.updateServer(this.server, this.id)
            .subscribe(data => {
              this.servers = this.servers.filter(u => u !== server);
              setTimeout(() => {
                this.refresh();
              }, 1000);
            });
        }
      }, error => {
        this.errors = error;
        document.getElementById('incorrectAddress2').innerHTML = 'Server link is incorrect.';
      });
  }


  updateInfo(server: Server) {
    this.id = server._id;
    this.name = server.name;
    this.ipAddress = server.ipAddress;
    this.description = server.description;

  }

  changeVanityId(event) {
    this.server._id = event;
  }

  deleteUser(server: Server): void {
    this.serverService.deleteServer(server._id)
      .subscribe(data => {
        this.servers = this.servers.filter(u => u !== server);
      });
    setTimeout(() => {
      this.refresh();
    }, 1000);

  }

  public changeSuccessMessage() {
    this._success.next(`${new Date()} - The server has been added`);
  }

  constructor(private modalService: NgbModal, private serverService: ServersFetchingService, private http: HttpClient) {

  }

  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
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
      return `with: ${reason}`;
    }
  }
}
