import { Platform } from './../models/platform';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class PlatformService {

  private url = environment.baseUrl + 'api/platform'

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


    index(): Observable<Platform[]> {
      return this.http.get<Platform[]>(this.url, this.getHttpOption()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("PlatformService.index(): Error returning a Platform" + err)
          )
        })
      );
    }


    destroy(id: number): Observable<Platform>{
      return this.http.delete<Platform>(this.url+'/'+id, this.getHttpOption()).pipe(
        catchError ((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("PlatformService.destroy(): Error deleting Platform" + err)
          )
        })
      );
    };


    show(id: number): Observable<Platform>{
      return this.http.get<Platform>(this.url+"/"+id, this.getHttpOption()).pipe(
        catchError ((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("PlatformService.Show(): error retrieving Platform" + err)
          )
        })
      )
    };


    update(platformUpdates: Platform): Observable<Platform>{

        return this.http.put<Platform>(this.url+"/"+platformUpdates.id, platformUpdates, this.getHttpOption()).pipe(
          catchError ((err: any) => {
            console.log(err);
            return throwError(
              () => new Error("PlatformService.update(): error updating Platform" + err)
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
    }


}
