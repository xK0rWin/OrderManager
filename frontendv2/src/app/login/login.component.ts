// login.component.ts

import { Component } from '@angular/core';
import { PASSWORD } from '../config';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  password: string = '';
  error: string = '';

  constructor(private router: Router) {}

  login() {
    // Simple password check (replace with your actual authentication logic)
    if (this.password === PASSWORD) {
      // Store the password in localStorage
      localStorage.setItem('password', this.password);
      this.router.navigate(['']);
    } else {
      this.error = "Falsches Passwort!";
    }
  }
}
