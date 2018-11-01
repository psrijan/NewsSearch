package com.orgware.project.newssearch.newsservice;

import com.orgware.project.newssearch.model.Message;
import com.orgware.project.newssearch.model.News;
import com.orgware.project.newssearch.newssaveservice.INewsSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * These are the rest endpoints that is hit by the client
 */
@RestController
public class NewsController implements INewsController{

    @Autowired
    private INewsSaveService newsSaveService;

    @Autowired
    private NewsService newsService;

    @Override
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @RequestMapping(value= "/all" , produces = {"application/json"} , method = RequestMethod.GET)
    public List<News> getAllNews() {
        return newsSaveService.getAllNews();
    }

    /**
     * Adding parameters in the rest url and using it as variables in the java class
     * The
     */
    @Override
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @RequestMapping(value="author/{authorName}" ,produces = "application/json" , method = RequestMethod.GET)
    public List<News> getSpecificNews (@PathVariable String authorName ) {
        return newsSaveService.getNewsByAuthor(authorName);
    }

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @RequestMapping(value="/add" , produces = "application/json" , method = RequestMethod.POST)
    public News addNewNews(@RequestBody  News news){
        System.out.println("News Users Add method");
        return news;
    }

    @Override
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @RequestMapping(value = "/toggle/{status}" , produces = "application/json")
    public Message switchNewsFetchService(@PathVariable String status){
        return newsService.toggleService(status);
    }

}
