package com.orgware.project.newssearch.newsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsController {

    @Autowired
    INewsService newsService;

    @RequestMapping("/")
    public String index() {
        newsService.getNewsFromRepository();
        return "Srijan is aweseom";
    }

}
