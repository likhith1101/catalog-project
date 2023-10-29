import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  // Define the structure of the user object according to your User model


  constructor(private _http: HttpClient, private router: Router) { } // Inject HttpClient
  name: string = '';
  userpassword: string = ''
  confirmPassword: string = '';
  role = 'USER';


  onSubmit() {
    const userData = {
      name: this.name,
      password: this.userpassword,
      repeatPassword: this.confirmPassword,
    };

    const url = `http://localhost:8080/api/auth/signup`;
    if (this.userpassword !== this.confirmPassword) {

      alert("Passwords do not match. Please try again.");

      return;

    }

    this._http.post(url, userData, { responseType: 'text' }).subscribe({
      next: (response) => {
        console.log(userData.name),
        console.log('User registered successfully:', response);
        this.router.navigate(['/login']);
      },

      error: (error) => {
        console.error('Error registering user:', error);
      }
    });

  }
}
