import { Rating } from './rating';
import { Platform } from './platform';
import { Publisher } from './publisher';
import { Category } from './category';
import { Developer } from './developer';
import { Mods } from "./mods";

export class Game {
  id: number;
  name: string | null;
  multiplayer: boolean | false;
  description: string | null;
  imageUrl: string | null;
  category: Category[] | null;
  developer: Developer | null;
  publisher: Publisher | null;
  platform: Platform[] | null;
  mods: Mods[] | null;
  rating: Rating | null;



  constructor(id: number=0, name:string='', multiplayer: boolean=false,
  description: string='', imageUrl: string='', category: any[] | null=[],
  developer: any=0, publisher: any=1, platform: any[] | null = [], rating: any=0, mods: Mods[]|null=[]){
    this.id = id;
    this.name = name;
    this.description = description;
    this.multiplayer = multiplayer;
    this.imageUrl = imageUrl;
    this.developer = developer;
    this.publisher = publisher;
    this.platform = platform;
    this.rating=rating;
    this.category = category;
    this.mods=mods;

  }

}
