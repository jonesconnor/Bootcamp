import { TestBed } from '@angular/core/testing';
import { AppComponent } from './app.component';

// xdescribe('AppComponent', () => {
//   beforeEach(async () => {
//     await TestBed.configureTestingModule({
//       declarations: [
//         AppComponent
//       ],
//     }).compileComponents();
//   });

//   it('should create the app', () => {
//     const fixture = TestBed.createComponent(AppComponent);
//     const app = fixture.componentInstance;
//     expect(app).toBeTruthy();
//   });

//   it(`should have as title 'unittestapp'`, () => {
//     const fixture = TestBed.createComponent(AppComponent);
//     const app = fixture.componentInstance;
//     expect(app.title).toEqual('unittestapp');
//   });

//   it('should render title', () => {
//     const fixture = TestBed.createComponent(AppComponent);
//     fixture.detectChanges();
//     const compiled = fixture.nativeElement as HTMLElement;
//     expect(compiled.querySelector('.content span')?.textContent).toContain('unittestapp app is running!');
//   });
// });

describe('test Spec for AppComponent', () => {
  beforeEach(async () => {
    TestBed.configureTestingModule({
      declarations:[AppComponent]
    }).compileComponents();
  })

  it('Should create the App Component', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  })

  it('Should have a title Unit Testing Demo', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('Unit Testing Demo');
  })

  it(`Should display message 'Welcome to Angular'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges()
    const app = fixture.debugElement.nativeElement as HTMLElement;
    expect(app.querySelector('h1')?.textContent).toContain("Welcome to Angular");
  })

  it(`Should have button with text 'Click Me'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const app = fixture.debugElement.nativeElement as HTMLElement;
    expect(app.querySelector('button')?.textContent).toContain("Click Me");
  })

})
