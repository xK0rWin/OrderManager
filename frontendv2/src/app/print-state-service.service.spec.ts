import { TestBed } from '@angular/core/testing';

import { PrintStateServiceService } from './print-state-service.service';

describe('PrintStateServiceService', () => {
  let service: PrintStateServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PrintStateServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
