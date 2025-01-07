import { Component } from '@angular/core';
import {AuthenticateRequest} from '../../services/models/authenticate-request';
import {NgForOf, NgIf} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../services/services/authentication.service';
import {TokenService} from "../../services/token/token.service";

@Component({
  selector: 'app-login',
  imports: [
    NgIf,
    NgForOf,
    FormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  authRequest: AuthenticateRequest = {email: '', password: ''};
  errorMessage: Array<string> = [];

  constructor(
    private router: Router,
    private authService: AuthenticationService,
    private tokenService: TokenService
  ) {}

  login() {
    this.errorMessage = [];
    this.authService.authenticate({body: this.authRequest}).subscribe({
      next: (authResponse) => {
        this.tokenService.token = authResponse.token as string;
        // console.log('Token: ' + this.tokenService.token);
        this.router.navigate(['/books']);
      },
      error: (err) => {
        console.log(err);
        if (err.error.validationErrors) {
          this.errorMessage = err.error.validationErrors;
        } else {
          this.errorMessage.push(err.error.businessErrorDescription);
          // this.errorMessage.push(err.error.errorMessage);
        }
      }
    });
  }

  register() {
      this.router.navigate(['/register']);
  }
}
