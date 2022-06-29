import { Review } from 'src/app/models/review';
import { ReviewService } from './../../services/review.service';
import { GameService } from 'src/app/services/game.service';
import { Component, OnInit } from '@angular/core';
import { Mods } from 'src/app/models/mods';
import { ModService } from 'src/app/services/mod.service';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';
import { Game } from 'src/app/models/game';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-mod',
  templateUrl: './mod.component.html',
  styleUrls: ['./mod.component.css'],
})
export class ModComponent implements OnInit {
  editMod: null | Mods = null;
  mods: Mods[] = [];
  newMod: Mods = new Mods();
  currentRate = 0;
  users: User[] = [];
  currentUser: null | User = null;
  games: Game[] = [];
  reviews: Review[] = [];


  constructor(
    private modSvc: ModService,
    private route: ActivatedRoute,
    private router: Router,
    config: NgbModalConfig,
    private modalService: NgbModal,
    private auth: AuthService,
    private gameSvc: GameService,
    private user: UserService,
    private reviewSvc: ReviewService
  ) {
    config.backdrop = 'static';
    config.keyboard = false;
  }

  reload(): void {
    this.modSvc.index().subscribe({
      next: (mod) => {
        console.log(mod);
        this.mods = mod;
      },
      error: (problem) => {
        console.error('ModHttpComponent.reload(): error loading Mods: ');
        console.error(problem);
      },
    });

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



    this.auth.getLoggedInUser().subscribe({
      next: (user) => {
        console.log(user);
        this.newMod.user = user;
      },
      error: (problem) => {
        console.error('ModHttpComponent.reload(): error loading Mods: ');
        console.error(problem);
      },
    });
  }


loadReviews(id:number): void {

  this.reviewSvc.indexReviewByMod(id).subscribe({
    next: (reviews) => {
      console.log(reviews);
      console.log(reviews[0]);
      this.reviews = reviews;
    },
    error: (problem) => {
      console.error('ReviewHttpComponent.reload(): error loading Games: ');
      console.error(problem);
    },
  });

}

  deleteMod(id: number): void {
    this.modSvc.destroy(id).subscribe({
      next: (result) => {
        this.reload();
      },
      error: (err) => {
        console.error('ModComponent.deleteMod(): Error deleting Mod');
        console.error(err);
      },
    });
  }

  addMod(mod: Mods): void {
    this.modSvc.create(mod).subscribe({
      next: (result) => {
        this.newMod = new Mods();
        this.reload();
      },
      error: (err) => {
        console.error('ModsComponent.addMod(): Error Creating a mod');
        console.error(err);
      },
    });
  }

  updateMod(mod: Mods) {
    this.modSvc.update(mod).subscribe({
      next: (mod) => {
        console.log(mod);
        this.reload();
      },

      error: (err) => {
        console.error('ModComponent.updateMod(): Error updating mod');
        console.error(err);
      },
    });
  }

  isAdmin(): boolean {
    return this.auth.isAdmin();
  }

  canEdit(mod: Mods): any {
    if (mod.user != null) {
      this.auth.getLoggedInUser();
    }
  }

  open(content: any) {
    this.modalService.open(content);
  }

  ngOnInit(): void {
    this.reload();
  }
}
