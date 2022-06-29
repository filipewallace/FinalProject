import { Mods } from "./mods";
import { User } from 'src/app/models/user';
import { Game } from 'src/app/models/game';

export class Review {
  id: number;
  user: User;
  mod: Mods;
  score: number | null;
  opinion: string | null;


  constructor(id: number=0, user:User= new User(), mod: Mods=new Mods(), score: number=0, opinion: string=""){
    this.id = id;
    this.user = user;
    this.mod = mod;
    this.score= score;
    this.opinion = opinion;

  }
}
