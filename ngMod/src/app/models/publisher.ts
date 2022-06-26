export class Publisher {

  id: number | null;
  name: string | null;
  imageUrl: string | null;
  webLink: string | null;


  constructor(id: number= 0, name: string="", imageUrl: string="", webLink: string=""){
    this.id = id;
    this.name = name;
    this.imageUrl = imageUrl;
    this.webLink = webLink;

  }
}
