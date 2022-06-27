import { TestBed } from '@angular/core/testing';

import { ModMediaService } from './mod-media.service';

describe('ModMediaService', () => {
  let service: ModMediaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ModMediaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
