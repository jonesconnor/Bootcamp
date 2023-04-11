import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable} from 'rxjs';
import { Issue } from '../models/Issue';


@Injectable({
  providedIn: 'root'
})

export class IssueService {

  constructor(private httpclientobj: HttpClient) { }

  // Implement addIssue method using HttpClient for a saving a Issue details
  addIssue(issue: Observable<any> | Issue): Observable<any> {
    return this.httpclientobj.post('http://localhost:3000/issues', issue, {
      headers: new HttpHeaders({"Content-Type":"application/json"})
    });
  }

  getIssues(): Observable<any> {
    return this.httpclientobj.get<Array<Issue>>('http://localhost:3000/issues');
  }

  // Implement deleteIssue method to delete a issue by id
  deleteIssue(id: any): Observable<any> {
    return this.httpclientobj.delete(`http://localhost:3000/issues/${id}`);
  }
}
