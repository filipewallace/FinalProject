export class Category {
  id: number | null
  genre: string | null;

  constructor(id: number=0, genre: string=""){
    this.id = id;
    this.genre = genre;
  }
}
