import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ModMedia } from '../models/mod-media';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ModMediaService {

  private url = environment.baseUrl + 'api/modMedia'

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


    index(): Observable<ModMedia[]> {
      return this.http.get<ModMedia[]>(this.url, this.getHttpOption()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("Mods.index(): Error returning index" + err)
          )
        })
      );
    }


    destroy(id: number): Observable<ModMedia>{
      return this.http.delete<ModMedia>(this.url+'/'+id, this.getHttpOption()).pipe(
        catchError ((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("Mods.destroy(): Error deleting user" + err)
          )
        })
      );
    };


    show(id: number): Observable<ModMedia>{
      return this.http.get<ModMedia>(this.url+"/"+id, this.getHttpOption()).pipe(
        catchError ((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("Mods.Show(): error retrieving user" + err)
          )
        })
      )
    };


    update(modMediaUpdates: ModMedia): Observable<ModMedia>{

        return this.http.put<ModMedia>(this.url+"/"+modMediaUpdates.id, modMediaUpdates, this.getHttpOption()).pipe(
          catchError ((err: any) => {
            console.log(err);
            return throwError(
              () => new Error("ModsService.update(): error updating user" + err)
            )
          })
        )
    };


    create(media: ModMedia): Observable<ModMedia> {
      return this.http.post<ModMedia>(this.url, media, this.getHttpOption()).pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError(
            () => new Error('ModsService.create(): error creating todo:' + err)
          )
        })
      )
    }
}
