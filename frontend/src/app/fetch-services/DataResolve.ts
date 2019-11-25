import {Trial} from '../call-models/trial';
import {Injectable} from '@angular/core';
import {BrAPIDetailResponse} from '../call-models/response/brAPIDetailResponse';
import {ActivatedRouteSnapshot, Resolve} from '@angular/router';
import {FetchBrapiResultService} from './fetch-brapi-result-service';
import {Observable} from 'rxjs';
import {IPost} from './Ipost';

@Injectable()
export class DataResolve implements Resolve<IPost[]> {

  constructor(private  fetchBrapiResult: FetchBrapiResultService) {}

  resolve() {
  return this.fetchBrapiResult.getAllData();
  }
}
