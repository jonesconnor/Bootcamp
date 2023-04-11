import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostapptdialogComponent } from './postapptdialog.component';

describe('PostapptdialogComponent', () => {
  let component: PostapptdialogComponent;
  let fixture: ComponentFixture<PostapptdialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PostapptdialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostapptdialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
