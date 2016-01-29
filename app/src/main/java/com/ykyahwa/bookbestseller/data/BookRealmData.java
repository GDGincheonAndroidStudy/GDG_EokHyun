package com.ykyahwa.bookbestseller.data;

import io.realm.RealmObject;

/**
 * Created by ehlee on 2016-01-13.
 */
public class BookRealmData extends RealmObject {
    private String title;
    private String author;
    private String publisher;
    private String mobileLink;
    private String priceStandard;
    private String rank;
    private String coverSmallUrl;


    public BookRealmData() {
    }

    public BookRealmData(BookData bookData) {
        this.title = bookData.getTitle();
        this.author = bookData.getAuthor();
        this.publisher = bookData.getPublisher();
        this.mobileLink = bookData.getMobileLink();
        this.priceStandard = bookData.getPriceStandard();
        this.rank = bookData.getRank();
        this.coverSmallUrl = bookData.getCoverSmallUrl();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getMobileLink() {
        return mobileLink;
    }

    public void setMobileLink(String mobileLink) {
        this.mobileLink = mobileLink;
    }

    public String getPriceStandard() {
        return priceStandard;
    }

    public void setPriceStandard(String priceStandard) {
        this.priceStandard = priceStandard;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getCoverSmallUrl() {
        return coverSmallUrl;
    }

    public void setCoverSmallUrl(String coverSmallUrl) {
        this.coverSmallUrl = coverSmallUrl;
    }
}
