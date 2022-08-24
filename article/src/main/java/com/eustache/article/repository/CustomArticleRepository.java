package com.eustache.article.repository;

import com.eustache.article.model.Article;
import com.eustache.article.model.ArticleCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomArticleRepository {

    List<Article> findArticlesByCriteria(Integer price, Boolean isInDiscount, Boolean isInStock, ArticleCategory articleCategory);
}
