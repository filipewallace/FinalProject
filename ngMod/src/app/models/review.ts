export class Review {
  id: number
  user: number
  mod: number
  score: number
  opinion: string


  constructor(id: number=0, user: number= 0, mod: number = 0, score: number=0, opinion: string=""){
    this.id = id
    this.user = user
    this.mod = mod
    this.score= score
    this.opinion = opinion

  }
}
