import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RouterService } from 'src/app/healthroute/Service/router.service';

@Component({
  selector: 'app-side-nav',
  templateUrl: './side-nav.component.html',
  styleUrls: ['./side-nav.component.css'],
})
export class SideNavComponent {
  currentRoute?: string;
  currentSP?: string;
  role: string | null = sessionStorage.getItem('Role');

  constructor(private router: RouterService, private route: ActivatedRoute) {
    // Get the current route
    this.currentRoute = this.route.snapshot.routeConfig!.path;
    this.currentSP = sessionStorage.getItem('userId')!;
  }

  goToReviews() {
    if (this.role === 'ServiceProvider') {
      this.router.openViewAllRevieweeReviews(sessionStorage.getItem('userId')!);
    }
  }
}
