import { CanActivateFn } from '@angular/router';
import { AuthService } from '../authService/auth.service';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
  return () => {
    const authService: AuthService=inject(AuthService);

    
  };
};
