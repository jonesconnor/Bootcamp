import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { RouterService } from '../healthroute/Service/router.service';

@Injectable({
  providedIn: 'root'
})
export class NeutralGuard implements CanActivate {
  constructor(private router:RouterService){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    let role = sessionStorage.getItem('Role');
    if(role=="Admin"){
      this.router.openAdminDash();
    }
    else if(role=="ServiceProvider"){
      this.router.openSPDash();
    }
    else if(role=="Patient"){
      this.router.openPatientDash();
    }
      return true;
  }
  
}
