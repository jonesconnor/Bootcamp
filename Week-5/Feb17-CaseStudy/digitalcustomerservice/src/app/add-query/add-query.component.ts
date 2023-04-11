import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-add-query',
  templateUrl: './add-query.component.html',
  styleUrls: ['./add-query.component.css']
})
export class AddQueryComponent {

  queryForm: FormGroup;

  constructor(private formBuilder: FormBuilder, public dialogRef: MatDialogRef<AddQueryComponent>) {
    this.queryForm = this.formBuilder.group({
      customerId:['', Validators.required],
      date_request:['', Validators.required],
      description:['', Validators.required]
    });
  }

  submitQuery() {
    if (this.queryForm.valid) {
      const formData = this.queryForm.value;
      this.dialogRef.close(formData);
    }
  }

  cancel() {
    this.queryForm.reset();
    this.dialogRef.close();
  }

}

