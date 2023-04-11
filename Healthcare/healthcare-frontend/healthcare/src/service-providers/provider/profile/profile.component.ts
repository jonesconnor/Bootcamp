import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

import { MatSnackBar } from '@angular/material/snack-bar';

import { ServiceProvider } from '../../models/serviceprovider';
import { ProvidersService } from '../../services/providers.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit {
  pageTitle = 'My Profile';
  updatedProvider: ServiceProvider = {} as ServiceProvider;
  providerId?: string;

  constructor(private api: ProvidersService, private _snackBar: MatSnackBar) {}

  ngOnInit(): void {
    if (sessionStorage.getItem('userId') === null) {
      // Add code to redirect to login
      return;
    }

    this.providerId = sessionStorage.getItem('userId')!;
    this.loadProviderDetails(this.providerId);
  }

  // Open a snackbar with the given message, action and duration
  openSnackBar(message: string, action: string, duration: number = 0) {
    if (duration) {
      return this._snackBar.open(message, action, { duration: duration });
    }
    return this._snackBar.open(message, action);
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
          'Unable to load profile. Try again later.',
          'Dismiss'
        );
        console.log(error);
      },
    });
  }

  // Save profile changes
  handleSaveChanges(form: NgForm) {
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
          this.openSnackBar('The provider was updated successfully', 'Dismiss');
          form.resetForm();
          this.loadProviderDetails(this.providerId!);
        },
        error: (error: any) => {
          this.openSnackBar(
            'Unable to update profile. Try again later.',
            'Dismiss'
          );
          console.log(error);
        },
      });
  }

  // Delete the provider profile after getting confirmation from the user
  handleDeleteProfile(providerId: string | undefined) {
    if (providerId) {
      console.log(providerId);
      const snackbarRef = this.openSnackBar(
        'Are you sure you want to delete your profile?',
        'Confirm',
        5000 // 5 seconds
      );

      snackbarRef.onAction().subscribe(() => {
        this.api.deleteServiceProvider(providerId).subscribe({
          next: (res) => {
            console.log(res);
            const snackbarRef = this.openSnackBar(
              'The provider was deleted successfully',
              'Dismiss',
              3000 // 3 seconds
            );

            snackbarRef.afterDismissed().subscribe(() => {
              // TODO: Add code to redirect to login page
              alert('Redirect to login page');
            });
          },
          error: (error: any) => {
            this.openSnackBar(
              'Unable to delete profile. Try again later.',
              'Dismiss'
            );
            console.log(error);
          },
        });
        console.log('The profile was deleted');
      });
    }
  }
}
