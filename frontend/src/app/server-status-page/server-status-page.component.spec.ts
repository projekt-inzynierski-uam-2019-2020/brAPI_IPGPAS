import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServerStatusPageComponent } from './server-status-page.component';

describe('ServerStatusPageComponent', () => {
  let component: ServerStatusPageComponent;
  let fixture: ComponentFixture<ServerStatusPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServerStatusPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServerStatusPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
