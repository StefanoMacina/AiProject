import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { SignupComponent } from './pages/signup/signup.component';
import { AdminComponent } from './pages/admin/admin.component';
import { roleGuard } from './auth/guardians/role.guard';

export const routes: Routes = [
  {
    path: '', redirectTo: '/login', pathMatch: 'full'
    //loadChildren: () => import('./tabs/tabs.routes').then((m) => m.routes),
  },
  {
    path: 'login', component: LoginComponent
  },
  {
    path: 'signup', component: SignupComponent
  },
  {
    path: 'admin', component: AdminComponent, canActivate: [roleGuard]
  },
  {
    path: '',
    loadChildren: () => import('./tabs/tabs.routes').then(m => m.routes),
    canActivate : [roleGuard]
    
  }
];
