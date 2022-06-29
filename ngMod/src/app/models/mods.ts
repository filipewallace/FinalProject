import { User } from 'src/app/models/user';
import { Game } from 'src/app/models/game';
export class Mods {
  id: number;
  title: string | null;
  description: string | null;
  version: string | null;
  requirments: string | null;
  imageUrl: string | null;
  price: number  | null;
  downloadLink: string | null;
  game: Game;
  user: User;


  constructor(id: number=0, title : string="", description:string="",
  version: string="", requirments: string="", imgUrl: string="", price: number=0, downloadLink: string="",
  game: Game = new Game() , user: User = new User()){
  this.id = id;
  this.title = title;
  this.description = description;
  this.version = requirments;
  this.requirments = requirments;
  this.imageUrl = imgUrl;
  this.price = price;
  this.downloadLink = downloadLink;
  this.game = game;
  this.user = user;

  }

}
