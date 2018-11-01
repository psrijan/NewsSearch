package com.orgware.project.newssearch.newssaveservice;

import com.orgware.project.newssearch.model.News;

import java.util.List;

/**
 * Interface contract for NewsSaveService.
 * Defines the type of methods available for
 * news
 */
public interface INewsSaveService {

    public void saveNews(News news);
    public List<News> getAllNews();
    public List<News> getNewsByAuthor(String author);

}
