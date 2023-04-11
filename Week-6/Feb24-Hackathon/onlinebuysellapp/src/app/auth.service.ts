import { RouterService } from './router.service';
import { UserhandlerService } from './userhandler.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, map, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private currentUserSubject: BehaviorSubject<any>;
  public currentUser: Observable<any>;

  constructor(private userhandler: UserhandlerService, private route: RouterService) {
    this.currentUserSubject = new BehaviorSubject<any>(JSON.parse(localStorage.getItem('currentUser') as string));
    this.currentUser = this.currentUserSubject.asObservable();
   }

   public getCurrentUserValue(): any {
    return this.currentUserSubject.value;
   }

   login(email: string, password: string) {
    return this.userhandler.getUsers().pipe(
      map(users => users.find(u => u.email === email && u.password === password)),
      tap(user => {
        if (user) {
          localStorage.setItem('currentUser', JSON.stringify(user));
          this.currentUserSubject.next(user);
        } else {
          throw new Error('Invalid credentials');
        }
      })
    );
   }

   logout() {
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
    this.route.openLogin();
   }
}
