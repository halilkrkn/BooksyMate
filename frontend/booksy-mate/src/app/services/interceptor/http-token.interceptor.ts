import {HttpHeaders, HttpInterceptorFn} from '@angular/common/http';
import {TokenService} from "../token/token.service";
import {KeycloakService} from '../keycloak/keycloak.service';

export const httpTokenInterceptor: HttpInterceptorFn = (req, next) => {
  const keycloakService = new KeycloakService();
  const authToken = keycloakService.keycloak?.token;
  if (authToken) {
    const authReq = req.clone({
      headers: new HttpHeaders({
        Authorization: `Bearer ${authToken}`
      })
    });
    return next(authReq);
  }
  return next(req);
};
