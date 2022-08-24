package com.eustache.article.model;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Article {

    @Id
    @SequenceGenerator(name = "article_id_sequence",
    sequenceName = "article_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "article_id_sequence")
    private Integer id;
    private String name;
    private String description;
    private Integer price;
    private Boolean isInDiscount;
    private Boolean isInStock;
    @Enumerated(EnumType.STRING)
    private ArticleCategory category;


}
