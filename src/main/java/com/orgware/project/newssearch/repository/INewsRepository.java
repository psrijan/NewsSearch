package com.orgware.project.newssearch.repository;

import com.orgware.project.newssearch.model.News;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Second type is the Id from which you will find the user
 */
@Repository
public interface INewsRepository extends MongoRepository<News, String> {
    public List<News> findByAuthor(String author);
    public List<News> findByTitle(String title);

}
