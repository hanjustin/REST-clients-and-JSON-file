package main

import (
	"fmt"
	"postResourceClient/httpManager"
	"postResourceClient/jsonManager"
	"strconv"
)

type Post struct {
	UserId int    `json:"userId"`
	Id     int    `json:"id"`
	Title  string `json:"title"`
	Body   string `json:"body"`
}

func main() {
	var newPost = Post{
		UserId: 135,
		Title:  "My Title",
		Body:   "My Body",
	}
	createWithMethodPOST(&newPost)

	var myPost Post
	readWithMethodGET("1", &myPost)

	filePath := "temp.json"
	saveAsJSON(filePath, myPost)
	getObjectFromJSON(filePath, &myPost)

	myPost.Title = "NEW title"
	myPost.Body = "NEW body"

	updateWithMethodPUT(&myPost)
	deleteWithMethodDELETE("1")
}

func createWithMethodPOST(post *Post) {
	fmt.Println("--- POST Response post ---")
	httpManager.CreateResource(post)
	fmt.Printf("%+v\n", post)
}

func readWithMethodGET(id string, post *Post) {
	httpManager.GetResource(id, post)
	fmt.Println("--- GET Response post ---")
	fmt.Printf("%+v\n", post)
}

func updateWithMethodPUT(post *Post) {
	httpManager.UpdateResource(strconv.Itoa(post.Id), post)
	fmt.Println("--- PUT Response post w/ updated title & body ---")
	fmt.Printf("%+v\n", post)
}

func deleteWithMethodDELETE(id string) {
	fmt.Println("--- DELETE Response status code ---")
	statusCode := httpManager.DeleteResource(id)
	fmt.Println(statusCode)
}

func saveAsJSON(filePath string, post Post) {
	fmt.Println("--- JSON disk write & read ---")
	jsonManager.SaveDataToPath(filePath, post)
	fmt.Println("Saved to disk")
}

func getObjectFromJSON(filePath string, post *Post) {
	jsonManager.LoadDataFromPath(filePath, post)
	fmt.Println("Converted JSON to object")
}
