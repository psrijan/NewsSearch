import { Input, Component, OnInit } from '@angular/core';
import {News}  from '../model/News';

@Component({
  selector: 'app-news-item',
  templateUrl: './news-item.component.html',
  styleUrls: ['./news-item.component.scss']
})
export class NewsItemComponent implements OnInit {

  @Input() newsEntry : News; 
  @Input() id; 
  @Input() author : string; 
  constructor() { }

  ngOnInit() {
    console.log("NEWS ITEM: " + this.id + " ENTRY: " + this.newsEntry)

  }

}
