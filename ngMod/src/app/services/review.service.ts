import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Platform } from '../models/platform';
import { Review } from '../models/review';
import { AuthService } from './auth.service';
import { Mods } from './../models/mods';
import { Game } from '../models/game';


@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  private url = environment.baseUrl + 'api/review'

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

   //list reviews by the user id
    indexReviewByUser(id:number): Observable<Review[]> {
      return this.http.get<Review[]>(this.url+"/"+id+"/user", this.getHttpOption()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("ReviewService.index(): Error returning a Review" + err)
          )
        })
      );
    }

    //list reviews by the mod id
    indexReviewByMod(id:number): Observable<Review[]> {
      return this.http.get<Review[]>(this.url+"/"+id+"/mod", this.getHttpOption()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("ReviewService.index(): Error returning a Review" + err)
          )
        })
      );
    }

  //send in mod id
    destroy(id: number): Observable<Review>{
      return this.http.delete<Review>(this.url+"/"+id+"/mod", this.getHttpOption()).pipe(
        catchError ((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("ReviewService.destroy(): Error deleting Review" + err)
          )
        })
      );
    };

    // send in mod id
    show(id: number): Observable<Review>{
      return this.http.get<Review>(this.url+"/"+id+"/mod", this.getHttpOption()).pipe(
        catchError ((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("ReviewService.Show(): error retrieving Review" + err)
          )
        })
      )
    };

    // send in mod id
    update(reviewUpdates: Review): Observable<Review>{

        return this.http.put<Review>(this.url+"/"+reviewUpdates.id, reviewUpdates, this.getHttpOption()).pipe(
          catchError ((err: any) => {
            console.log(err);
            return throwError(
              () => new Error("ReviewService.update(): error updating Review" + err)
            )
          })
        )
    };


    create(review: Review, id: number): Observable<Platform> {
      return this.http.post<Platform>(this.url+"/"+id+"/user", review, this.getHttpOption()).pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError(
            () => new Error('ReviewService.create(): error creating Review:' + err)
          )
        })
      )
    };



}
