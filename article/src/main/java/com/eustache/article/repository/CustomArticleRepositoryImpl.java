package com.eustache.article.repository;

import com.eustache.article.criteria_utils.CriteriaUtils;
import com.eustache.article.model.Article;
import com.eustache.article.model.ArticleCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomArticleRepositoryImpl implements  CustomArticleRepository{

    private final EntityManager entityManager;
    private final CriteriaUtils criteriaUtils;

    @Autowired
    public CustomArticleRepositoryImpl(EntityManager entityManager, CriteriaUtils criteriaUtils) {
        this.entityManager = entityManager.getEntityManagerFactory().createEntityManager();
        this.criteriaUtils = criteriaUtils;
    }
    @Override
    public List<Article> findArticlesByCriteria(Integer price, Boolean isInDiscount, Boolean isInStock, ArticleCategory articleCategory) {

        Query query = this.entityManager
                .createNativeQuery(criteriaUtils.doConcatenateCriteriasInQuery(price, isInDiscount, isInStock, articleCategory), Article.class);
        return query.getResultList();
    }
}
