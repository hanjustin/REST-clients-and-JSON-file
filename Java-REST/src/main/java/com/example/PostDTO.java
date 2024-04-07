package com.example;

public class PostDTO {
    int userId;
    String title;
    String body;

    public PostDTO(int userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }
}