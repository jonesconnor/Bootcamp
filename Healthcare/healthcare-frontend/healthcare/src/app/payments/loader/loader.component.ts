import { Component, Input } from '@angular/core';
import { RouterService } from 'src/app/healthroute/Service/router.service';

@Component({
  selector: 'app-loader',
  templateUrl: './loader.component.html',
  styleUrls: ['./loader.component.css'],
})
export class LoaderComponent {
  @Input()
  error!: string;

  @Input()
  statusMessage: string = '';

  constructor(private routerService: RouterService) {}

  openLanding() {
    this.routerService.openLanding();
  }
}
