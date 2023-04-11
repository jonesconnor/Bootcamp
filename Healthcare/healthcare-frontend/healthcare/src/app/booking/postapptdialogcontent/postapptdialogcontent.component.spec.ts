import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostapptdialogcontentComponent } from './postapptdialogcontent.component';

describe('PostapptdialogcontentComponent', () => {
  let component: PostapptdialogcontentComponent;
  let fixture: ComponentFixture<PostapptdialogcontentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PostapptdialogcontentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostapptdialogcontentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
