import Foundation

public struct Post: Codable {
    public var id: Int?
    public var userId: Int
    public var title: String
    public var body: String
    
    public init(id: Int? = nil, userId: Int, title: String, body: String) {
        self.id = id
        self.userId = userId
        self.title = title
        self.body = body
    }
}

extension Post {
    private enum CodingKeys: String, CodingKey {
            case id, userId, title, body
    }
    
    public init(from decoder: Decoder) throws {
        let values = try decoder.container(keyedBy: CodingKeys.self)
        self.id = try values.decodeIfPresent(Int.self, forKey: .id)
        self.userId = try values.decode(Int.self, forKey: .userId)
        self.title = try values.decode(String.self, forKey: .title)
        self.body = try values.decode(String.self, forKey: .body)
    }
}
