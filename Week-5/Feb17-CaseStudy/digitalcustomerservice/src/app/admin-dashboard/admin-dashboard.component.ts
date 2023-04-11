import { Component } from '@angular/core';
import { Query } from '../models/Query';
import { QueryService } from '../query.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent {

  dataSource: any;
  displayedColumns : string[] = ['id', 'customerId', 'date_request', 'description'];
  queries: Array<Query> = [];

  constructor(private queryserviceobj : QueryService) {}

  ngOnInit(): void {
    this.loadQueries();
  }

  loadQueries() {
    this.queryserviceobj.getQueries().subscribe(
      (res:any) => {
        this.queries = res;
        this.dataSource = this.queries;
      },
      (err:any) => {
        console.log(err);
      }
    )
    this.queryserviceobj.queryList.subscribe((queries: Query []) => {
      this.queries = queries;
      this.dataSource = this.queries;
    });
  }

}
