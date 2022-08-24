package com.eustache.article.criteria_utils;

import com.eustache.article.model.ArticleCategory;
import org.springframework.stereotype.Component;

@Component
public class CriteriaUtils {

    public String doConcatenateCriteriasInQuery(Integer price, Boolean isInDiscount, Boolean isInStock, ArticleCategory articleCategory) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * from article a WHERE 1=1 ");
        if(isInDiscount != null){

            sb.append("AND a.is_in_discount = ").append(isInDiscount);
        }
        if(isInStock != null){
            sb.append(" AND a.is_in_stock = ").append(isInStock);
        }
        if(articleCategory != null){
            sb.append(" AND a.category = '").append(articleCategory.getCategory()).append("'");
        }
        return sb.toString();
    }

}
