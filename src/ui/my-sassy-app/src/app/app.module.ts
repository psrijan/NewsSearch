import { BrowserModule  } from '@angular/platform-browser';
import { CommonModule} from '@angular/common';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { NewsBoxComponent } from './news-box/news-box.component';
import { HttpClientModule } from '@angular/common/http';
import { NewsItemComponent } from './news-item/news-item.component';

@NgModule({
  declarations: [
    AppComponent,
    NewsBoxComponent,
    NewsItemComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
