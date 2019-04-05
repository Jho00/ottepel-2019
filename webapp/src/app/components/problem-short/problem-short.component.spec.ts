import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProblemShortComponent } from './problem-short.component';

describe('ProblemShortComponent', () => {
  let component: ProblemShortComponent;
  let fixture: ComponentFixture<ProblemShortComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProblemShortComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProblemShortComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
