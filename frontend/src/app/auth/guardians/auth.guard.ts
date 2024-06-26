import { CanActivateFn } from '@angular/router';
import { AuthService } from '../authService/auth.service';
import { inject } from '@angular/core';
import { map } from 'rxjs';

export const authGuard: CanActivateFn = (route, state) => {

  const authService: AuthService=inject(AuthService);

  return authService.hasAccess().pipe(
    map((response : any ) => {
      if (response.isAuthenticated) {
        return true;
      } 
      return false;
      
    }),
  );
};
