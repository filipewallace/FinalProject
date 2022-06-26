import { Game } from './game';
export class Developer {

  id: number | null;
  name: string | null;
  imageUrl: string | null;
  webLink: string | null;

  constructor(id: number | null=0, name: string | null="", imageUrl: string | null="",
  webLink: string | null =""){

    this.id = id;
    this.name = name;
    this.webLink = webLink;
    this.imageUrl = imageUrl;

  }

}
