import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
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
      //return this.http.get()
      return of<Problem>({
          id: 1,
          title: 'Говно течёт по трубам',
          text: 'Давно выяснено, что при оценке дизайна и композиции читаемый текст мешает сосредоточиться. Lorem Ipsum используют потому, что тот обеспечивает более или менее стандартное заполнение шаблона, а также реальное распределение букв и пробелов в абзацах, которое не получается при простой дубликации "Здесь ваш текст.. Здесь ваш текст.. Здесь ваш текст.." Многие программы электронной вёрстки и редакторы HTML используют Lorem Ipsum в качестве текста по умолчанию, так что поиск по ключевым словам "lorem ipsum" сразу показывает, как много веб-страниц всё ещё дожидаются своего настоящего рождения. За прошедшие годы текст Lorem Ipsum получил много версий. Некоторые версии появились по ошибке, некоторые - намеренно (например, юмористические варианты).'
      })
  }

  public sendProblem(data: any): Observable<Response> {
      return this.http.post<Response>(ENDPOINTS.sendProblem, data);
  }
}
