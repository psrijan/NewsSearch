import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { News } from '../model/News';
import { Message } from '../model/Message';

@Injectable({
  providedIn: 'root'
})
export class NewsFetchService {

  BASE_URL : string ="http://localhost:8080";
  TEST_URL : string ="https://newsapi.org/v2/everything?q=bitcoin&apiKey=dad48ba99c6547e795d918dac24b1fc5&page=1&pageSize=100";
  ALL_NEWS : string = "/all";
  TOGGLE : string = "/toggle";
  AUTHOR_SEARCH :string ="/author";

  constructor(private http: HttpClient) { }

  /**
   * HTTP Request to get all news 
   */
  getAllNews()  {
    console.log("Fetching news from server");
    return this.http.get(this.getNewsUrl());
    
  }

  /**
   * Creates a url from base url and endpoint 
   * for all news.
   */
  private getNewsUrl() {
    return this.BASE_URL + this.ALL_NEWS; 
  }

  /**
   * Toggles the server to start or stop External
   * News Fetch Service
   */
  toggleServer (toggle  : string) : Observable<HttpResponse<Message>>{

    console.log("calling toggle api");
    return this.http.get<Message>(this.getToggleUrl(toggle) , {observe: 'response'})
 
  }

  /**
   * unused
   * but will search the db for 
   * author based on the string 
   * provided 
   * @param author 
   */
  searchNewsOnAuthor(author : string){
    this.http.get(this.getAuthorSearchUrl(author))
    .subscribe(resp =>{
      console.log(resp);
      let responseArr = resp; 
      //iterate through individual array
      // get individual array
      // iterate through individual array and make ArrayList
    });
  }

  /**
   * Makes the url based on base url and endpoint 
   * for fetching news based on author 
   * @param author 
   */
  private getAuthorSearchUrl(author : string) : string {
    return this.BASE_URL + this.AUTHOR_SEARCH +  "/" + this.convertSpace(author);
  }


  /**
   * converts the space in the url with %20
   * standard format it url. 
   * @param author 
   */
  private convertSpace (author : string) : string{
    return author.replace(" ", "%20");
  }

  /**
   * Get the url for Service Toggle 
   */
  private getToggleUrl(toggle : string ) : string {
    return this.BASE_URL + this.TOGGLE+"/"+toggle;
  }


}
