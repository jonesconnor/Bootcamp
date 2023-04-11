import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

import { MatSnackBar } from '@angular/material/snack-bar';

import { ServiceProvider } from '../../models/serviceprovider';
import { ProvidersService } from '../../services/providers.service';

@Component({
  selector: 'app-update-provider',
  templateUrl: './update-provider.component.html',
  styleUrls: ['./update-provider.component.css'],
})
export class UpdateProviderComponent implements OnInit {
  updatedProvider: ServiceProvider = {} as ServiceProvider;
  // Need to get the providerId from the route
  providerId: string = '6426468f9b1ebff31207023d';

  constructor(private api: ProvidersService, private _snackBar: MatSnackBar) {}

  ngOnInit(): void {
    this.loadProviderDetails(this.providerId);
  }

  // Open a snackbar with the given message and action
  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action);
  }

  // Load the provider details
  loadProviderDetails(providerId: string) {
    this.api.getServiceProviderById(providerId).subscribe({
      next: (res) => {
        console.log(res);
        this.updatedProvider = res;
      },
      error: (error: any) => {
        this.openSnackBar(
          'Unable to load provider details. Try again later.',
          'Error'
        );
        console.log(error);
      },
    });
  }

  // Handle the editForm submission
  handleEditProvider(form: NgForm) {
    if (form.invalid) {
      return;
    }

    console.log(this.updatedProvider.id);

    // Update the provider details
    this.api
      .updateServiceProvider(this.updatedProvider.id!, this.updatedProvider)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.openSnackBar(
            'The provider details were updated successfully',
            'Success'
          );
          form.resetForm();
          this.loadProviderDetails(this.providerId);
        },
        error: (error: any) => {
          this.openSnackBar(
            'Unable to update provider details. Try again later.',
            'Error'
          );
          console.log(error);
        },
      });
  }
}
