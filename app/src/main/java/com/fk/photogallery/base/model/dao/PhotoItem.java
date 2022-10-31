package com.fk.photogallery.base.model.dao;

public class PhotoItem {
    private int id;
    private String previewURL;
    private String largeImageURL;
    private int likes;
    private int comments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }

    @Override
    public String toString() {
        return "PhotoItem{" +
                "id=" + id +
                ", previewURL='" + previewURL + '\'' +
                ", likes=" + likes +
                ", comments=" + comments +
                '}';
    }
}
