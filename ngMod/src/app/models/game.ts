export class Game {
  id: number;
  name: string | null;
  multiplayer: boolean | false;
  description: string | null;
  imageUrl: string | null;
  category: any[] | null;
  developer: number | null;
  publisher: number | null;
  platform: any[] | null;



  constructor(id: number=0, name:string='', multiplayer: boolean=false,
  description: string='', imageUrl: string='', category: any[] | null=null,
  developer: number=0, publisher: number=0, platform: any[] | null = null){
    this.id = id
    this.name = name
    this.description = description
    this.multiplayer = multiplayer
    this.imageUrl = imageUrl
    this.category = category
    this.developer = developer
    this.publisher = publisher
    this.platform = platform

  }

}
