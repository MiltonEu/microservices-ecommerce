package com.eustache.article.controller;


import com.eustache.article.exception.ArticleNotFoundException;
import com.eustache.article.model.Article;
import com.eustache.article.model.ArticleCategory;
import com.eustache.article.service.ArticleService;
import com.eustache.model.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/article")
@RequiredArgsConstructor
public class ArticleController {

    public static final String ARTICLE = "article";
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ArticleService articleService;
    @GetMapping("/listArticles")
    public ResponseEntity<Response> findAllArticles(){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("articles", articleService.findAll()))
                        .message("Articles retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @GetMapping("/findOne/{articleId}")
    public ResponseEntity<Response> findArticleById(@PathVariable("articleId") Integer articleId) throws ArticleNotFoundException {
        Optional<Article> optionalArticle = articleService.findArticleById(articleId);
        if(optionalArticle.isPresent()){
            return ResponseEntity.ok(Response.builder()
                .timeStamp(LocalDateTime.now())
                .data(Map.of(ARTICLE,optionalArticle))
                .message("Article retrieved")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value()).build());
        }
        throw new ArticleNotFoundException("Requested article not found");
    }

    @GetMapping("/filterByCriteria")
    public ResponseEntity<Response> findArticlesByCriteria(@RequestParam(required = false) Integer price,
                                                           @RequestParam(required = false) Boolean isInDiscount,
                                                           @RequestParam(required = false) Boolean isInStock,
                                                           @RequestParam(required = false) ArticleCategory articleCategory){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("articles", articleService.findArticlesByCriteria(price,isInDiscount,isInStock,articleCategory)))
                        .message("Articles retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
    @PostMapping("/saveArticle")
    public ResponseEntity<Response> saveArticle(@RequestBody Article article) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/saveArticle")
                .buildAndExpand(article.getName())
                .toUri();

        return ResponseEntity.created(location).body(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of(ARTICLE, articleService.saveArticle(article)))
                        .message("Article saved")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value()).build()
        );
    }



    @DeleteMapping("/deleteArticle/{articleId}")
    public ResponseEntity<Response> deleteArticle(@PathVariable("articleId") Integer articleId) throws ArticleNotFoundException {
        return ResponseEntity.ok(Response.builder()
                .timeStamp(LocalDateTime.now())
                .data(Map.of("deleted", articleService.deleteArticleById(articleId)))
                .message("Article deleted")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value()).build());
    }
}
