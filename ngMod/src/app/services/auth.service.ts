import { User } from './../models/user';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, tap, throwError} from 'rxjs';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root',
})
export class AuthService {
  // Set port number to server's port
  //private baseUrl = 'http://localhost:8086/';
  private url = environment.baseUrl;


  constructor(private http: HttpClient) {

  }

  login(username: string, password: string): Observable<User> {
    // Make credentials
    const credentials = this.generateBasicAuthCredentials(username, password);
    // Send credentials as Authorization header specifying Basic HTTP authentication
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest',
      }),
    };

    // Create GET request to authenticate credentials
    //httpOptions
    return this.http.get<User>(this.url + 'authenticate', httpOptions).pipe(
      tap((newUser) => {
        // While credentials are stored in browser localStorage, we consider
        // ourselves logged in.
        localStorage.setItem('credentials', credentials);
        if(newUser != null && newUser.role != null){
          localStorage.setItem('user', newUser.role.toString());

        }
        return newUser;
      }),
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('AuthService.login(): error logging in user.')
        );
      })
    );
  }

  register(user: User): Observable<User> {
    // Create POST request to register a new account
    return this.http.post<User>(this.url + 'register', user).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('AuthService.register(): error registering user.')
        );
      })
    );
  }
 //Check user Role
  getLoggedInUser(): Observable<User> {
    if (! this.checkLogin()) {
      return throwError(
        () => { new Error('Not logged in.')}
      )
    }
    let httpOptions = {
      headers: {
        Authorization: 'Basic ' + this.getCredentials(),
        'X-Requested-with': 'XMLHttpRequest'
      }
    };
    return this.http.get<User>(environment.baseUrl+'authenticate', httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error(
              'UserService.getUserById(): error retrieving user: ' + err
            )
        );
      })
    );
  };

  logout(): void {
    localStorage.removeItem('credentials');
    localStorage.removeItem('user');
  }

  checkLogin(): boolean {
    if (localStorage.getItem('credentials')) {
      return true;
    } else {

      return false;
    }
  }




  generateBasicAuthCredentials(username: string, password: string): string {
    return btoa(`${username}:${password}`);
  }

  getCredentials(): string | null {
    return localStorage.getItem('credentials');
  }


  isAdmin(): boolean {
    let item = localStorage.getItem('user');

    if(item === "ROLE_ADMIN"){
      return true;
    } else {

      return false;
    }
  }







}
