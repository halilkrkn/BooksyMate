import { Injectable } from '@angular/core';
import Keycloak from 'keycloak-js';
import {UserProfile} from './user-profile';

@Injectable({
  providedIn: 'root'
})
export class KeycloakService {


  private _keycloak: Keycloak | undefined;
  private _userProfile: UserProfile | undefined;

  get keycloak() {
    if (!this._keycloak) {
      this._keycloak = new Keycloak({
        url: 'http://localhost:9090',
        realm: 'booksy-mate',
        clientId: 'booksy-mate'
      });
    }
    return this._keycloak;
  }

  get userProfile(): UserProfile | undefined {
    return this._userProfile;
  }

  constructor() { }

  async init() {
    console.log('Authenticating The User...');
    const authenticated = await this.keycloak?.init({
        onLoad: 'login-required',
    });

    if (authenticated) {
        this._userProfile = (await this.keycloak?.loadUserProfile()) as UserProfile;
        this._userProfile.token = this.keycloak?.token as string;
    }
  }

  login() {
     return this.keycloak?.login()
  }

  register() {
    return this.keycloak?.register();
  }

  logout() {
    return this.keycloak?.logout({
      redirectUri: 'http://localhost:4200'
    });
  }
}
