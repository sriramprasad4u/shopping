package com.manuvastra;

/**
 * Created by weizeng on 3/18/2016.
 */
/**
 * Item class denotes a product in EasyShopping app data
 */
public class Item {

    private boolean featured;
    private String depId;
    private String review;
    private String title;
    private String id;
    private String image;
    private float rating;
    private Double price;
    private String description;

    public Item(boolean featured, String depId, String review, String title, String id, String image, float rating, Double price, String description) {
        this.featured = featured;
        this.depId = depId;
        this.review = review;
        this.title = title;
        this.id = id;
        this.image = image;
        this.rating = rating;
        this.price = price;
        this.description = description;
    }

    public Item(Item item) {
        this.featured = item.featured;
        this.depId = item.depId;
        this.review = item.review;
        this.title = item.title;
        this.id = item.id;
        this.image = item.image;
        this.rating = item.rating;
        this.price = item.price;
        this.description = item.description;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Item() {
    }
}
