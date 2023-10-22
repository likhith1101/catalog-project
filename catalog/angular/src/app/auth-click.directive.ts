import { Directive, HostListener } from '@angular/core';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';

@Directive({
  selector: '[appAuthClick]'
})
export class AuthClickDirective {

  constructor(private authService: AuthService, private router: Router) { }

  @HostListener('click', ['$event'])
  onClick(event: MouseEvent) {
    if (!this.authService.isLoggedIn || this.authService.isExpired()) {
      event.preventDefault();
      this.router.navigate(['/login']);
    }
  } 
}
