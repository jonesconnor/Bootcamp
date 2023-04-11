import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { RouteserveService } from './routeserve.service';

@Injectable({
  providedIn: 'root'
})
export class MycanactiveGuard implements CanActivate {


constructor(private routeobj : RouteserveService)
{

}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
  
   let status=sessionStorage.getItem("isLoggedin");
  
     if(status!="true")
     this.routeobj.openLogin();
     
     return true;
     
  }
  
}
