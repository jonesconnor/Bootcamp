import { environment } from './../../environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Query } from './models/Query';

@Injectable({
  providedIn: 'root'
})
export class QueryService {

  token: any;
  private queryListSubject = new BehaviorSubject<Query[]>([]);
  public queryList = this.queryListSubject.asObservable();

  constructor(private httpclientobj: HttpClient) {
    this.token=sessionStorage.getItem("mytoken");
   }

  getQueries(): Observable<Array<Query>> {
    return this.httpclientobj.get<Array<Query>>(environment.queryAPI,
      {
        headers:new HttpHeaders().set('Authorization','Bearer ' + this.token)
      }).pipe(
        tap((queries: Query[]) => {
          this.queryListSubject.next(queries);
          })
      );
  }

  getQueriesByCustomerId(customerId: number): Observable<any> {
    return this.httpclientobj.get<Array<Query>>(environment.queryByCustomerIdAPI + customerId)
    .pipe(
      tap((queries: Query[]) => {
        this.queryListSubject.next(queries);
        })
    );
  }

  addQuery(query: Query): Observable<Query> {
    return this.httpclientobj.post(environment.queryAPI, query, {
      headers: new HttpHeaders({"Content-Type":"application/json"})
    }).pipe(
      tap((newQuery: Query) => {
        this.queryListSubject.next([...this.queryListSubject.value, newQuery]);
      })
    );
  }

}
