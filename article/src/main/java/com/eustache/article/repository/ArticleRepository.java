package com.eustache.article.repository;

import com.eustache.article.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Integer>, CustomArticleRepository {
}
