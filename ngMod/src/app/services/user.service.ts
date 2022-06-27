import { catchError, Observable, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url = environment.baseUrl + 'api/user'

  constructor(private http: HttpClient, private auth: AuthService) { }

    getHttpOption(){
      let option = {
        headers: {
          Authorization: 'Basic' + this.auth.getCredentials(),
          'X-Requested-With': 'XMLHttpRequest'
        },
      };
      return option;
    }


    index(): Observable<User[]> {
      return this.http.get<User[]>(this.url, this.getHttpOption()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("UserService.index(): Error returning index" + err)
          )
        })
      );
    }


    destroy(id: number): Observable<User>{
      return this.http.delete<User>(this.url+'/'+id, this.getHttpOption()).pipe(
        catchError ((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("UserService.destroy(): Error deleting user" + err)
          )
        })
      );
    };


    show(id: number): Observable<User>{
      return this.http.get<User>(this.url+"/"+id, this.getHttpOption()).pipe(
        catchError ((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("UserService.Show(): error retrieving user" + err)
          )
        })
      )
    };


    update(userUpdate: User): Observable<User>{

        return this.http.put<User>(this.url+"/"+userUpdate.id, userUpdate, this.getHttpOption()).pipe(
          catchError ((err: any) => {
            console.log(err);
            return throwError(
              () => new Error("UserService.update(): error updating user" + err)
            )
          })
        )
    };








}
