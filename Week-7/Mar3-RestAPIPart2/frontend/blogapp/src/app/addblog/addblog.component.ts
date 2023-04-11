import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-addblog',
  templateUrl: './addblog.component.html',
  styleUrls: ['./addblog.component.css']
})
export class AddblogComponent implements OnInit{

  blogForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private dialogRef: MatDialogRef<AddblogComponent>
  ) { }

  ngOnInit() {
    this.blogForm = this.formBuilder.group({
      blogId: ['', Validators.required],
      blogTitle: ['', Validators.required],
      authorName: ['', Validators.required],
      blogContent: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.blogForm.valid) {
      this.dialogRef.close(this.blogForm.value);
    }
  }

  onCancel() {
    this.blogForm.reset();
    this.dialogRef.close();
  }

}
