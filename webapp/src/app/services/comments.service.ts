import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Comment} from "../models/comment.model";
import {ENDPOINTS} from "../constants/url.constants";

@Injectable({
  providedIn: 'root'
})
export class CommentsService {

  constructor(private http: HttpClient) { }

  public getComments(id: number): Observable<Comment[]> {
      return this.http.get<Comment[]>(`${ENDPOINTS.getComments}?postId=${id}`);
  }

  public addComment(text: string, problemId: number): Observable<Response> {
      return this.http.get<Response>(`${ENDPOINTS.addComment}?text=${text}&problemId=${problemId}`);
  }
}
