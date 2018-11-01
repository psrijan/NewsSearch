package com.orgware.project.newssearch.newsservice;

import com.orgware.project.newssearch.model.Message;
import com.orgware.project.newssearch.model.News;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * This interface is a contract that defines
 * the Spring application APIS available for
 * News Service
 */
public interface INewsController {
    Message switchNewsFetchService(@PathVariable String status);
    List<News> getAllNews();
    List<News> getSpecificNews(@PathVariable String userId );
}
