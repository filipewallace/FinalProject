import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.css']
})
export class UserManagementComponent implements OnInit {
    users: User[] = [];
    editUser: null | User = null;

  constructor(private userSvc: UserService, private modalService: NgbModal) { }


  reload():void {
    this.userSvc.index().subscribe(
      {
        next: (user) => {
          console.log(user)
          this.users = user;

        },
        error: (problem) => {
          console.error("ModHttpComponent.reload(): error loading Mods: ");
          console.error(problem);

        }
      }
    );
  }

  activeUsers(user: User): String {
    if(user.enabled == 1){
      return 'Active'
    } else{

      return 'Inactive'
    }
  };


  updateUser(user: User){


    this.userSvc.update(user).subscribe({
      next: (edit) => {
        console.log(user)
        this.reload();
      },
      error: (err) => {
        console.error("TodoListHttpComponent.updataTodo(): error updating todo:");
        console.error(err);


      }
    });


    this.editUser = null;
}

open(content: any) {
  this.modalService.open(content);
}

  ngOnInit(): void {
    this.reload();
  }

}
