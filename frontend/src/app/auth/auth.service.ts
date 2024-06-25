import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  HttpClient = inject(HttpClient);
  baseUrl = "http://localhost:8080/api/v1/auth";

  constructor() { }

  signUp(data : User)  {
    return this.HttpClient.post(`${this.baseUrl}/signup`, data);
  }





}
