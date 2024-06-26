import { HttpHandlerFn, HttpInterceptorFn, HttpRequest } from '@angular/common/http';

export const authenticationInterceptor: HttpInterceptorFn = (req, next) => {

  const cloneReq = req.clone({
    withCredentials : true
  });

  return next(cloneReq);
};