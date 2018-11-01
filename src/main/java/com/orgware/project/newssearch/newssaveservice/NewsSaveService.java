package com.orgware.project.newssearch.newssaveservice;

import com.orgware.project.newssearch.model.News;
import com.orgware.project.newssearch.repository.INewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * This is service that is used to save/ fetch news to and from the server.
 * I have injected NewsRepository through Autowiring a Bean.
 */
@Service
public class NewsSaveService implements  INewsSaveService{

    @Autowired
    INewsRepository newsRepository;

    @Override
    public void saveNews(News news) {
        newsRepository.save(news);
    }

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public List<News> getNewsByAuthor(String author) {
        return newsRepository.findByAuthor(author);
    }
}
