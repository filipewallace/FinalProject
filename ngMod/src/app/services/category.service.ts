import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Category } from '../models/category';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private url = environment.baseUrl + 'api/category'

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


    index(): Observable<Category[]> {
      return this.http.get<Category[]>(this.url, this.getHttpOption()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("CategoryService.index(): Error returning index" + err)
          )
        })
      );
    }


    destroy(id: number): Observable<Category>{
      return this.http.delete<Category>(this.url+'/'+id, this.getHttpOption()).pipe(
        catchError ((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("CategoryService.destroy(): Error deleting Category" + err)
          )
        })
      );
    };


    show(id: number): Observable<Category>{
      return this.http.get<Category>(this.url+"/"+id, this.getHttpOption()).pipe(
        catchError ((err: any) => {
          console.log(err);
          return throwError(
            () => new Error("Category.Show(): error retrieving Category" + err)
          )
        })
      )
    };


    update(catUpdates: Category): Observable<Category>{

        return this.http.put<Category>(this.url+"/"+catUpdates.id, catUpdates, this.getHttpOption()).pipe(
          catchError ((err: any) => {
            console.log(err);
            return throwError(
              () => new Error("Category.update(): error updating Category" + err)
            )
          })
        )
    };


    create(cat: Category): Observable<Category> {
      return this.http.post<Category>(this.url, cat, this.getHttpOption()).pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError(
            () => new Error('CategoryService.create(): error creating Category:' + err)
          )
        })
      )
    };






}
