import { QueryService } from './../query.service';
import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AddQueryComponent } from '../add-query/add-query.component';
import { Query } from '../models/Query';

@Component({
  selector: 'app-customer-dashboard',
  templateUrl: './customer-dashboard.component.html',
  styleUrls: ['./customer-dashboard.component.css']
})
export class CustomerDashboardComponent {

  constructor(public dialog: MatDialog, private queryservice : QueryService) {}

  openDialog() {
    const dialogRef = this.dialog.open(AddQueryComponent, {
      width: '500px'
    });

    dialogRef.afterClosed().subscribe(
      (res) => {
        if (res && Object.keys(res).length > 0) {
          this.queryservice.addQuery(res).subscribe(
            (result: any) => {
              console.log(result);
            },
            (err: any) => {
              console.log(err);
            }
          );
        }
      }
    )
  }

}
