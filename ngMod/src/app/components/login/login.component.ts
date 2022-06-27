import { User } from './../../models/user';
import { Router } from '@angular/router';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private auth: AuthService, private router: Router) { }


  loginUser: User = new User();


  login(user: User){
    console.log(user);
    if(user.username && user.password){

      this.auth.login(user.username, user.password).subscribe({
        next: (loggedin) => {
          this.router.navigateByUrl('/home')
        },
        error: (fail) => {
          console.log('LoginComponent.login(): Error loggin in ')
        }
      })
    }

  }


  ngOnInit(): void {
  }

}
