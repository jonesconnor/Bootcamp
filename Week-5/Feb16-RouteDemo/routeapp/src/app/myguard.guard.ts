import { RouterserveService } from './routerserve.service';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MyguardGuard implements CanActivate {

  constructor(private routeobj: RouterserveService) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

      let status = sessionStorage.getItem("isLoggedIn");

      if (status) {
        return true
      }
      this.routeobj.openLogin();
      return false;
  }

}
