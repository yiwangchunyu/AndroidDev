package com.example.springrain.ch5project3;

/**
 * Created by SpringRain on 2018/11/26.
 */

public class Item {
    private String content;
    private int imageId;

    public Item(String content, int imageId) {
        this.content = content;
        this.imageId = imageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
