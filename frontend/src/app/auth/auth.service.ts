import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoginRequest, RegistrationRequest } from '../models/user.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  http = inject(HttpClient);
  baseUrl = "http://localhost:8080/api/v1/auth";

  constructor() { }

  signUp(data : RegistrationRequest) : Observable<any>  {
    return this.http.post<RegistrationRequest>(`${this.baseUrl}/signup`, data);
  }

  signin(data: LoginRequest ){
    return this.http.post<LoginRequest>(`${this.baseUrl}/signin`, data)
  }

  logout(){
    return this.http.post(`${this.baseUrl}/signout`,null);
  }

}
