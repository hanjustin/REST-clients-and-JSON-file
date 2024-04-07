import Foundation

public class HTTPRequestManager {
    
    private static let ResourceURL = "https://jsonplaceholder.typicode.com/posts/"
    
    public static func getPost(with id: Int) async throws -> Post {
        let (data, _) = try await URLSession.shared.data(from: postResourceURL(with: id))
        let post = try JSONDecoder().decode(Post.self, from: data)
        return post
    }
    
    public static func createPost(with post: Post) async throws -> Post {
        var request = try URLRequest(url: postResourceURL())
        request.httpMethod = "POST"
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        request.httpBody = try JSONEncoder().encode(post)
        
        let (data, _) = try await URLSession.shared.data(for: request)
        let post = try JSONDecoder().decode(Post.self, from: data)
        return post
    }
    
    public static func updatePost(with post: Post) async throws -> Post {
        var request = try URLRequest(url: postResourceURL(with: post.id))
        request.httpMethod = "PUT"
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        request.httpBody = try JSONEncoder().encode(post)
        
        let (data, _) = try await URLSession.shared.data(for: request)
        let post = try JSONDecoder().decode(Post.self, from: data)
        return post
    }
    
    public static func deletePost(with id: Int) async throws -> Int {
        var request = try URLRequest(url: postResourceURL(with: id))
        request.httpMethod = "DELETE"
        let (_, response) = try await URLSession.shared.data(for: request)
        
        guard
            let statusCode = (response as? HTTPURLResponse)?.statusCode,
            (200..<300).contains(statusCode)
        else {
            throw URLError(.unknown)
        }
        
        return statusCode
    }
    
    private static func postResourceURL(with id: Int? = nil) throws -> URL {
        var urlStr = ResourceURL
        if let id {
            urlStr += "\(id)"
        }
        
        guard let url = URL(string: urlStr) else {
            throw URLError(.badURL)
        }
        return url
    }
}
