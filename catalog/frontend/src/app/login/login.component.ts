import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { LoginForm } from '../login-form';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  login(loginForm: any): void {
    if (loginForm.invalid) return;

    // Assuming the AuthService returns a user object with information regarding their role (admin or user)
    this.authService.login(loginForm.value as LoginForm).subscribe((user) => {
      if (this.authService.isUserAdmin()) {
        console.log(this.authService.isUserAdmin());
        this.router.navigate(['/product']); // Redirect to admin summary page
      } else if(this.authService.isUser()) {
        console.log("user");
        this.router.navigate(['/summary']); // Redirect to user summary page
      }
    }, error => {
      // Handle login error, e.g., show error message
      console.error('Login failed:', error);
    });
  }
}
