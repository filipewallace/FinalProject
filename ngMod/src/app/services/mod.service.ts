import { Mods } from './../models/mods';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ModService {

  private url = environment.baseUrl + 'api/mods'

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


    index(): Observable<Mods[]> {
      return this.http.get<Mods[]>(this.url).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("Mods.index(): Error returning index" + err)
          )
        })
      );
    }


    destroy(id: number): Observable<Mods>{
      return this.http.delete<Mods>(this.url+'/'+id, this.getHttpOption()).pipe(
        catchError ((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("Mods.destroy(): Error deleting user" + err)
          )
        })
      );
    };


    show(id: number): Observable<Mods>{
      return this.http.get<Mods>(this.url+"/"+id, this.getHttpOption()).pipe(
        catchError ((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("Mods.Show(): error retrieving user" + err)
          )
        })
      )
    };


    update(modUpdates: Mods): Observable<Mods>{

        return this.http.put<Mods>(this.url+"/"+modUpdates.id, modUpdates, this.getHttpOption()).pipe(
          catchError ((err: any) => {
            console.log(err);
            return throwError(
              () => new Error("ModsService.update(): error updating user" + err)
            )
          })
        )
    };


    create(mod: Mods): Observable<Mods> {
      return this.http.post<Mods>(this.url, mod, this.getHttpOption()).pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError(
            () => new Error('ModsService.create(): error creating todo:' + err)
          )
        })
      )
    }






}
