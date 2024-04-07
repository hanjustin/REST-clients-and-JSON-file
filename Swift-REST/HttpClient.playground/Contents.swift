
import Foundation

Task {
    do {
        var postData = Post(userId: 99, title: "Title", body: "Body")
        try await createWithMethodPOST(body: postData)
        
        let getPost = try await readWithMethodGET(id: 1)
        
        let filePath = "temp.json"
        try saveAsJSON(from: getPost, to: filePath)
        var jsonPost: Post = try getObjectFromJSON(at: "temp.json")
        
        (jsonPost.title, jsonPost.body) = ("NEW title", "NEW body")
        let _ = try await updateWithMethodPUT(body: jsonPost)
        
        try await deleteWithMethodDELETE(id: 1)
    } catch {
        print(error)
    }
}

// MARK: - REST

func createWithMethodPOST(body: Post) async throws {
    let newPost = try await HTTPRequestManager.createPost(with: body)
    print("--- POST Response post ---")
    print(newPost)
}

func readWithMethodGET(id: Int) async throws -> Post {
    let getPost = try await HTTPRequestManager.getPost(with: id)
    print("--- GET Response post ---")
    print(getPost)
    return getPost
}

func updateWithMethodPUT(body: Post) async throws -> Post {
    let updatedPost = try await HTTPRequestManager.updatePost(with: body)
    print("--- PUT Response post w/ updated title & body ---")
    print(updatedPost)
    return updatedPost
}

func deleteWithMethodDELETE(id: Int) async throws {
    let statusCode = try await HTTPRequestManager.deletePost(with: 1)
    print("--- DELETE Response status code ---")
    print(statusCode)
}

// MARK: - Disk

func saveAsJSON(from data: Codable, to filePath: String) throws {
    let json = try JSONEncoder().encode(data)
    try JSONFileManager.save(data: json, to: filePath)
    print("Saved to disk")
}

func getObjectFromJSON<T:Codable>(at filePath: String) throws -> T {
    let jsonData = try JSONFileManager.read(filePath)
    print("Converted JSON to object")
    return try JSONDecoder().decode(T.self, from: jsonData)
}
