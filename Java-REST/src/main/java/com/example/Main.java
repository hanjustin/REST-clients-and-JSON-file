package com.example;

public class Main {
    public static void main(String[] args) {
        try {
            PostDTO dto = new PostDTO(99, "Title", "Body");
            createWithMethodPOST(dto);
            Post myPost = readWithMethodGET("1");

            String filePath = "Temp.json";
            saveObjAsJSON(myPost, filePath);
            getObjFromJSON(filePath);

            myPost.title = "NEW title";
            myPost.body = "NEW body";
            updateWithMethodPUT(myPost);
            deleteWithMethodDELETE("1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static Post createWithMethodPOST(PostDTO dto) throws Exception {
        Post newPost = WebClientManager.postRequest(dto);
        System.out.println("--- POST Response post ---");
        System.out.println(newPost.toString());
        return newPost;
    }

    static Post readWithMethodGET(String id) throws Exception {
        System.out.println("--- GET Response post ---");
        Post getPost = WebClientManager.getRequest("1");
        System.out.println(getPost.toString());
        return getPost;
    }

    static Post updateWithMethodPUT(Post post) throws Exception {
        System.out.println("--- PUT Response post w/ updated title & body ---");
        Post updatedPost = WebClientManager.putRequest(post);
        System.out.println(updatedPost.toString());
        return updatedPost;
    }

    static int deleteWithMethodDELETE(String id) throws Exception {
        System.out.println("--- DELETE Response status code ---");
        int statusCode = WebClientManager.deleteRequest(id);
        System.out.println(statusCode);
        return statusCode;
    }

    static void saveObjAsJSON(Post post, String filePath) throws Exception {
        FileManager.saveObjToPath(post, filePath);
        System.out.println("--- JSON disk write & read ---");
        System.out.println("Saved to disk");
    }

    static Post getObjFromJSON(String filePath) throws Exception {
        Post post = FileManager.readObjFromPath(filePath);
        System.out.println("Converted JSON to object");
        return post;
    }
}