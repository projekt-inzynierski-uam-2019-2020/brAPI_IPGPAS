import {Component, Inject, OnInit, VERSION} from '@angular/core';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {debounceTime} from 'rxjs/operators';
import {Subject} from 'rxjs';
import {ServersService} from '../services/servers-service/servers.service';
import {Server} from '../services/servers-service/servers';


@Component({
  selector: 'app-admin-main-page',
  templateUrl: './admin-main-page.component.html',
  styleUrls: ['./admin-main-page.component.scss']
})
export class AdminMainPageComponent implements OnInit{
  private _success = new Subject<string>();
  closeResult: string;
  staticAlertClosed = false;
  successMessage: string;
  server_name: string;
  server_link: string;
  servers: Server[];
  server: Server = new Server();


  ngOnInit(): void {
    setTimeout(() => this.staticAlertClosed = true, 20000);
    this._success.subscribe((message) => this.successMessage = message);
    this._success.pipe(
      debounceTime(5000)
    ).subscribe(() => this.successMessage = null);
    this.serverService.getAllServers()
      .subscribe(data => {
        this.servers = data;
      });

  }

  createServer(): void{
    this.serverService.createServer(this.server)
      .subscribe(data => {
        console.log("User created successfully.")
      });
  }

  public changeSuccessMessage() {
    this._success.next(`${new Date()} - The server has been added`);
  }

  constructor(private modalService: NgbModal, private serverService: ServersService) {
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
