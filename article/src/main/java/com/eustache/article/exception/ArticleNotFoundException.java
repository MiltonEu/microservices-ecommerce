package com.eustache.article.exception;

public class ArticleNotFoundException extends Exception{

    public ArticleNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
