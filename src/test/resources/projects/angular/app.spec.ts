import { ComponentFixture, ComponentFixtureAutoDetect, TestBed, waitForAsync } from '@angular/core/testing';
import { provideRouter } from '@angular/router';

import { App } from './app';

describe('App Component', () => {
  let comp: App;
  let fixture: ComponentFixture<App>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      providers: [provideRouter([]), { provide: ComponentFixtureAutoDetect, useValue: true }],
      declarations: [App],
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(App);
    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('should have appName', () => {
      expect(comp.appName()).toBe('test');
    });
  });
});
