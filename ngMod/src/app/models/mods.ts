export class Mods {
  id: number | null;
  title: string | null;
  description: string | null;
  version: string | null;
  requirments: string | null;
  imgUrl: string | null;
  price: number  | null;
  downloadLink: string | null;
  game: any[] | null ;
  user: any[] | null;


  constructor(id: number=0, title : string="", description:string="",
  version: string="", requirments: string="", imgUrl: string="", price: number=0, downloadLink: string="",
  game: any[] | null=null, user: any[] | null = null){
  this.id = id;
  this.title = title;
  this.description = description;
  this.version = requirments;
  this.requirments = requirments;
  this.imgUrl = imgUrl;
  this.price = price;
  this.downloadLink = downloadLink;
  this.game = game;
  this.user = user;

  }

}
