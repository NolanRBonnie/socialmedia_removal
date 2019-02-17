package com.example.nolanbonnie.social_media;

/**
 * Created by User on 4/4/2017.
 */

public class Card {
    private String imgURL;
    private String article;
    private String title;

    public Card(String imgURL, String article, String title) {
        this.imgURL = imgURL;
        this.article = article;
        this.title = title;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }
}