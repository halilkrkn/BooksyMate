import { Component } from '@angular/core';
import {AuthenticationService} from '../../services/services/authentication.service';
import {Router} from '@angular/router';
import {CodeInputModule} from 'angular-code-input';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-activate-account',
  imports: [
    CodeInputModule,
    NgIf
  ],
  templateUrl: './activate-account.component.html',
  styleUrl: './activate-account.component.scss'
})
export class ActivateAccountComponent {

  message = "";
  isOkay = true;
  submitted = false;

  constructor(
    private router: Router,
    private authService: AuthenticationService,
  ) { }

  onCodeCompleted(token: string) {
    this.confirmAccount(token);
  }

  redirectToLogin() {
    this.router.navigate(['/login']);
  }

  private confirmAccount(token: string) {
    this.authService.activateAccount({
      token
    }).subscribe({
      next : () => {
        this.message = "Your account has been activated successfully.\n You can now login.";
        this.submitted = true;
        this.isOkay = true;
      },
      error: () => {
        this.message = "Token has been expired or invalid.";
        this.isOkay = false;
        this.submitted = true;
      }
    });

  }
}
