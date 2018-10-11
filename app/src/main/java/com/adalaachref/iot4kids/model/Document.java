package com.adalaachref.iot4kids.model;

/**
 * Created by dell on 10/12/2017.
 */

public class Document {

    private String text;
    private String text2;
    private String image;

    public Document(String text, String text2, String image) {
        this.text = text;
        this.text2 = text2;
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    @Override
    public String toString() {
        return "Document{" +
                "text='" + text + '\'' +
                ", text2='" + text2 + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
