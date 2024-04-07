
## About

* Used `HttpClient`, `HttpRequest`, & `HttpResponse` for REST API.
* Used `Gson` convert JSON file to Java object & Java object to JSON. (serialization/deserialization)

## Dependencies

* Gson - JSON handling

## Build Tool

* Maven

### Console output

```
--- POST Response post ---
Post{id='101', userId='99', title='Title', body='Body' '}'

--- GET Response post ---
Post{id='1', userId='1', title='sunt aut facere repellat provident occaecati excepturi optio reprehenderit', body='quia et suscipit
suscipit recusandae consequuntur expedita et cum
reprehenderit molestiae ut ut quas totam
nostrum rerum est autem sunt rem eveniet architecto' '}'

--- JSON disk write & read ---
Saved to disk
Converted JSON to object

--- PUT Response post w/ updated title & body ---
Post{id='1', userId='1', title='NEW title', body='NEW body' '}'

--- DELETE Response status code ---
200
```