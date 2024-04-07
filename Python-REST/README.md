
1. Install `Request` for HTTP requests.
```
pip install requests
```

2. Run `main.py`

### Console output

```
--- POST Response post ---
{"id": 101, "userId": 135, "title": "My Title", "body": "My Body"}

--- GET Response post ---
{"id": 1, "userId": 1, "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit", "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"}

--- JSON disk write & read ---
Saved to disk
Converted JSON to object

--- PUT Response post w/ updated title & body ---
{"id": 1, "userId": 1, "title": "NEW title", "body": "NEW body"}

--- DELETE Response status code ---
200
```