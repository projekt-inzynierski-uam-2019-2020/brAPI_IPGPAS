import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DatabasesPageComponent } from './databases-page.component';

describe('DatabasesPageComponent', () => {
  let component: DatabasesPageComponent;
  let fixture: ComponentFixture<DatabasesPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DatabasesPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DatabasesPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
