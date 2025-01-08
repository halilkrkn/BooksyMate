import {HttpHeaders, HttpInterceptorFn} from '@angular/common/http';
import {TokenService} from "../token/token.service";

export const httpTokenInterceptor: HttpInterceptorFn = (req, next) => {
  const tokenService = new TokenService();
  const authToken = tokenService.token;
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
