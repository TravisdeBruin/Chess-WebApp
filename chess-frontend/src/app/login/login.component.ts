import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_services/auth.service';
import { ProxyService } from '../_services/proxy.service';
import { StorageService } from '../_services/storage.service';
import { UserLoginDto } from '../model/userDto';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  user: UserLoginDto = {
    username: '',
    password: '',
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];

  constructor(
    private authService: AuthService,
    private storageService: StorageService,
    private readonly proxyService: ProxyService
  ) {}

  ngOnInit(): void {
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.roles = this.storageService.getUser().roles;
      this.changeAuthenticated();
    }
  }

  onSubmit(): void {
    const user = this.user;

    const response = this.authService.login(user).subscribe({
      next: (data) => {
        this.storageService.saveUser(data);
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.storageService.getUser().roles;
        this.changeAuthenticated();
        // this.reloadPage();
      },
      error: (err) => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      },
    });
    console.log(response);
  }

  reloadPage(): void {
    window.location.reload();
  }

  changeAuthenticated() {
    console.log(this.user);
    console.log('updated authentication');
    this.proxyService.isAuthenticated = true;
    this.proxyService.username = this.user.username;
    console.log(this.proxyService);
  }
}
