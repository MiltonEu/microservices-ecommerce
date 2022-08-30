package com.eustache.article.service;

import com.eustache.article.model.Article;
import com.eustache.article.model.ArticleCategory;
import com.eustache.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public List<Article> findArticlesByCriteria(Integer price, Boolean isInDiscount, Boolean isInStock, ArticleCategory articleCategory) {

        return articleRepository.findArticlesByCriteria(price,isInDiscount,isInStock,articleCategory);
    }

    @Override
    public Optional<Article> findArticleById(Integer articleId) {
        return articleRepository.findById(articleId);
    }



    @Override
    public Article saveArticle(Article article) {
        articleRepository.saveAndFlush(article);
        return article;
    }

    @Override
    public Boolean deleteArticleById(Integer articleId) {
        Optional<Article> articleToDelete = articleRepository.findById(articleId);
        if (articleToDelete.isPresent()) {
            articleRepository.deleteById(articleId);
            return true;
        }
        return false;
    }
}
