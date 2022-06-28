import { Post } from './../models/post';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class PostService {


  private url = environment.baseUrl + 'api/post'

  constructor(private http: HttpClient, private auth: AuthService) { }

    getHttpOption(){
      let option = {
        headers: {
          Authorization: 'Basic ' + this.auth.getCredentials(),
          'X-Requested-With': 'XMLHttpRequest'
        },
      };
      return option;
    }


    index(): Observable<Post[]> {
      return this.http.get<Post[]>(this.url, this.getHttpOption()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("PostService.index(): Error returning a Post" + err)
          )
        })
      );
    }


    destroy(id: number): Observable<Post>{
      return this.http.delete<Post>(this.url+'/'+id, this.getHttpOption()).pipe(
        catchError ((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("PostService.destroy(): Error deleting Post" + err)
          )
        })
      );
    };


    show(id: number): Observable<Post>{
      return this.http.get<Post>(this.url+"/"+id, this.getHttpOption()).pipe(
        catchError ((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("PostService.Show(): error retrieving Post" + err)
          )
        })
      )
    };


    update(postUpdates: Post): Observable<Post>{

        return this.http.put<Post>(this.url+"/"+postUpdates.id, postUpdates, this.getHttpOption()).pipe(
          catchError ((err: any) => {
            console.log(err);
            return throwError(
              () => new Error("PostService.update(): error updating Post" + err)
            )
          })
        )
    };


    create(post: Post): Observable<Post> {
      return this.http.post<Post>(this.url, post, this.getHttpOption()).pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError(
            () => new Error('PostService.create(): error creating Post:' + err)
          )
        })
      )
    }

}
