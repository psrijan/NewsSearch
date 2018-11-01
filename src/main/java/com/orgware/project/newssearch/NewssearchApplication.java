package com.orgware.project.newssearch;

import com.orgware.project.newssearch.model.News;
import com.orgware.project.newssearch.newssaveservice.NewsSaveService;
import com.orgware.project.newssearch.newsservice.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
public class NewssearchApplication {


	@Autowired
	NewsSaveService newsSaveService;

	@Autowired
	NewsService newsService;


	public static void main(String[] args) {

		SpringApplication.run(NewssearchApplication.class, args);


	}

	@PostConstruct
	public void init() {
//		List<News> newsList  = newsSaveService.getAllNews();
//		System.out.println(newsList.get(0).toString());
//		newsSaveService.getNewsByAuthor("Emily Dreyfuss");
	}



}
