import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Platform } from '../models/platform';
import { Review } from '../models/review';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  private url = environment.baseUrl + 'api/FixMe'

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


    index(): Observable<Review[]> {
      return this.http.get<Review[]>(this.url, this.getHttpOption()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("ReviewService.index(): Error returning a Review" + err)
          )
        })
      );
    }


    destroy(id: number): Observable<Review>{
      return this.http.delete<Review>(this.url+'/'+id, this.getHttpOption()).pipe(
        catchError ((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("ReviewService.destroy(): Error deleting Review" + err)
          )
        })
      );
    };


    show(id: number): Observable<Review>{
      return this.http.get<Review>(this.url+"/"+id, this.getHttpOption()).pipe(
        catchError ((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("ReviewService.Show(): error retrieving Review" + err)
          )
        })
      )
    };


    update(platformUpdates: Review): Observable<Review>{

        return this.http.put<Review>(this.url+"/"+platformUpdates.id, platformUpdates, this.getHttpOption()).pipe(
          catchError ((err: any) => {
            console.log(err);
            return throwError(
              () => new Error("ReviewService.update(): error updating Review" + err)
            )
          })
        )
    };


    create(platform: Platform): Observable<Platform> {
      return this.http.post<Platform>(this.url, platform, this.getHttpOption()).pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError(
            () => new Error('PlatformService.create(): error creating Platform:' + err)
          )
        })
      )
    };



}
