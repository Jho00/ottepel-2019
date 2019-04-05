import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
    selector: 'app-signup',
    templateUrl: './signup.component.html',
    styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {
    public signupForm: FormGroup;
    constructor(private fb: FormBuilder) {
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

    }

}
