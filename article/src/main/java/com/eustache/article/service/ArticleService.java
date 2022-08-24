package com.eustache.article.service;

import com.eustache.article.exception.ArticleNotFoundException;
import com.eustache.article.model.Article;
import com.eustache.article.model.ArticleCategory;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    List<Article> findAll();
    List<Article> findArticlesByCriteria(Integer price, Boolean isInDiscount, Boolean isInStock, ArticleCategory articleCategory);
    Optional<Article> findArticleById(Integer articleId) throws ArticleNotFoundException;
    Article saveArticle(Article articleToSave);
    Boolean deleteArticleById(Integer articleId) throws ArticleNotFoundException;


}
