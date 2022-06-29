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
  developer: Developer;
  publisher: Publisher;
  platform: Platform;
  mods: Mods[] | null;
  rating: Rating;



  constructor(id: number=0, name:string='', multiplayer: boolean=false,
  description: string='', imageUrl: string='', category: Category[] | null=[],
  developer: Developer=new Developer(), publisher: Publisher=new Publisher(), platform: Platform=new Platform(), rating:Rating=new Rating(), mods: Mods[]|null=[]){
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
