package httpManager

import (
	"bytes"
	"encoding/json"
	"io"
	"net/http"
)

const Api_Url = "https://jsonplaceholder.typicode.com/posts/"

func CreateResource(object any) {
	objBytes, err := json.Marshal(object)
	if err != nil {
		panic(err)
	}

	postRequest, _ := http.NewRequest(http.MethodPost, Api_Url, bytes.NewBuffer(objBytes))
	err = decodeResponseToObj(postRequest, &object)
	if err != nil {
		panic(err)
	}
}

func GetResource(id string, object any) {
	getRequest, _ := http.NewRequest(http.MethodGet, Api_Url+id, nil)
	err := decodeResponseToObj(getRequest, &object)
	if err != nil {
		panic(err)
	}
}

func UpdateResource(id string, object any) {
	objBytes, err := json.Marshal(object)
	if err != nil {
		panic(err)
	}

	putRequest, _ := http.NewRequest(http.MethodPut, Api_Url+id, bytes.NewBuffer(objBytes))
	err = decodeResponseToObj(putRequest, &object)
	if err != nil {
		panic(err)
	}
}

func DeleteResource(id string) int {
	deleteRequest, _ := http.NewRequest(http.MethodDelete, Api_Url+id, nil)
	client := &http.Client{}
	response, err := client.Do(deleteRequest)
	if err != nil {
		panic(err)
	}

	return response.StatusCode
}

func decodeResponseToObj(req *http.Request, obj any) error {
	body, err := responseBodyForRequest(req)
	if err != nil {
		return err
	}

	err = json.Unmarshal(body, &obj)
	if err != nil {
		return err
	}

	return nil
}

func responseBodyForRequest(req *http.Request) ([]byte, error) {
	client := &http.Client{}
	response, err := client.Do(req)
	if err != nil {
		return nil, err
	}
	defer response.Body.Close()

	body, err := io.ReadAll(response.Body)
	if err != nil {
		return nil, err
	}

	return body, nil
}
