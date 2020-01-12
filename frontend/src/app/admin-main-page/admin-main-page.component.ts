import {Component, Inject, OnInit, VERSION} from '@angular/core';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {debounceTime} from 'rxjs/operators';
import {Subject} from 'rxjs';
import {ServersFetchingService} from '../services/servers-service/servers-fetching.service';
import {Server} from '../services/servers-service/servers';


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


  ngOnInit(): void {
    setTimeout(() => this.staticAlertClosed = true, 20000);
    this._success.subscribe((message) => this.successMessage = message);
    this._success.pipe(
      debounceTime(5000)
    ).subscribe(() => this.successMessage = null);
    this.getServers();

  }

  getServers() {
    this.serverService.getAllServers()
      .subscribe(data => {
        this.servers = data;
      });
  }

  createServer(): void {
    this.serverService.createServer(this.server)
      .subscribe(data => {
        this.getServers();
      });

  }

  updateServer(server: Server): void {
    this.serverService.updateServer(this.server, this.id)
      .subscribe(data => {
        this.servers = this.servers.filter(u => u !== server);
      });

  }

  updateInfo(server: Server)  {
    this.id = server._id;
  }

  deleteUser(server: Server): void {
    this.serverService.deleteServer(server._id)
      .subscribe( data => {
        this.servers = this.servers.filter(u => u !== server);
      });
  }

  public changeSuccessMessage() {
    this._success.next(`${new Date()} - The server has been added`);
  }

  constructor(private modalService: NgbModal, private serverService: ServersFetchingService) {
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
