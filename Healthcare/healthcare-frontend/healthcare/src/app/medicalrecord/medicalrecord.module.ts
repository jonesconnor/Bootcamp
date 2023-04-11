import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { MrdisplayComponent } from './mrdisplay/mrdisplay.component';

import {MatCardModule} from '@angular/material/card';
import {MatTableModule} from '@angular/material/table';
import { TruncatePipe } from './truncate.pipe';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatButtonModule} from '@angular/material/button';
import { SearchPipe } from './search.pipe';
import { FormsModule } from '@angular/forms';
import { PatientModule } from '../patient/patient.module';




@NgModule({
  declarations: [
    MrdisplayComponent,
    TruncatePipe,
    SearchPipe
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    MatCardModule,
    MatTableModule,
    MatExpansionModule,
    MatButtonModule,
    MatPaginatorModule,
    FormsModule, 
    PatientModule
  ],
  exports: [
    MrdisplayComponent,
    TruncatePipe
  ]
})
export class MedicalrecordModule { }
