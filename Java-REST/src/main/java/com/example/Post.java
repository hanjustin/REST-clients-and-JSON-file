package com.example;

public class Post {
    int id;
    int userId;
    String title;
    String body;

    @Override
    public String toString() {
        return "Post{id='" + id + "', userId='" + userId + "', title='" + title + "', body='" + body + "' '}'";
    }
}
