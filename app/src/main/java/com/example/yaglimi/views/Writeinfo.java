package com.example.yaglimi.views;

public class Writeinfo {
    private String title;
    private String content;

    public Writeinfo (String title, String content) {
        this.title = title;
        this.content = content;
    }
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent(){
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
