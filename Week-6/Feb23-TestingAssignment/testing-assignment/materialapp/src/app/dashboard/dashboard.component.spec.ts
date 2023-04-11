import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { of, throwError } from 'rxjs';
import { EmpserveService } from '../empserve.service';
import { Employee } from '../model/emp';
import { RouteserveService } from '../routeserve.service';

import { DashboardComponent } from './dashboard.component';

describe('DashboardComponent', () => {
  let component: DashboardComponent;
  let fixture: ComponentFixture<DashboardComponent>;
  let empserveSpy: jasmine.SpyObj<EmpserveService>;
  let routeobjSpy: jasmine.SpyObj<RouteserveService>;

  beforeEach(async () => {
    empserveSpy = jasmine.createSpyObj('EmpserveService', ['getEmployees']);
    routeobjSpy = jasmine.createSpyObj('RouteserveService', ['openLogin']);

    await TestBed.configureTestingModule({
      declarations: [ DashboardComponent ],
      imports: [
        HttpClientModule,
        RouterTestingModule
      ],
      providers: [
        { provide: EmpserveService, useValue: empserveSpy },
        { provide: RouteserveService, useValue: routeobjSpy }
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DashboardComponent);
    empserveSpy = TestBed.inject(EmpserveService) as jasmine.SpyObj<EmpserveService>;
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load employee data', () => {
    const employees: Employee[] = [
      { id: 1, empName: 'John', role: 'Developer' },
      { id: 2, empName: 'Jane', role: 'Tester' }
    ];
    spyOn(empserveSpy, 'getEmployees').and.returnValue(of(employees));
    component.loadEmployee();
    expect(component.employees).toEqual(employees);
    expect(component.dataSource).toEqual(employees);
  });

  it('should handle error while loading employee data', () => {
    const errorResponse = { status: 404, statusText: 'Not Found' };
    spyOn(empserveSpy, 'getEmployees').and.returnValue(
      throwError(errorResponse)
    );
    component.loadEmployee();
    expect(component.errmsg).toEqual(errorResponse);
  });

  it('should logout and clear session storage', () => {
    component.logout();
    expect(routeobjSpy.openLogin).toHaveBeenCalled();
    expect(sessionStorage.clear).toHaveBeenCalled();
  });
});
