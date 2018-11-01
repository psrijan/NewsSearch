package com.orgware.project.newssearch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;

/**
 * In terms of NoSQL index = table
 */

/**
 * This is the format of the document I store in the
 * Mongodb database. This is passed on through a rest service to
 * a simple html page for display.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class News {
    @Id
    private String newsId;
    private String author;
    private String title;
    private String description;
    private String url;
    private String utlToImage;
    private String publishedAt;
    private String content;

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUtlToImage() {
        return utlToImage;
    }

    public void setUtlToImage(String utlToImage) {
        this.utlToImage = utlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "HEADING: " + title + " CONTENT: " +content;
    }
}
