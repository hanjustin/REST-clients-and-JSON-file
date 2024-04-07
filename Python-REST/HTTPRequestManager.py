import requests
from Post import *

class HTTPRequestManager:

    API_URL = "https://jsonplaceholder.typicode.com/posts/"

    @classmethod
    def getPost(cls, id: int) -> Post:
        full_url = cls.API_URL + str(id)
        get_response = requests.get(full_url)
        return Post(get_response.json())

    @classmethod
    def createPost(cls, post: Post):
        headers = {'Content-type': 'application/json', 'Accept': 'text/plain'}
        post_response = requests.post(cls.API_URL, data=post.toJSON(), headers = headers)
        return Post(post_response.json())

    @classmethod
    def updatePost(cls, post: Post) -> Post:
        full_url = cls.API_URL + str(post.id)
        headers = {'Content-type': 'application/json', 'Accept': 'text/plain'}
        put_response = requests.put(full_url, data=post.toJSON(), headers = headers)
        return Post(put_response.json())

    @classmethod
    def deletePost(cls, id: int) -> int:
        full_url = cls.API_URL + str(id)
        delete_response = requests.delete(full_url)
        return delete_response.status_code