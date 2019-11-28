import {Injectable} from '@angular/core';
import {Globals} from '../globals';

@Injectable()
export class ServersService {

  globals: Globals;

  constructor(globals: Globals) {
    this.globals = globals;
  }

  public getAllSelected() {
    return this.globals.selectedServers;
  }
}
