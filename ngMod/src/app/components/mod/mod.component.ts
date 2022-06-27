import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Mods } from 'src/app/models/mods';
import { ModService } from 'src/app/services/mod.service';
import { Game } from 'src/app/models/game';
import { GameService } from 'src/app/services/game.service';

@Component({
  selector: 'app-mod',
  templateUrl: './mod.component.html',
  styleUrls: ['./mod.component.css']
})
export class ModComponent implements OnInit {

  mods: Mods[] = [];


  constructor(private modSvc: ModService, private route: ActivatedRoute, private router: Router) { }

  reload():void {
    this.modSvc.index().subscribe(
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
    );
  }



  ngOnInit(): void {

    this.reload()
  }

}
