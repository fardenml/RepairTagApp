import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MpCoveragePickerComponent } from './mp-coverage-picker.component';

describe('MpCoveragePickerComponent', () => {
  let component: MpCoveragePickerComponent;
  let fixture: ComponentFixture<MpCoveragePickerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MpCoveragePickerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MpCoveragePickerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
