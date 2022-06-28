import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Publisher } from '../models/publisher';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class PublisherService {

  private url = environment.baseUrl + 'api/publisher'

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


    index(): Observable<Publisher[]> {
      return this.http.get<Publisher[]>(this.url, this.getHttpOption()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("PublisherService.index(): Error returning a Publisher" + err)
          )
        })
      );
    }


    destroy(id: number): Observable<Publisher>{
      return this.http.delete<Publisher>(this.url+'/'+id, this.getHttpOption()).pipe(
        catchError ((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("PublisherService.destroy(): Error deleting Publisher" + err)
          )
        })
      );
    };


    show(id: number): Observable<Publisher>{
      return this.http.get<Publisher>(this.url+"/"+id, this.getHttpOption()).pipe(
        catchError ((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("PublisherService.Show(): error retrieving Publisher" + err)
          )
        })
      )
    };


    update(publisherUpdates: Publisher): Observable<Publisher>{

        return this.http.put<Publisher>(this.url+"/"+publisherUpdates.id, publisherUpdates, this.getHttpOption()).pipe(
          catchError ((err: any) => {
            console.log(err);
            return throwError(
              () => new Error("PublisherService.update(): error updating Publisher" + err)
            )
          })
        )
    };


    create(publisher: Publisher): Observable<Publisher> {
      return this.http.post<Publisher>(this.url, publisher, this.getHttpOption()).pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError(
            () => new Error('PublisherService.create(): error creating Publisher:' + err)
          )
        })
      )
    }

}
