import json

class FileManager:
    @classmethod
    def save_as_json_file(cls, data: dict[str: any], path: str):
        with open(path, "w") as file:
            json.dump(data, file)
    
    @classmethod
    def read_json_from(cls, path: str) -> dict[str: any]:
        with open(path, "r") as file:
            json_data = json.load(file)
        return json_data