import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MatDialogModule } from '@angular/material/dialog';
import { SearchpipePipe } from '../searchpipe.pipe';
import { MatPaginatorModule } from '@angular/material/paginator';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


import { MedicationviewComponent } from './medicationview.component';
import { By } from '@angular/platform-browser';

describe('MedicationviewComponent', () => {
  let component: MedicationviewComponent;
  let fixture: ComponentFixture<MedicationviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MedicationviewComponent, SearchpipePipe ],
      imports: [ HttpClientTestingModule, MatDialogModule, MatPaginatorModule, FormsModule, BrowserAnimationsModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MedicationviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should perform binding in HTML template', () => {
    const hostElement = fixture.nativeElement;
    const searchTextInput: HTMLInputElement = hostElement.querySelector('#st');
    // const searchText: HTMLInputElement = hostElement.querySelector('#disSearch');
    
    fixture.detectChanges();
    searchTextInput.value = 'acne';
    searchTextInput.dispatchEvent(new Event('input'));
    expect(component.searchtext).toBe('acne');
    
  });
});
