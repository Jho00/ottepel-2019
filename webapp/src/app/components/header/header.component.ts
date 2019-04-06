import {Component, OnDestroy, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {User} from "../../models/user.model";
import {Subscription} from "rxjs";

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit, OnDestroy {
    public currentUser: User;
    private _subs: Subscription[] = [];
    constructor(private userService: UserService) {
    }

    ngOnInit() {
        if(localStorage.getItem('token') !== null) {
            this._subs.push(this.userService.getCurrentUser().subscribe((data: any) => {
                this.userService.user = new User(data.body);
            }));
        }
        this._subs.push(this.userService.currentUserChange.subscribe(user=> this.currentUser = user));
    }

    public logout(): void {
        this.userService.user = null;
    }

    ngOnDestroy(): void {
        this._subs.forEach(sub => sub.unsubscribe());
    }

}
