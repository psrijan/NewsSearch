package com.orgware.project.newssearch.newsservice;

import org.springframework.stereotype.Service;

@Service
public class NewsService implements INewsService {

    @Override
    public void getNewsFromRepository(){
        System.out.println("Fetched Rows from database ");
    }

}
