package com.packtpub.library;

public class EBook extends Book{
   private String downloadUrl;

    public EBook(String title, String author) {
        super(title, author);
    }

    public EBook(String title, String author, String downloadUrl) {
        super(title, author);
        this.downloadUrl = downloadUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
