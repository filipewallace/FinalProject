import { Game } from 'src/app/models/game';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { GameService } from 'src/app/services/game.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

  editGame: null | Game = null;
  games: Game[] = [];
  newGame: Game = new Game();
  currentRate = 0;

  constructor(private gameSvc: GameService, private route: ActivatedRoute, private router: Router,config: NgbModalConfig, private modalService: NgbModal) {
    config.backdrop = 'static';
    config.keyboard = false;
  }

  reload():void {
    this.gameSvc.index().subscribe(
      {
        next: (game) => {
          console.log(game)
          this.games = game;
        },
        error: (problem) => {
          console.error("GameHttpComponent.reload(): error loading Games: ");
          console.error(problem);

        }
      }
    );
  }

  deleteGame(id: number): void {

    this.gameSvc.destroy(id).subscribe({
      next: (result) => {
        this.reload();
      },
      error: (err) => {
        console.error("GameComponent.deleteGame(): Error deleting Game");
        console.error(err);
      }
    })
  };

  addGame(game: Game): void {
    this.gameSvc.create(game).subscribe({
     next: (result) =>{
       this.newGame = new Game();
       this.reload()
     },
     error: (err) => {
       console.error("GameComponent.addGame(): Error Creating a game");
       console.error(err);
     }
   })
 };

 updateGame(game: Game){
  this.gameSvc.update(game).subscribe({
    next: (game) => {
      console.log(game);
      this.reload();
    },

    error: (err) => {
      console.error("GameComponent.updateGame(): Error updating game");
      console.error(err);
    }
  })
}

open(content: any) {
  this.modalService.open(content);
}


  ngOnInit(): void {

    this.reload();
  }

}
