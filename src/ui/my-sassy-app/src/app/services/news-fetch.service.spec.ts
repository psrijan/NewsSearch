import { TestBed } from '@angular/core/testing';

import { NewsFetchService } from './news-fetch.service';

describe('NewsFetchService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: NewsFetchService = TestBed.get(NewsFetchService);
    expect(service).toBeTruthy();
  });
});
