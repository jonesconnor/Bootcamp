import { QueryService } from './../query.service';
import { Component } from '@angular/core';
import { Query } from '../models/Query';

@Component({
  selector: 'app-view-query',
  templateUrl: './view-query.component.html',
  styleUrls: ['./view-query.component.css']
})
export class ViewQueryComponent {

  dataSource:any;
  displayedColumns : string[] = ['id', 'date_request', 'description'];
  queries: Array<Query> = [];

  constructor(private queryserviceobj : QueryService) {}

  ngOnInit(): void {
    this.loadQueries();
  }

  loadQueries() {
    this.queryserviceobj.getQueriesByCustomerId(1).subscribe(
      (res:any) => {
        this.queries = res;
        this.dataSource = this.queries;
        console.log(res);
      },
      (err:any) => {
        console.log(err);
      }
    );
    this.queryserviceobj.queryList.subscribe((queries: Query []) => {
      this.queries = queries;
      this.dataSource = this.queries;
    });
  }


}
