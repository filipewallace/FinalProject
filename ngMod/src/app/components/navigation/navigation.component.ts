import { Observable } from 'rxjs';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  currentUser: User = new User();

  constructor(private auth: AuthService) { }


  loggedIn(): boolean {

    return this.auth.checkLogin();
  };

getUser(): void{

  this.auth.getLoggedInUser().subscribe(event => this.currentUser = event);

}

isAdmin(): boolean {
  return this.auth.isAdmin();
}


  ngOnInit(): void {
    this.getUser();


  }

}
