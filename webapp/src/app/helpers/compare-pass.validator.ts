import {FormGroup, ValidationErrors, ValidatorFn} from "@angular/forms";

export const comparePassValidator: ValidatorFn = (control: FormGroup): ValidationErrors | null => {
    const password = control.get('password');
    const passwordConfirmation = control.get('passwordConfirmation');

    return password.value !== passwordConfirmation.value ? { 'differentPasswords': true } : null;
};
