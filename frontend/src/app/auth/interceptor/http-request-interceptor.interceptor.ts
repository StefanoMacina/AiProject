import { HttpInterceptorFn } from '@angular/common/http';

export const httpRequestInterceptorInterceptor: HttpInterceptorFn = (req, next) => {
  return next(req);
};
