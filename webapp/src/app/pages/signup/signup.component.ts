import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../services/user.service";
import {Subscription} from "rxjs";
import {User} from "../../models/user.model";
import {Router} from "@angular/router";
import {comparePassValidator} from "../../helpers/compare-pass.validator";

@Component({
    selector: 'app-signup',
    templateUrl: './signup.component.html',
    styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit, OnDestroy {
    public signupForm: FormGroup;
    private _subs: Subscription[] = [];
    constructor(private fb: FormBuilder,
                private userService: UserService,
                private router: Router) {
    }

    ngOnInit() {
        this.signupForm = this.fb.group({
            firstName: new FormControl('', Validators.required),
            lastName: new FormControl('', Validators.required),
            email: new FormControl('', [Validators.required, Validators.email]),
            password: new FormControl('', [Validators.required, Validators.minLength(8)]),
            passwordConfirmation: new FormControl('', [Validators.required, Validators.minLength(8)]),
        }, {validators: comparePassValidator})
    }

    public signup(): void {
        this._subs.push(this.userService.signup(this.signupForm.value).subscribe((data: any) => {
            this.userService.user = new User(data.body);
            this.router.navigateByUrl('/main');
        }));
    }

    ngOnDestroy(): void {
        this._subs.forEach(sub => sub.unsubscribe());
    }

}
