import { User } from 'src/app/models/user';
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'activeUser'
})
export class ActiveUserPipe implements PipeTransform {

  transform(users: User[]): any {
    users.forEach((user) => {
      if(user.enabled === 1){
          return "Active"
      }else {
        return "Inactive";
      }
    })

  }

}
