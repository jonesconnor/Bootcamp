import { BlogserveService } from './../blogserve.service';
import { Component } from '@angular/core';
import { Blog } from '../model/Blog';
import { MatDialog } from '@angular/material/dialog';
import { AddblogComponent } from '../addblog/addblog.component';

@Component({
  selector: 'app-viewblogs',
  templateUrl: './viewblogs.component.html',
  styleUrls: ['./viewblogs.component.css']
})
export class ViewblogsComponent {

  blogs!: Blog[];

  constructor(private blogService: BlogserveService, public dialog: MatDialog) { }

  ngOnInit() {
    this.blogService.getBlogs().subscribe((blogs: Blog[]) => {
      this.blogs = blogs;
    });
  }

  openDialog() {
    const dialogRef = this.dialog.open(AddblogComponent, {
      width: '500px'
    });

    dialogRef.afterClosed().subscribe(
      (res) => {
        let blog = {
          blogId: res.blogId,
          blogTitle: res.blogTitle,
          authorName: res.authorName,
          blogContent: res.blogContent
        };
        this.blogService.saveBlog(blog).subscribe(
          (result: any) => {
            console.log(result);
          },
          (err: any) => {
            console.log(err)
          }
        );
      }
    );
  }

  deleteBlog(blogId: number) {
    this.blogService.deleteBlog(blogId).subscribe(() => {
      this.blogs = this.blogs.filter((b: Blog) => {
        return b.blogId !== blogId;
      });
    });
  }

}
