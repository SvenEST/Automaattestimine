package file;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class WriteFile {

	public void writeFile(Path outputFilePath, String fileName, String content, boolean appendFile){
		String path = outputFilePath.toString() + "\\" + fileName;
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(path, appendFile);
			fileWriter.write(content);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
