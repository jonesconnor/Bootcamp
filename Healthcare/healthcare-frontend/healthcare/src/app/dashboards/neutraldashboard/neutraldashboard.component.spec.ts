import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AppModule } from 'src/app/app.module';
import { DashboardsModule } from '../dashboards.module';

import { NeutraldashboardComponent } from './neutraldashboard.component';

describe('NeutraldashboardComponent', () => {
  let component: NeutraldashboardComponent;
  let fixture: ComponentFixture<NeutraldashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NeutraldashboardComponent ],
      imports:[DashboardsModule,AppModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NeutraldashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
