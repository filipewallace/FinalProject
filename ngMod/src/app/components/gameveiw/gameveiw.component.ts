import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Game } from 'src/app/models/game';
import { GameService } from 'src/app/services/game.service';

@Component({
  selector: 'app-gameveiw',
  templateUrl: './gameveiw.component.html',
  styleUrls: ['./gameveiw.component.css']
})
export class GameveiwComponent implements OnInit {

  constructor(private route: ActivatedRoute, private gameSvc: GameService) { }
  game: Game = new Game();



  reload(id:number): void {
    this.gameSvc.show(id).subscribe({
      next: (game : any) => {
        console.log("**********"+game.title+"************");
        console.log("**********"+game.reviews+"************");
        this.game = game;
      },
      error: (problem : any) => {
        console.error('ModHttpComponent.reload(): error loading Games: ');
        console.error(problem);
      },
    });
  }



  ngOnInit(): void {

    let modIdStr = this.route.snapshot.paramMap.get('id');
    if (modIdStr) {
      let modId = Number.parseInt(modIdStr);
      if (!isNaN(modId)) {
      this.reload(modId);}
    }




  }

}
