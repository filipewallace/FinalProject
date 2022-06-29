import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserModsComponent } from './user-mods.component';

describe('UserModsComponent', () => {
  let component: UserModsComponent;
  let fixture: ComponentFixture<UserModsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserModsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserModsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
