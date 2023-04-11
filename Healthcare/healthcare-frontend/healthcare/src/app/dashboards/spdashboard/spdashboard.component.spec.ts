import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AppModule } from 'src/app/app.module';
import { DashboardsModule } from '../dashboards.module';

import { SPdashboardComponent } from './spdashboard.component';

describe('SPdasboardComponent', () => {
  let component: SPdashboardComponent;
  let fixture: ComponentFixture<SPdashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SPdashboardComponent],
      imports: [DashboardsModule, AppModule],
    }).compileComponents();

    fixture = TestBed.createComponent(SPdashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
