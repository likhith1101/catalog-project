import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { LoginForm } from '../login-form';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private authService: AuthService) {

  }

  login(loginForm: any): void {
    if (loginForm.invalid) return;
    console.log(loginForm.value)
    this.authService.login(loginForm.value as LoginForm).subscribe();
  }
  
}
