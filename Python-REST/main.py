from FileManager import *
from HTTPRequestManager import *
from Post import *

import requests

def main():
    resource_id = 1
    post_data = {"title":"My Title", "body":"My Body", "userId":135}
    createWithMethodPOST(Post(post_data))

    my_post = readWithMethodGET(resource_id)
    
    filePath = "temp.json"
    saveAsJSON(my_post, filePath)
    my_post = getObjectFromJSON(filePath)

    my_post.title, my_post.body = "NEW title", "NEW body"
    updateWithMethodPUT(my_post)
    deleteWithMethodDELETE(resource_id)


def createWithMethodPOST(post: Post) -> Post:
    new_post = HTTPRequestManager.createPost(post)
    print("--- POST Response post ---")
    print(new_post.toJSON())
    return new_post

def readWithMethodGET(id: int) -> Post:
    get_post = HTTPRequestManager.getPost(id)
    print("--- GET Response post ---")
    print(get_post.toJSON())
    return get_post

def updateWithMethodPUT(post: Post):
    updated_Post = HTTPRequestManager.updatePost(post)
    print("--- PUT Response post w/ updated title & body ---")
    print(updated_Post.toJSON())
    return updated_Post

def deleteWithMethodDELETE(id: int) -> int:
    statusCode = HTTPRequestManager.deletePost(1)
    print("--- DELETE Response status code ---")
    print(statusCode)
    return statusCode

def saveAsJSON(data: Post, filepath: str):
    FileManager.save_as_json_file(data.__dict__, filepath)
    print("--- JSON disk write & read ---")
    print("Saved to disk")

def getObjectFromJSON(filepath: str) -> Post:
    json_data = FileManager.read_json_from(filepath)
    print("Converted JSON to object")
    return Post(json_data)



if __name__=="__main__": 
    main() 
