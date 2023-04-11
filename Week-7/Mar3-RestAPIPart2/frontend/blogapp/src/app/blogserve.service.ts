import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Blog } from './model/Blog';

@Injectable({
  providedIn: 'root'
})
export class BlogserveService {

  private blogListSubject = new BehaviorSubject<Blog[]>([]);
  public blogList = this.blogListSubject.asObservable();

  constructor(private httpclientobj: HttpClient) { }

  getBlogs(): Observable<Array<Blog>> {
    return this.httpclientobj.get<Array<Blog>>("http://localhost:9091/viewblogs")
    .pipe(
      tap((blogs: Blog[]) => {
        this.blogListSubject.next(blogs);
      })
    );
  }

  getBlogById(blogId: number): Observable<Blog> {
    return this.httpclientobj.get<Blog>(`http://localhost:9091/blog/${blogId}`);
  }

  saveBlog(blog: Blog): Observable<Blog> {
    return this.httpclientobj.post("http://localhost:9091/saveblog", blog, {
      headers: new HttpHeaders({"Content-Type": "application/json"})
    }).pipe(
      tap((newBlog: Blog) => {
        const updatedList = [...this.blogListSubject.value, newBlog];
        this.blogListSubject.next(updatedList);
      })
    );
  }

  deleteBlog(blogId: number): Observable<Blog> {
    return this.httpclientobj.delete(`http://localhost:9091/deleteblog/${blogId}`).pipe(
      tap(() => {
        const updatedList = this.blogListSubject.value.filter((b: Blog) => {
          return b.blogId !== blogId;
        });
        this.blogListSubject.next(updatedList);
      })
    )
  }

}
