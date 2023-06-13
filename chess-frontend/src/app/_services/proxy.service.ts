import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ProxyService {
  public isAuthenticated: boolean = false;
  public username: string = '';
  constructor() {}
}
