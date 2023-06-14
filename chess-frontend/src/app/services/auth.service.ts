import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserLoginDto, UserRegisterDto } from '../models/userDto';

const AUTH_API = 'http://localhost:8080/api/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  login(user: UserLoginDto): Observable<any> {
    return this.http.post(AUTH_API + 'signin', user, httpOptions);
  }

  register(user: UserRegisterDto): Observable<any> {
    return this.http.post(AUTH_API + 'register', user, httpOptions);
  }
}
