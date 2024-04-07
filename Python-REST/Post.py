import json

class Post:
    def __init__(self, dict):
        if "id" in dict:
            self.id = dict["id"]
        self.userId = dict["userId"]
        self.title = dict["title"]
        self.body = dict["body"]

    def toJSON(self):
        return json.dumps(self.__dict__)