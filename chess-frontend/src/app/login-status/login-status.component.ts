import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProxyService } from '../_services/proxy.service';

@Component({
  selector: 'app-login-status',
  templateUrl: './login-status.component.html',
  styleUrls: ['./login-status.component.scss'],
})
export class LoginStatusComponent implements OnInit {
  constructor(
    private router: Router,
    private readonly proxyService: ProxyService
  ) {}

  getAuthenticated() {
    return this.proxyService.isAuthenticated;
  }

  getUsername() {
    return this.proxyService.username;
  }

  ngOnInit(): void {}
  getUserDetails() {}

  gotoLogin() {
    this.router.navigate(['/login']);
  }

  gotoRegister() {
    this.router.navigate(['/register']);
  }

  logout() {}
}
