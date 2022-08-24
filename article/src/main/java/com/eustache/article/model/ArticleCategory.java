package com.eustache.article.model;

public enum ArticleCategory {
    VIDEO_GAME("VIDEO_GAME"),
    ACCESSORIES("ACCESSORIES"),
    ACTION_FIGURES("ACTION_FIGURES"),
    APPAREL("APPAREL"),
    BOOKS("BOOKS"),
    JEWELRY("JEWELRY"),
    MUSIC("MUSIC");

    private String category;
    ArticleCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
