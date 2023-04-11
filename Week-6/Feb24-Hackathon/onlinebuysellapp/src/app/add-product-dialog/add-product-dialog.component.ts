import { MatDialogRef } from '@angular/material/dialog';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-product-dialog',
  templateUrl: './add-product-dialog.component.html',
  styleUrls: ['./add-product-dialog.component.css']
})
export class AddProductDialogComponent {
  productForm!: FormGroup;

  constructor(private formBuilder: FormBuilder, public dialogRef: MatDialogRef<AddProductDialogComponent>) {
    this.productForm = this.formBuilder.group({
      name:['', Validators.required],
      description:['', Validators.required],
      price:['', Validators.required]
    });
  }

  submitProduct() {
    if (this.productForm.valid) {
      const formData = this.productForm.value;
      this.dialogRef.close(formData);
    }
  }

  cancel() {
    this.productForm.reset();
    this.dialogRef.close();
  }
}
