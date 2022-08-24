package com.eustache.article.criteria_utils;

import com.eustache.article.model.Article;
import com.eustache.article.model.ArticleCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CriteriaUtilsTest {

    public static final String QUERY_STRING_BASE = "SELECT * from article a WHERE 1=1 ";
    private CriteriaUtils criteriaUtils;
    private List<Article> articleList;

    @BeforeEach
    public void setUp(){
        criteriaUtils = new CriteriaUtils();
        articleList = new ArrayList<>();
    }
    @Test
    @DisplayName("Test if the called method return a query string searching for price = 40, isInStock = true and ArticleCategory = VideoGame")
    void itShouldReturnStringWithoutTheIsInDiscountCriteria() {
        String concatenatedStringForQuery = this.criteriaUtils.doConcatenateCriteriasInQuery(40, null, true, ArticleCategory.VIDEO_GAME);
        assertThat(concatenatedStringForQuery).isEqualTo(QUERY_STRING_BASE + " AND a.is_in_stock = true AND a.category = 'VIDEO_GAME'");
    }
    @Test
    @DisplayName("Test if the called method return a query string searching for price = 40, isInDiscount = true and ArticleCategory = VideoGame")
    void itShouldReturnStringWithoutTheIsInStockCriteria() {
        String concatenatedStringForQuery = this.criteriaUtils.doConcatenateCriteriasInQuery(40, true, null, ArticleCategory.VIDEO_GAME);
        assertThat(concatenatedStringForQuery).isEqualTo(QUERY_STRING_BASE + "AND a.is_in_discount = true AND a.category = 'VIDEO_GAME'");
    }

    @Test
    @DisplayName("Test if the called method return a query string searching for price = 40, isInDiscount = true ")
    void itShouldReturnStringWithoutTheArticleCategoryCriteria() {
        String concatenatedStringForQuery = this.criteriaUtils.doConcatenateCriteriasInQuery(40, true, true, null);
        assertThat(concatenatedStringForQuery).isEqualTo(QUERY_STRING_BASE + "AND a.is_in_discount = true AND a.is_in_stock = true");
    }

    @Test
    @DisplayName("Test if the called method return a query string searching for all articles ")
    void itShouldReturnStringWithoutCriteria() {
        String concatenatedStringForQuery = this.criteriaUtils.doConcatenateCriteriasInQuery(null, null, null, null);
        assertThat(concatenatedStringForQuery).isEqualTo(QUERY_STRING_BASE);
    }
}