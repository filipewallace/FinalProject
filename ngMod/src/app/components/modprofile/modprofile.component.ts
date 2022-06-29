import { ReviewService } from './../../services/review.service';
import { Component, OnInit } from '@angular/core';
import { ModService } from 'src/app/services/mod.service';
import { Game } from 'src/app/models/game';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { GameService } from 'src/app/services/game.service';
import { Observable } from 'rxjs';9
import { Mods } from 'src/app/models/mods';
import { Call } from '@angular/compiler';
import { Review } from 'src/app/models/review';

@Component({
  selector: 'app-modprofile',
  templateUrl: './modprofile.component.html',
  styleUrls: ['./modprofile.component.css']
})

export class ModProfileComponent implements OnInit {
  gameMods: Mods[] = [];
  game: Game = new Game();
  newGameMod: Mods = new Mods();
  mod: Mods = new Mods();
  reviews: Review []=[];


  title = 'Mod Details';
  selected: null | Mods = null;

  constructor(
    gameSvc: GameService,
    private modSvc: ModService,
    private reviewSvc: ReviewService,
    private route: ActivatedRoute,
    private router: Router,
    config: NgbModalConfig,
    private modalService: NgbModal
  ) {
    config.backdrop = 'static';
    config.keyboard = false;
  }

  ngOnInit(): void {

    let modIdStr = this.route.snapshot.paramMap.get('id');
    if (modIdStr) {
      let modId = Number.parseInt(modIdStr);
      if (!isNaN(modId)) {
      this.reload(modId);}
    }
  }
  open(content: any) {
    this.modalService.open(content);
  }

  reload(id:number): void {
    this.modSvc.show(id).subscribe({
      next: (mod) => {
        console.log("**********"+mod.title+"************");
        console.log("**********"+mod.reviews+"************");
        this.mod = mod;
      },
      error: (problem) => {
        console.error('ModHttpComponent.reload(): error loading Games: ');
        console.error(problem);
      },
    });
  }

  getModReviews(id:number): void {
    this.reviewSvc.indexReviewByMod(id).subscribe({
      next: (tempReviews) => {
        console.log("**********"+tempReviews+"************");
        console.log("**********"+tempReviews[1]+"************");
        this.reviews=tempReviews;
      },
      error: (problem) => {
        console.error('ModHttpComponent.reload(): error loading Games: ');
        console.error(problem);
      },
    });
  }


}
