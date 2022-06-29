import { AuthService } from 'src/app/services/auth.service';
import { GameService } from 'src/app/services/game.service';
import { ModService } from './../../services/mod.service';
import { Component, OnInit } from '@angular/core';
import { Game } from 'src/app/models/game';
import { Mods } from 'src/app/models/mods';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-user-mods',
  templateUrl: './user-mods.component.html',
  styleUrls: ['./user-mods.component.css']
})
export class UserModsComponent implements OnInit {

  mods: Mods[] = [];
  newMod: Mods = new Mods();
  users: User[] = [];
  games: Game[] =[];

  constructor(private modSvc: ModService, private gameSvc: GameService, private auth: AuthService) { }




  reload():void {

    if(this.newMod.user.id != null){


      this.modSvc.userMods().subscribe(
      {
        next: (mod) => {
          console.log(mod)
          this.mods = mod;
        },
        error: (problem) => {
          console.error("ModHttpComponent.reload(): error loading Mods: ");
          console.error(problem);

        }
      }
    )

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


      this.auth.getLoggedInUser().subscribe({
        next: (user) => {
          console.log(user)
          this.newMod.user = user;
        },
        error: (problem) => {
          console.error("ModHttpComponent.reload(): error loading Mods: ");
          console.error(problem);

        }

      })

    }


    };





  ngOnInit(): void {
    this.reload();
  }

}
