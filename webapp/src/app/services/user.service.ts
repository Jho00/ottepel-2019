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
    public token: string;
    private currentUser: User;
    constructor(private http: HttpClient) {
    }

    set user(user: User) {
        if(user === null) {
            localStorage.removeItem('token');
        }
        this.currentUser = user;
        this.token = user.token;
        localStorage.setItem('token', this.token);
        this.currentUserChange.next(this.currentUser);
    }

    get user(): User {
        return this.currentUser;
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
