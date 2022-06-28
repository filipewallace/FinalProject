import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Developer } from '../models/developer';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class DeveloperService {

  private url = environment.baseUrl + 'api/developers'

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


    index(): Observable<Developer[]> {
      return this.http.get<Developer[]>(this.url).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("DeveloperService.index(): Error returning index" + err)
          )
        })
      );
    }


    destroy(id: number): Observable<Developer>{
      return this.http.delete<Developer>(this.url+'/'+id, this.getHttpOption()).pipe(
        catchError ((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("DeveloperService.destroy(): Error deleting Developer" + err)
          )
        })
      );
    };


    show(id: number): Observable<Developer>{
      return this.http.get<Developer>(this.url+"/"+id, this.getHttpOption()).pipe(
        catchError ((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("DeveloperService.Show(): error retrieving Developer" + err)
          )
        })
      )
    };


    update(devUpdates: Developer): Observable<Developer>{

        return this.http.put<Developer>(this.url+"/"+devUpdates.id, devUpdates, this.getHttpOption()).pipe(
          catchError ((err: any) => {
            console.log(err);
            return throwError(
              () => new Error("DeveloperService.update(): error updating Developer" + err)
            )
          })
        )
    };


    create(dev: Developer): Observable<Developer> {
      return this.http.post<Developer>(this.url, dev, this.getHttpOption()).pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError(
            () => new Error('DeveloperService.create(): error creating Developer:' + err)
          )
        })
      )
    }
}
