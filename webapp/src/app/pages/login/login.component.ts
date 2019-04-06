import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../services/user.service";
import {Subscription} from "rxjs";
import {User} from "../../models/user.model";
import {Router} from "@angular/router";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, OnDestroy {
    public loginForm: FormGroup;
    private _subs: Subscription[] = [];
    constructor(private fb: FormBuilder,
                private userService: UserService,
                private router: Router) {
    }

    ngOnInit() {
        this.loginForm = this.fb.group({
            email: new FormControl('', [Validators.required, Validators.email]),
            password: new FormControl('', [Validators.required, Validators.minLength(8)]),
        })
    }

    public login(): void {
        this._subs.push(this.userService.login(this.loginForm.value).subscribe((data: any) => {
            this.userService.user = new User(data.body);
            this.router.navigateByUrl('/main');
        }));
    }

    ngOnDestroy(): void {
        this._subs.forEach(sub => sub.unsubscribe());
    }

}
