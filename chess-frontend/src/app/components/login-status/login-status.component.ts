import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-login-status',
  templateUrl: './login-status.component.html',
  styleUrls: ['./login-status.component.scss'],
})
export class LoginStatusComponent implements OnInit {
  constructor(private router: Router, private storageService: StorageService) {}

  isAuthenticated() {
    return this.storageService.isLoggedIn();
  }

  getUsername() {
    const user = this.storageService.getUser();
    return user!.username;
  }

  ngOnInit(): void {}
  getUserDetails() {}

  gotoLogin() {
    this.router.navigate(['/login']);
  }

  gotoRegister() {
    this.router.navigate(['/register']);
  }
  gotoProfile() {
    this.router.navigate(['/profile']);
  }

  logout() {
    this.storageService.clean();
    this.router.navigate(['/login']);
  }
}
