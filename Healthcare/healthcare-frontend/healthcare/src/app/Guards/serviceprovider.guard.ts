import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import jwtDecode from 'jwt-decode';
import { Observable } from 'rxjs';
import { RouterService } from '../healthroute/Service/router.service';

@Injectable({
  providedIn: 'root'
})
export class ServiceproviderGuard implements CanActivate {
  constructor(private router:RouterService){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      let token:any = sessionStorage.getItem('bearerToken');
      if(token==null){
        console.log("token null");
        this.router.openLanding();
        return true;
      }
      let decoded:any = jwtDecode(token);
      let role:string = decoded['Role'];
      if(role!='ServiceProvider'){
        console.log("Guarded for SPs");
        this.router.openLanding();
      }
      return true;
  }
  
}
