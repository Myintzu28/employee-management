import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormGroupCheckComponent } from './form-group-check.component';

describe('FormGroupCheckComponent', () => {
  let component: FormGroupCheckComponent;
  let fixture: ComponentFixture<FormGroupCheckComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormGroupCheckComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FormGroupCheckComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
