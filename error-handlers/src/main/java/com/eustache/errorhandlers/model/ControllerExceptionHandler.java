package com.eustache.errorhandlers.model;

import com.eustache.article.exception.ArticleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(
            value = HttpStatus.NOT_FOUND,
            reason = "Requested article not found"
    )
    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<Error> handleArticleNotFoundException(ArticleNotFoundException articleNotFoundException) {
        Error error = new Error(HttpStatus.NOT_FOUND, articleNotFoundException.getLocalizedMessage());
        return new ResponseEntity<>(error, error.getHttpStatus());
    }

    @ResponseStatus(
            value = HttpStatus.NOT_FOUND,
            reason = "Requested article not found"
    )
    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<Error> handleUserNotFoundException(ArticleNotFoundException articleNotFoundException) {
        Error error = new Error(HttpStatus.NOT_FOUND, articleNotFoundException.getLocalizedMessage());
        return new ResponseEntity<>(error, error.getHttpStatus());
    }

}
