import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../services/user.service";
import {Subscription} from "rxjs";

@Component({
    selector: 'app-signup',
    templateUrl: './signup.component.html',
    styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit, OnDestroy {
    public signupForm: FormGroup;
    private _subs: Subscription[] = [];
    constructor(private fb: FormBuilder,
                private userService: UserService) {
    }

    ngOnInit() {
        this.signupForm = this.fb.group({
            firstName: new FormControl('', Validators.required),
            lastName: new FormControl('', Validators.required),
            email: new FormControl('', [Validators.required, Validators.email]),
            password: new FormControl('', [Validators.required, Validators.minLength(8)]),
            passwordConfirmation: new FormControl('', [Validators.required, Validators.minLength(8)]),
        })
    }

    public signup(): void {
        this._subs.push(this.userService.signup(this.signupForm.value).subscribe());
    }

    ngOnDestroy(): void {
        this._subs.forEach(sub => sub.unsubscribe());
    }

}
