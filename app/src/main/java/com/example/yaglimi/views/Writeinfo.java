package com.example.yaglimi.views;

public class Writeinfo {
    private String title;
    private String content;
    private String publisher;
    private String time;

    public Writeinfo (String title, String content, String publisher, String time) {
        this.title = title;
        this.content = content;
        this.publisher = publisher;
        this.time = time;
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
    public String getPublisher(){
        return this.publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public String getTime(){
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
}
