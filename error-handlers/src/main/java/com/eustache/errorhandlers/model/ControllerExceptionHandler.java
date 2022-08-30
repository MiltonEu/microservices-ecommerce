package com.eustache.errorhandlers.model;

import com.eustache.article.exception.ArticleNotFoundException;
import com.eustache.user.exception.RoleNotFoundException;
import com.eustache.user.exception.UserNotFoundException;
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
            reason = "Requested user not found"
    )
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Error> handleUserNotFoundException(UserNotFoundException userNotFoundException) {
        Error error = new Error(HttpStatus.NOT_FOUND, userNotFoundException.getLocalizedMessage());
        return new ResponseEntity<>(error, error.getHttpStatus());
    }

    @ResponseStatus(
            value = HttpStatus.NOT_FOUND,
            reason = "Requested role not found"
    )
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Error> handleRoleNotFoundException(RoleNotFoundException roleNotFoundException) {
        Error error = new Error(HttpStatus.NOT_FOUND, roleNotFoundException.getLocalizedMessage());
        return new ResponseEntity<>(error, error.getHttpStatus());
    }

}
