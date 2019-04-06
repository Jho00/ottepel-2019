import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {Problem} from '../models/problem.model';
import {ENDPOINTS} from "../constants/url.constants";

@Injectable({
  providedIn: 'root'
})
export class ProblemsService {

  constructor(private http: HttpClient) { }

  public getProblemsList(): Observable<Problem[]> {
      return this.http.get<Problem[]>(ENDPOINTS.getProblemsList);
  }

  public getProblemById(id: string): Observable<Problem> {
      return this.http.get<Problem>(`${ENDPOINTS.getProblemById}?id=${id}`);

  }

  public sendProblem(data: Object): Observable<Response> {
      return this.http.post<Response>(ENDPOINTS.sendProblem, data);
  }

  public sendPhotos(data: Object, id: number): Observable<Response> {
      return this.http.post<Response>(`${ENDPOINTS.sendPhotos}/${id}`, data);
  }
}
