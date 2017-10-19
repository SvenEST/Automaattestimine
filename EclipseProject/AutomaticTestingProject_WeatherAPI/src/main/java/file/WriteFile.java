package file;

import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {

	public String writeFile(String filePath, String fileName, String content, boolean appendFile){
		String path = filePath + fileName + ".txt";
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(path, appendFile);
			fileWriter.write(content);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}
}
