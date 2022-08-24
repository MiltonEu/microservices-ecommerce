package com.eustache.article.service;

import com.eustache.article.model.Article;
import com.eustache.article.repository.ArticleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.eustache.article.model.ArticleCategory.VIDEO_GAME;
import static org.apache.coyote.http11.Constants.a;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArticleServiceImplTest {

    @Mock
    ArticleRepository articleRepository;

    @InjectMocks
    ArticleServiceImpl articleService;

    Article article;
    @BeforeEach
    void setUp() {
        article = new Article(1, "ArticleName", "ArticleTest", 10, true, false, VIDEO_GAME);
    }

    @Test
    void findAll() {
        //given
        List<Article> articleList = Arrays.asList(new Article(1, "ArticleName", "ArticleTest", 10, true, false, VIDEO_GAME)
                , new Article(2, "ArticleNameTwo", "ArticleTestTwo", 70, false, true, VIDEO_GAME));
        when(articleRepository.findAll()).thenReturn(articleList);

        List<Article> articlesFromService = articleService.findAll();

        assertThat(articlesFromService).hasSize(2);


    }

    @Test
    void findArticleById() {
        //when
        when(articleRepository.findById(anyInt())).thenReturn(Optional.of(article));

        //then
        Optional<Article> articleFounded = articleService.findArticleById(1);
        assertThat(articleFounded).isNotNull();
        then(articleRepository).should().findById(anyInt());
        then(articleRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void saveArticle() {

        when(articleRepository.save(any(Article.class))).thenReturn(article);
        Article savedArticle = articleService.saveArticle(this.article);
        assertThat(savedArticle).isNotNull();
        then(articleRepository).should().save(any(Article.class));
    }


    @Test
    void itShouldDeleteAnArticle() {

        when(articleRepository.findById(1)).thenReturn(Optional.of(article));

        articleService.deleteArticleById(1);
        then(articleRepository).should().deleteById(1);
    }

    @Test
    void itShouldFindArticlesByCriteria(){
        List<Article> articleList = Arrays.asList(new Article(1, "ArticleName", "ArticleTest", 70, true, true, VIDEO_GAME)
                , new Article(2, "ArticleNameTwo", "ArticleTestTwo", 70, false, true, VIDEO_GAME));
        when(articleRepository.findArticlesByCriteria(70, false, true, VIDEO_GAME)).thenReturn(articleList);

        List<Article> articlesFromService = articleService.findArticlesByCriteria(70,false,true, VIDEO_GAME);

        assertThat(articlesFromService).hasSize(2);
    }
}