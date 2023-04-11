import { IssueService } from 'src/app/services/issue.service';
import { Component, OnInit } from '@angular/core';
import { Issue } from '../models/Issue';

@Component({
  selector: 'app-issues-list',
  templateUrl: './issues-list.component.html',
  styleUrls: ['./issues-list.component.css']
})
export class IssuesListComponent implements OnInit {

  issues:Issue[]=[]

  constructor(private issueserviceobj: IssueService) { }

  // Write logic to get all issues from IssueService
  ngOnInit() {
    this.issueserviceobj.getIssues().subscribe(
      (res : any) => {
        this.issues=res;
        console.log(res);
      },
      (err : any) => {
        console.log(err);
      }
    );
  }

  // Implement deleteIssue method to delete the issue
  deleteIssue(id: any) {
    this.issueserviceobj.deleteIssue(id).subscribe(
      (res : any) => {
        console.log(res);
        let index = this.issues.findIndex(issue => issue.id === id);
        this.issues.splice(index, 1);
      },
      (err : any) => {
        console.log(err);
      }
    )
  }

}
