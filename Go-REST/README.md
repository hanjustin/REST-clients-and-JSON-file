
* Go 1.22

* Run command

```
go run .
```

### Console output

```
--- POST Response post ---
&{UserId:135 Id:101 Title:My Title Body:My Body}

--- GET Response post ---
&{UserId:1 Id:1 Title:sunt aut facere repellat provident occaecati excepturi optio reprehenderit Body:quia et suscipit
suscipit recusandae consequuntur expedita et cum
reprehenderit molestiae ut ut quas totam
nostrum rerum est autem sunt rem eveniet architecto}

--- JSON disk write & read ---
Saved to disk
Converted JSON to object

--- PUT Response post w/ updated title & body ---
&{UserId:1 Id:1 Title:NEW title Body:NEW body}

--- DELETE Response status code ---
200
```