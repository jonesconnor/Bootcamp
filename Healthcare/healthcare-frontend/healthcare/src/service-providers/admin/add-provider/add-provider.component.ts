import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';

import { MatSnackBar } from '@angular/material/snack-bar';

import { ServiceProvider } from '../../models/serviceprovider';
import { ProvidersService } from '../../services/providers.service';

@Component({
  selector: 'app-add-provider',
  templateUrl: './add-provider.component.html',
  styleUrls: ['./add-provider.component.css'],
})
export class AddProviderComponent {
  newProvider: ServiceProvider = {} as ServiceProvider;

  constructor(private api: ProvidersService, private _snackBar: MatSnackBar) {}

  // Open a snackbar with the given message and action
  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action);
  }

  // Handle the addForm submission
  handleAddProvider(form: NgForm) {
    if (form.invalid) {
      return;
    }

    // Add code to save new provider to database
    this.api.addServiceProvider(this.newProvider).subscribe({
      next: (res) => {
        console.log(res);
        this.openSnackBar('The provider was added successfully', 'Success');
        form.resetForm();
      },
      error: (error: any) => {
        this.openSnackBar('Unable to add provider. Try again later.', 'Error');
        console.log(error);
      },
    });
  }
}
