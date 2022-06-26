export class Post {
  id: number | null;
  title: string | null;
  comment: string | null;
  user: number | null
  mod: number | null
  post: number | null;


  constructor(id: number=0, title: string="", comment: string="", user: number=0, mod: number =0, post: number=0){
    this.id =id;
    this.title = title
    this.comment = comment
    this.user = user
    this.mod = mod
    this.post = post
  }
}
