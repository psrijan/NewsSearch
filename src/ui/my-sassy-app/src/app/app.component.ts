import { Component , OnInit} from '@angular/core';
import {NewsFetchService} from './services/news-fetch.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})

export class AppComponent implements OnInit {
  
  title = 'NewsViewer';

  constructor(
    public newsFetchService: NewsFetchService
  ) {

  }

  ngOnInit() : void {
  }
  
}
