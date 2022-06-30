import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GameveiwComponent } from './gameveiw.component';

describe('GameveiwComponent', () => {
  let component: GameveiwComponent;
  let fixture: ComponentFixture<GameveiwComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GameveiwComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GameveiwComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
