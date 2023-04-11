import { RouterService } from './router.service';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdmindashcanactiveGuard implements CanActivate {

  constructor(private routeobj: RouterService) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

      let token = sessionStorage.getItem("mytoken");
      if (!token || token=="undefined") {
        this.routeobj.openLogin();
      }
    return true;
  }

}
