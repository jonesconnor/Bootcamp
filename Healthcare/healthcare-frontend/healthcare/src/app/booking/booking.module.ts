import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreateComponent } from './create/create.component';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from './material/material.module';
import { SearchproviderComponent } from './searchprovider/searchprovider.component';
import { SearchfilterPipe } from './pipe/searchfilter.pipe';
import { PostapptdialogComponent } from './postapptdialog/postapptdialog.component';
import { PostapptdialogcontentComponent } from './postapptdialogcontent/postapptdialogcontent.component';


@NgModule({
  declarations: [
    CreateComponent,
    SearchproviderComponent,
    SearchfilterPipe,
    PostapptdialogComponent,
    PostapptdialogcontentComponent
  ],
  imports: [
    CommonModule,
    BrowserModule, 
    FormsModule, 
    HttpClientModule,
    ReactiveFormsModule, 
    MaterialModule
  ], exports:[
    CreateComponent, 
    SearchproviderComponent,
    SearchfilterPipe
  ]
})
export class BookingModule {}
