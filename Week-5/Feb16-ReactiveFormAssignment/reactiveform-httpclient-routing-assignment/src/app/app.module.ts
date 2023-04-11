import { IssueService } from './services/issue.service';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { IssueComponent } from './issue/issue.component';
import { HttpClientModule } from '@angular/common/http';

import { IssuesListComponent } from './issues-list/issues-list.component';

@NgModule({
  declarations: [
    AppComponent,
    IssueComponent,
    IssuesListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [IssueService],
  bootstrap: [AppComponent]
})
export class AppModule { }
