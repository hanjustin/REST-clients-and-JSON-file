import Foundation

public class JSONFileManager {
    public static func save(data: Data, to fileName: String) throws {
        let jsonFileURL = FileManager.default.temporaryDirectory.appending(path: fileName)
        try data.write(to: jsonFileURL)
    }
    
    public static func read(_ fileName: String) throws -> Data {
        let jsonFileURL = FileManager.default.temporaryDirectory.appending(path: fileName)
        let jsonData = try Data(contentsOf: jsonFileURL)
        return jsonData
    }
}
