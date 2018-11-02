import { Component, OnInit } from '@angular/core';
import {Message , MessageError , MessageOk} from '../model/Message';
import {News} from '../model/News';
import {NewsItemComponent} from '../news-item/news-item.component';
import {NewsFetchService} from '../services/news-fetch.service';

declare var $ :any; 

@Component({
  selector: 'app-news-box',
  templateUrl: './news-box.component.html',
  styleUrls: ['./news-box.component.scss']
})

export class NewsBoxComponent implements OnInit {

  author : string =""; 
  newsCount: number =0;
  message : Message = MessageOk; 
  news : News[];   
  disable : string = "stop";
  enable : string = "start";
  current : string = this.enable;
  newsMapper : object = {}; 

  constructor(private fetchService : NewsFetchService) { }

  ngOnInit() {
     
  }
 
  submitClicked() {
    alert("Submit Button Clicked");
     
  }

  /**
   * This function calls the endpoint in Spring Application
   * that toggles the get new service to fetch new News from
   * online and save it to mongodb. The service is a timered 
   * service which regularly calls an external API to get different
   * news
   */
  toggleClicked() {
      this.current = (this.current == this.enable) ? this.disable : this.enable;
      let httpResponse = this.fetchService.toggleServer(this.current);
      
      httpResponse.subscribe ((resp) => {
        const key = resp.headers.keys();
        this.message = { ... resp.body };
        console.log(this.message);
      }
    );
  }

  /**
   * This function calls the service to fetch all news 
   * available in the database. The news that is fetched 
   * is then displayed in the UI using the UI Template of angular.
   * To ensure that repeated items aren't displayed, I've created 
   * a map called newsMapper, which has key as author + heading + desc
   * and any other document with similar key will be stored as child array
   * inside the object. 
   */
  fetchNewsClicked() {
    let newsList = this.fetchService.getAllNews(); 
    this.newsMapper = {};
    newsList.subscribe(resp =>{
      console.log(resp);
      let i=0;
      let val = resp[0];
      let newsList : News[] = []; 

      //move to background thread 
      //ui will slow down 
      while(val != undefined && i <3000) {
        let entry : News = this.createNewsObjectFromJson(val); 
        let newsFromMap = this.newsMapper[entry.getUniqueHash()]; 
        console.log("###" + (entry instanceof News));

        if(newsFromMap == undefined) {
          this.newsMapper[entry.getUniqueHash()] = entry; // if not found add to entry
        } else {
          console.log('###ADDING CHILDREN');
          if(this.newsMapper[entry.getUniqueHash()].childs == undefined) {
            console.log("##NEW ENTRY");
            let childArr = [entry]; 
            this.newsMapper[entry.getUniqueHash()].childs = childArr;
          } else {
            console.log("##PUSHING");
            this.newsMapper[entry.getUniqueHash()].childs.push(entry);
            console.log("PRESENT ARRAY COUNT " + this.newsMapper[entry.getUniqueHash()].childs.length);
          }
        }
        newsList.push(entry); 
        i++; 
        val = resp[i];
        console.log(entry);
      }
      this.news = newsList;
    })
  }

  /**
   * this is a test function to see attributes 
   * of some item IGNORE
   * @param event 
   */
  repeatClicked(event) {
    let target = event.target || event.srcElement || event.currentTarget;
    let attr = target.attributes; 
    // let idValue = idAttr.nodeValue; 
    console.log(attr);
    console.log("data-target: " + attr["data-target"]);
    

  }
  /**
   * Utility function that converts the json data of 
   * News coming from server into typescript news object.
   * 
   * @param newsJson 
   */
  createNewsObjectFromJson(newsJson : Object ) : News {
    let author = newsJson["author"]; 
    let content = newsJson["content"];
    let description = newsJson["description"];
    let newsId = newsJson["newsId"]; 
    let publishedAt = newsJson["publishedAt"];
    let url = newsJson["url"];
    let title = newsJson["title"];
    let utlToImage = newsJson["utlToImage"]; 
    let curNews = new News(newsId,author,title,description, url,utlToImage,publishedAt,content);
    return curNews;
  }

}
/*

author: "Taylor Hatmaker"
​​
content: "Coinbase’s newest asset is live. On Tuesday the popular U.S.-based cryptocurrency platform added support for ZRX, the token representing the 0x Project. On Coinbase, ZRX joins the rarified ranks of Bitcoin, Bitcoin Cash, Ethereum, Ethereum Classic and Litecoi… [+1000 chars]"
​​
description: "Coinbase’s newest asset is live. On Tuesday the popular U.S.-based cryptocurrency platform added support for ZRX, the token representing the 0x Project. On Coinbase, ZRX joins the rarified ranks of Bitcoin, Bitcoin Cash, Ethereum, Ethereum Classic and Litecoi…"
​​
newsId: "5bd605930a1d1f4856dccd7b"
​​
publishedAt: "2018-10-16T19:48:06Z"
​​
title: "Coinbase now lets you buy and sell ZRX"
​​
url: "http://techcrunch.com/2018/10/16/zrx-coinbase/"
​​
utlToImage: null
*/