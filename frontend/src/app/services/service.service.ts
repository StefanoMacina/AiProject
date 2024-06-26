import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { UserDto } from '../models/user.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  $http = inject(HttpClient);

  baseUrl = "http://localhost:8080/api/v1/user";

  getUserInfo() : Observable<UserDto>{
   return this.$http.get<UserDto>(`${this.baseUrl}/info`)
  }

  constructor() { }
}
