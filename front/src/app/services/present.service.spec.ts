import { TestBed } from '@angular/core/testing';

import { PresentService } from './present.service';

describe('DrawService', () => {
  let service: PresentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PresentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
