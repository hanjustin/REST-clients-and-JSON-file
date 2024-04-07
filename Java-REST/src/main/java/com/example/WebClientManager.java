package com.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;

public class WebClientManager {
    private static String resourceURL = "https://jsonplaceholder.typicode.com/posts/";

    static Post postRequest(PostDTO dto) throws Exception {
        Gson gson = new Gson();
        String body = gson.toJson(dto);
    
        HttpRequest postRequest = HttpRequest.newBuilder()
                                    .uri(new URI(resourceURL))
                                    .setHeader("content-type", "application/json")
                                    .POST(BodyPublishers.ofString(body))
                                    .build();
        
        HttpResponse<String> postResponse = newHttpClient().send(postRequest, BodyHandlers.ofString());
        Post newPost = deserializeObjectFromJSON(postResponse);
        return newPost;
    }

    static Post getRequest(String id) throws Exception {
        HttpRequest getRequest = HttpRequest.newBuilder()
                                    .uri(new URI(resourceURL + id))
                                    .build();
    
        HttpResponse<String> getResponse = newHttpClient().send(getRequest, BodyHandlers.ofString());
        
        Post getPost = deserializeObjectFromJSON(getResponse);
        return getPost;
    }

    static Post putRequest(Post post) throws Exception {
        Gson gson = new Gson();
        String body = gson.toJson(post);
        
        HttpRequest putRequest = HttpRequest.newBuilder()
                                    .uri(new URI(resourceURL + Integer.toString(post.id)))
                                    .setHeader("content-type", "application/json")
                                    .PUT(BodyPublishers.ofString(body))
                                    .build();
        
        HttpResponse<String> putResponse = newHttpClient().send(putRequest, BodyHandlers.ofString());

        Post updatedPost = deserializeObjectFromJSON(putResponse);
        return updatedPost;
    }

    static int deleteRequest(String id) throws Exception {
        HttpRequest deleteRequest = HttpRequest.newBuilder()
                                        .uri(new URI(resourceURL + id))
                                        .DELETE()
                                        .build();

        HttpResponse<String> deleteResponse = newHttpClient().send(deleteRequest, BodyHandlers.ofString());
        return deleteResponse.statusCode();
    }

    private static HttpClient newHttpClient() {
        return HttpClient.newHttpClient();
    }

    private static Post deserializeObjectFromJSON(HttpResponse<String> response) {
        Gson gson = new Gson();
        return gson.fromJson(response.body(), Post.class);
    }
}
