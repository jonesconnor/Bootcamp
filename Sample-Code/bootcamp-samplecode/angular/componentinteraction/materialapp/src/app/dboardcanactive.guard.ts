import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { RouteserveService } from './services/routeserve.service';

@Injectable({
  providedIn: 'root'
})
export class DboardcanactiveGuard implements CanActivate {
  constructor(private routeoj: RouteserveService) {
    
  }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    
 let tok=sessionStorage.getItem("mytoken")

 if (!tok|| tok=='undefined' )
this.routeoj.openLogin();

return true;



  }
  
}
