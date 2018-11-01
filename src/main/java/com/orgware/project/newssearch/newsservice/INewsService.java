package com.orgware.project.newssearch.newsservice;

import com.orgware.project.newssearch.model.Message;
import com.orgware.project.newssearch.model.News;

import java.util.List;

public interface INewsService {
    void startNewsFetcherService(NewsService newsService);

    void fetchNewsAPI();

    List<News> convertStringToJson(String jsonString);

    Message toggleService(String option);
}
