import { Injectable } from '@angular/core';
import {
  HttpClient,
  HttpErrorResponse,
  HttpParams,
  HttpHeaders,
} from '@angular/common/http';
import {
  ShortestPathRequest,
  PlanetListResponse,
  BaseResponse,
} from '../shared/model/interfaces';
import { Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { getOptions, errorHandler } from '../shared/util/static.function';
import { catchError } from 'rxjs/operators';

@Injectable()
export class ShortestPathService {

  constructor(private http: HttpClient) {}

  public calculateShortestPath(request: ShortestPathRequest): Observable<PlanetListResponse> {
    const url = environment.apiBaseUrl + '/planet/path';

    return this.http
      .post<PlanetListResponse>(url, request, getOptions())
      .pipe(catchError(errorHandler));
  }

  public getListOfPlanets(): Observable<PlanetListResponse> {
    const url = environment.apiBaseUrl + '/planet';
    return this.http
      .get<any>(url, getOptions())
      .pipe(catchError(errorHandler));
  }
}
