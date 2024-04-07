package com.example;

import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class FileManager {
    public static void saveObjToPath(Post post, String filePath) throws Exception {
        try (FileWriter writer = new FileWriter(filePath)){
            Gson gson = new Gson();
            gson.toJson(post, writer);
        }
    }

    public static Post readObjFromPath(String filePath) throws Exception {
        try (JsonReader reader = new JsonReader(new FileReader(filePath))) {
            Gson gson = new Gson();
            Post post = gson.fromJson(reader, Post.class);
            return post;
        }
    }
}
