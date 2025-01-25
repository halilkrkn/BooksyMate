import {Component, OnInit} from '@angular/core';
import {RegistrationRequest} from '../../services/models/registration-request';
import {FormsModule} from '@angular/forms';
import {NgForOf, NgIf} from '@angular/common';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../services/services/authentication.service';
import {KeycloakService} from '../../services/keycloak/keycloak.service';

@Component({
  selector: 'app-register',
  imports: [
    FormsModule,
    NgForOf,
    NgIf
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {

  registerRequest: RegistrationRequest = {
    email: '',
    firstName: '',
    lastName: '',
    password: ''
  };

  errorMessage: Array<string> = [];

  constructor(
    private router: Router,
    private authService: AuthenticationService,
  ) {
  }


  register() {
    this.errorMessage = [];
    this.authService.register({body: this.registerRequest}).subscribe({
      next: (authResponse) => {
        this.router.navigate(['/activate-account']);
      },
      error: (err) => {
        console.log(err);
        if (err.error.validationErrors) {
          this.errorMessage = err.error.validationErrors;
        } else {
          this.errorMessage.push(err.error.businessErrorDescription);
        }
      }
    });
  }

  login() {
    this.router.navigate(['/login']);
  }
}
