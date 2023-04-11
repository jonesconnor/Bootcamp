import { NgModule } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule} from '@angular/material/button';
import { MatMenuModule } from '@angular/material/menu';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatFormFieldModule} from '@angular/material/form-field';
import { MatInputModule} from '@angular/material/input';
import { MatCheckboxModule} from '@angular/material/checkbox';
import { MatDatepickerModule} from '@angular/material/datepicker';
import {MatSelectModule} from '@angular/material/select';
import {MatTableModule} from '@angular/material/table';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatNativeDateModule} from "@angular/material/core";
import {MatGridListModule} from '@angular/material/grid-list';

@NgModule({
  imports: [
    MatMenuModule,
    MatToolbarModule,
    MatIconModule,
    MatCardModule,
    MatSlideToggleModule, 
    MatInputModule, 
    MatFormFieldModule,
    MatCheckboxModule, 
    MatDatepickerModule, 
    MatSelectModule,
    MatExpansionModule,
    MatSnackBarModule,
    MatTableModule,
    MatSidenavModule,
    MatNativeDateModule,
    MatGridListModule
  ],
  exports: [
    MatButtonModule,
    MatMenuModule,
    MatToolbarModule,
    MatIconModule,
    MatCardModule,
    MatSlideToggleModule, 
    MatInputModule, 
    MatFormFieldModule,
    MatCheckboxModule, 
    MatDatepickerModule,
    MatSelectModule,
    MatExpansionModule,
    MatSnackBarModule,
    MatTableModule,
    MatSidenavModule,
    MatNativeDateModule,
    MatGridListModule
  ]
})
export class MaterialModule {}