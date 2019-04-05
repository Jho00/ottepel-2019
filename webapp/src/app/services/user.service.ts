import {Injectable} from '@angular/core';
import {User} from "../models/user.model";
import {HttpClient} from "@angular/common/http";
import {Observable, Subject} from "rxjs";
import {ENDPOINTS} from "../constants/url.constants";

@Injectable({
    providedIn: 'root'
})
export class UserService {
    public currentUserChange: Subject<User> = new Subject<User>();
    private currentUser: User;
    constructor(private http: HttpClient) {
    }

    public setUser(user: User): void {
        this.currentUser = user;
        this.currentUserChange.next(this.currentUser);
    }

    public resetCurrentUser(): void {
        this.currentUser = null;
    }

    public getCurrentUser(): Observable<User> {
        return this.http.get<User>(ENDPOINTS.getCurrentUser);
    }

    public login(data: Object): Observable<User> {
        return this.http.post<User>(ENDPOINTS.login, data);
    }

    public signup(data: Object): Observable<User> {
        return this.http.post<User>(ENDPOINTS.register, data);
    }
}
