import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
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
    console.log(loginForm.value);
    this.authService.login(loginForm.value as LoginForm).subscribe(
      () => {
        // Check the selected role
        if (loginForm.value.role === 'user' || loginForm.value.role === 'admin') {
          // Redirect to the "summary" page for users
          this.router.navigate(['/summary']);
        } else if (loginForm.value.role === 'admin') {
          // Redirect to the "product" page for admins
          this.router.navigate(['/product']);
        }
      }
    );
  }
}
