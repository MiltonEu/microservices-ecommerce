package com.eustache.article.controller;

import com.eustache.article.exception.ArticleNotFoundException;
import com.eustache.article.model.Article;
import com.eustache.article.service.ArticleServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.eustache.article.model.ArticleCategory.VIDEO_GAME;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = ArticleController.class)
class ArticleControllerTest {
    public static final String ARTICLE_NAME = "ArticleName";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticleServiceImpl articleServiceImpl;

    private List<Article> articleList;

    private Article article;

    @Autowired
    private ObjectMapper objectMapper;

    private final static String BASE_PATH = "/api/v1/article";

    @BeforeEach
    void setUp() {

        this.article = new Article(1, "ArticleName", "ArticleTest", 10, true, false, VIDEO_GAME);
        this.articleList = Arrays.asList(new Article(), new Article(), new Article());
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void itShouldPass() {

    }

    @Test
    void itShouldAddAnArticle() throws Exception {
        when(articleServiceImpl.saveArticle(any(Article.class))).thenReturn(article);
        this.mockMvc.perform(MockMvcRequestBuilders.
                        post(BASE_PATH + "/saveArticle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(article)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.article.name").value(ARTICLE_NAME));
    }

    @Test
    void itShouldReturnAListOfArticles() throws Exception {
        when(articleServiceImpl.findAll()).thenReturn(articleList);
        this.mockMvc.perform(MockMvcRequestBuilders.
                        get(BASE_PATH + "/listArticles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.articles", hasSize(3))).andDo(print());
    }

    @Test
    void itShouldFindAnArticleById() throws Exception {
        when(articleServiceImpl.findArticleById(anyInt())).thenReturn(Optional.of(article));
        this.mockMvc.perform(MockMvcRequestBuilders
                .get(BASE_PATH+ "/findOne/" + anyInt()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.article.name").value(ARTICLE_NAME)).andDo(print() );
    }
    @Test()
    void itShouldThrowAnExceptionWhenFindAnArticleById() throws Exception {
        given(articleServiceImpl.findArticleById(anyInt())).willAnswer(invocationOnMock ->  { throw new ArticleNotFoundException("Article not found");});
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get(BASE_PATH+ "/findOne/" + anyInt()))
                .andExpect(status().isNotFound());
    }

    @Test
    void itShouldDeleteAnArticle() throws Exception {
        when(articleServiceImpl.findArticleById(anyInt())).thenReturn(Optional.of(article));
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete(BASE_PATH+ "/deleteArticle/" + anyInt()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.article.name").doesNotExist()).andDo(print());
    }

    @Test
    void itShouldFindArticlesByCriteriaInStock() throws Exception {
        List<Article> articleList = Arrays.asList(new Article(1, "ArticleName", "ArticleTest", 70, true, true, VIDEO_GAME)
                , new Article(2, "ArticleNameTwo", "ArticleTestTwo", 70, false, true, VIDEO_GAME));
        when(articleServiceImpl.findArticlesByCriteria(70,false, true, VIDEO_GAME)).thenReturn(articleList);
        this.mockMvc.perform(MockMvcRequestBuilders
                .get(BASE_PATH + "/filterByCriteria")
                .param("price", String.valueOf(70))
                .param("isInDiscount", "false")
                .param("isInStock", "true")
                .param("articleCategory", VIDEO_GAME.getCategory())).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.articles", hasSize(2)));
    }
}