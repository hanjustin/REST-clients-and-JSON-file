package jsonManager

import (
	"encoding/json"
	"os"
)

func SaveDataToPath(filePath string, data any) error {
	content, err := json.Marshal(data)
	if err != nil {
		return err
	}

	err = os.WriteFile(filePath, content, 0644)
	if err != nil {
		return err
	}

	return nil
}

func LoadDataFromPath(filePath string, data any) error {
	content, err := os.ReadFile(filePath)
	if err != nil {
		return err
	}

	err = json.Unmarshal(content, &data)
	return err
}
