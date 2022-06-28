import { Rating } from './../models/rating';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class RatingService {

  private url = environment.baseUrl + 'api/rating'

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


    index(): Observable<Rating[]> {
      return this.http.get<Rating[]>(this.url, this.getHttpOption()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("RatingService.index(): Error returning a Rating" + err)
          )
        })
      );
    }


    destroy(id: number): Observable<Rating>{
      return this.http.delete<Rating>(this.url+'/'+id, this.getHttpOption()).pipe(
        catchError ((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("RatingService.destroy(): Error deleting Rating" + err)
          )
        })
      );
    };


    show(id: number): Observable<Rating>{
      return this.http.get<Rating>(this.url+"/"+id, this.getHttpOption()).pipe(
        catchError ((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("RatingService.Show(): error retrieving Rating" + err)
          )
        })
      )
    };


    update(ratingUpdates: Rating): Observable<Rating>{

        return this.http.put<Rating>(this.url+"/"+ratingUpdates.id, ratingUpdates, this.getHttpOption()).pipe(
          catchError ((err: any) => {
            console.log(err);
            return throwError(
              () => new Error("RatingService.update(): error updating Rating" + err)
            )
          })
        )
    };


    create(rating: Rating): Observable<Rating> {
      return this.http.post<Rating>(this.url, rating, this.getHttpOption()).pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError(
            () => new Error('RatingService.create(): error creating Rating:' + err)
          )
        })
      )
    };

}
