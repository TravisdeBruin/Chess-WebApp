import { Injectable } from '@angular/core';
import { UserDto } from '../models/userDto';

@Injectable({
  providedIn: 'root',
})
export class StorageService {
  private readonly USER_KEY = 'user';
  private readonly TOKEN_KEY = 'authToken';

  constructor() {}

  clean(): void {
    window.sessionStorage.clear();
  }

  public saveUser(user: UserDto, token: string): void {
    window.sessionStorage.removeItem(this.USER_KEY);
    window.sessionStorage.setItem(this.USER_KEY, JSON.stringify(user));
    this.saveToken(token);
  }

  public getUser(): UserDto | null {
    const user = window.sessionStorage.getItem(this.USER_KEY);
    if (user) {
      return JSON.parse(user);
    }

    return null;
  }

  public getToken(): string | null {
    return window.sessionStorage.getItem(this.TOKEN_KEY);
  }

  private saveToken(token: string): void {
    window.sessionStorage.setItem(this.TOKEN_KEY, token);
  }

  public isLoggedIn(): boolean {
    return this.getToken() !== null;
  }
}
