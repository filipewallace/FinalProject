import { ModService } from 'src/app/services/mod.service';
import { Game } from 'src/app/models/game';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { GameService } from 'src/app/services/game.service';
import { Observable } from 'rxjs';9
import { Mods } from 'src/app/models/mods';
import { Call } from '@angular/compiler';

@Component({
  selector: 'app-game-profile',
  templateUrl: './game-profile.component.html',
  styleUrls: ['./game-profile.component.css'],
})
export class GameProfileComponent implements OnInit {
  gameMods: Mods[] = [];
  game: Game = new Game();
  newGameMod: Mods = new Mods();

  title = 'Amazing Game Mod List';
  selected: null | Mods = null;

  constructor(
    private gameSvc: GameService,
    modSvc: ModService,
    private route: ActivatedRoute,
    private router: Router,
    config: NgbModalConfig,
    private modalService: NgbModal
  ) {
    config.backdrop = 'static';
    config.keyboard = false;
  }

  ngOnInit(): void {

    let gameIdStr = this.route.snapshot.paramMap.get('id');
    if (gameIdStr) {
      let gameId = Number.parseInt(gameIdStr);
      if (!isNaN(gameId)) {
      this.reload(gameId);}
    }
  }
  open(content: any) {
    this.modalService.open(content);
  }

  reload(id:number): void {
    this.gameSvc.show(id).subscribe({
      next: (game) => {
        console.log(game);
        this.game = game;
      },
      error: (problem) => {
        console.error('GameHttpComponent.reload(): error loading Games: ');
        console.error(problem);
      },
    });
  }

}
