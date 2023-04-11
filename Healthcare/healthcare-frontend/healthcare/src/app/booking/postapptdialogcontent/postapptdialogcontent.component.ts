import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-postapptdialogcontent',
  templateUrl: './postapptdialogcontent.component.html',
  styleUrls: ['./postapptdialogcontent.component.css']
})
export class PostapptdialogcontentComponent {
  postBookingForm!: FormGroup;

  constructor(private formBuilder: FormBuilder, private dialogRef: MatDialogRef<PostapptdialogcontentComponent>) {
    this.postBookingForm = this.formBuilder.group({
      notes:['', Validators.required],
      needsPrescription: false
    });
  }

  onNext() {
    if (this.postBookingForm.valid) {
      const formData = this.postBookingForm.value;
      this.dialogRef.close(formData);
    }
  }
}
