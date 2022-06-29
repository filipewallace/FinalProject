import { ModService } from './../../services/mod.service';
import { CategoryService } from './../../services/category.service';
import { Rating } from './../../models/rating';
import { PlatformService } from './../../services/platform.service';
import { Publisher } from './../../models/publisher';
import { Developer } from './../../models/developer';
import { Game } from 'src/app/models/game';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { GameService } from 'src/app/services/game.service';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';
import { Platform } from 'src/app/models/platform';
import { Category } from 'src/app/models/category';
import { DeveloperService } from 'src/app/services/developer.service';
import { PublisherService } from 'src/app/services/publisher.service';
import { RatingService } from 'src/app/services/rating.service';
import { Mods } from 'src/app/models/mods';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css'],
})
export class GameComponent implements OnInit {
  editGame: null | Game = null;
  games: Game[] = [];
  newGame: Game = new Game();
  currentRate = 0;
  developers: Developer[] = [];
  publishers: Publisher[] = [];
  platforms: Platform[] = [];
  rating: Rating[] = [];
  categories: Category[] = [];
  mods: Mods[] = [];


  constructor(
    private gameSvc: GameService,
    private route: ActivatedRoute,
    private router: Router,
    config: NgbModalConfig,
    private modalService: NgbModal,
    private paltformSvc: PlatformService,
    private developerSvc: DeveloperService,
    private publisherSvc: PublisherService,
    private platformSvc: PlatformService,
    private ratingSvc: RatingService,
    private categorySrv: CategoryService,
    private modSrv: ModService,
    private auth: AuthService
  ) {

    config.backdrop = 'static';
    config.keyboard = false;
  }

  reload(): void {
    this.gameSvc.index().subscribe({
      next: (game) => {
        console.log(game);
        this.games = game;
      },
      error: (problem) => {
        console.error('GameHttpComponent.reload(): error loading Games: ');
        console.error(problem);
      },
    });

    this.developerSvc.index().subscribe({
      next: (developers) => {
        console.log(developers);
        this.developers = developers;
      },
      error: (problem) => {
        console.error('GameHttpComponent.reload(): error loading developers: ');
        console.error(problem);
      },
    });

    this.publisherSvc.index().subscribe({
      next: (publishers) => {
        console.log(publishers);
        this.publishers = publishers;
      },
      error: (problem) => {
        console.error('GameHttpComponent.reload(): error loading publishers: ');
        console.error(problem);
      },
    });
    this.platformSvc.index().subscribe({
      next: (platforms) => {
        console.log(platforms);
        this.platforms = platforms;
      },
      error: (problem) => {
        console.error('GameHttpComponent.reload(): error loading platforms: ');
        console.error(problem);
      },
    });

    this.ratingSvc.index().subscribe({
      next: (rating) => {
        console.log(rating);
        this.rating = rating;
      },
      error: (problem) => {
        console.error('GameHttpComponent.reload(): error loading platforms: ');
        console.error(problem);
      },
    });

    this.categorySrv.index().subscribe({
      next: (categories) => {
        console.log(categories);
        this.categories = categories;
      },
      error: (problem) => {
        console.error('GameHttpComponent.reload(): error loading categories: ');
        console.error(problem);
      },
    });

    this.modSrv.index().subscribe({
      next: (mods) => {
        console.log(mods);
        this.mods = mods;
      },
      error: (problem) => {
        console.error('GameHttpComponent.reload(): error loading categories: ');
        console.error(problem);
      },
    });
  }

  deleteGame(id: number): void {
    this.gameSvc.destroy(id).subscribe({
      next: (result) => {
        this.reload();
      },
      error: (err) => {
        console.error('GameComponent.deleteGame(): Error deleting Game');
        console.error(err);
      },
    });
  }

  addGame(game: Game): void {
    this.gameSvc.create(game).subscribe({
      next: (result) => {
        this.newGame = new Game();
        this.reload();
      },
      error: (err) => {
        console.error('GameComponent.addGame(): Error Creating a game');
        console.error(err);
      },
    });
  }

  updateGame(game: Game) {
    this.gameSvc.update(game).subscribe({
      next: (game) => {
        console.log(game);
        this.reload();
      },

      error: (err) => {
        console.error('GameComponent.updateGame(): Error updating game');
        console.error(err);
      },
    });
  }


isAdmin() : boolean {
  return this.auth.isAdmin()
}

  open(content: any) {
    this.modalService.open(content);
  }

  ngOnInit(): void {
    this.reload();
  }
}
