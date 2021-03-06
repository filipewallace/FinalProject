import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';
import { Game } from 'src/app/models/game';
import { GameService } from 'src/app/services/game.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  games: Game[] = [];




  constructor(private gameServ: GameService, private auth: AuthService) {

   }


  reload():void {
    this.gameServ.index().subscribe(
      {
        next: (game) => {

          this.games = game
        },
        error: (err) => {
          console.error("HomeComponent.reload(): Error loading Games");
          console.error(err);
        }
      }
    )
  };



  loggedIn(): boolean {
    return this.auth.checkLogin();
  }



  ngOnInit(): void {
    this.reload();
  }

}
