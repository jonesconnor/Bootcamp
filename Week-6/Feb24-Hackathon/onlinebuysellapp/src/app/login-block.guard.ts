import { RouterService } from './router.service';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { map, Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class LoginBlockGuard implements CanActivate {

  constructor(private auth: AuthService, private routeobj: RouterService) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      return this.auth.currentUser.pipe(
        map(user => {
          if (user) {
            return true;
          } else {
            this.routeobj.openLogin();
            return false;
          }
        })
      );
  }

}
