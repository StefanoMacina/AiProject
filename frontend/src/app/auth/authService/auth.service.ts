import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginRequest, RegistrationRequest } from 'src/app/models/user.interface';

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

  hasAccess(){
    return this.http.get(`${this.baseUrl}/hasaccess`)
  }

}
