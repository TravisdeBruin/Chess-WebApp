import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserRegisterDto } from '../../models/userDto';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent {
  user: UserRegisterDto = {
    name: '',
    surname: '',
    username: '',
    email: '',
    password: '',
  };
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit(): void {
    const user = this.user;

    this.authService.register(user).subscribe({
      next: (data) => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        this.router.navigate(['/login']);
      },
      error: (err) => {
        console.log(err);
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      },
    });
  }
}
