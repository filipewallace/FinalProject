import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Game } from '../models/game';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private url = environment.baseUrl + 'api/games'

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


    index(): Observable<Game[]> {
      return this.http.get<Game[]>(this.url, this.getHttpOption()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("GamesService.index(): Error returning index" + err)
          )
        })
      );
    }


    destroy(id: number): Observable<Game>{
      return this.http.delete<Game>(this.url+'/'+id, this.getHttpOption()).pipe(
        catchError ((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("GamesService.destroy(): Error deleting a game" + err)
          )
        })
      );
    };


    show(id: number): Observable<Game>{
      return this.http.get<Game>(this.url+"/"+id, this.getHttpOption()).pipe(
        catchError ((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("GameService.Show(): error retrieving a game" + err)
          )
        })
      )
    };


    update(gameUpdates: Game): Observable<Game>{

        return this.http.put<Game>(this.url+"/"+gameUpdates.id, gameUpdates, this.getHttpOption()).pipe(
          catchError ((err: any) => {
            console.log(err);
            return throwError(
              () => new Error("GameService.update(): error updating a game" + err)
            )
          })
        )
    };


    create(game: Game): Observable<Game> {
      return this.http.post<Game>(this.url, game, this.getHttpOption()).pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError(
            () => new Error('GameService.create(): error creating a game:' + err)
          )
        })
      )
    }


}
