export class ModMedia {
id: number | null;
mediaUrl: string | null;
description: string | null;
mod: number | null;
user: number | null;


constructor(id: number= 0, mediaUrl: string="", description: string = "",
mod: number = 0, user: number= 0){
  this.id = id;
  this.mediaUrl = mediaUrl;
  this.description = description;
  this.mod = mod;
  this.user = user;
}


}
