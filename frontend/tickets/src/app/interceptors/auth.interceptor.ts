import { HttpInterceptorFn } from '@angular/common/http';
import { Environment } from '../enviroment/enviroment';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const token = Environment.jwtToken;
  if (!token) return next(req);

  const cloned = req.clone({
    setHeaders: { Authorization: `Bearer ${token}` },
  });
  return next(cloned);
};
