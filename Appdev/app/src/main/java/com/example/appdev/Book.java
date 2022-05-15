package com.example.appdev;

public class Book {

    String title;
    Integer yearReleased;
    Integer quantityInStore;
    String authorName;
    Integer quantityBorrowed;

    public  Book () {

    }

    public Book(String title, Integer yearReleased, Integer quantityInStore, String authorName, Integer quantityBorrowed) {
        this.title = title;
        this.yearReleased = yearReleased;
        this.quantityInStore = quantityInStore;
        this.authorName = authorName;
        this.quantityBorrowed = quantityBorrowed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYearReleased() {
        return yearReleased;
    }

    public void setYearReleased(Integer yearReleased) {
        this.yearReleased = yearReleased;
    }

    public Integer getQuantityInStore() {
        return quantityInStore;
    }

    public void setQuantityInStore(Integer quantityInStore) {
        this.quantityInStore = quantityInStore;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Integer getQuantityBorrowed() {
        return quantityBorrowed;
    }

    public void setQuantityBorrowed(Integer quantityBorrowed) {
        this.quantityBorrowed = quantityBorrowed;
    }

}
