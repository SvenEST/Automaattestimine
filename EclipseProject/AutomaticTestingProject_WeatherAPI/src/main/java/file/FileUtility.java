package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileUtility {
	
	public String readFile(Path path){
		File file = new File(path.toString());
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    Scanner scanner = new Scanner(inputStream, "UTF-8");
	    String result = "";
	    while (scanner.hasNextLine()) {
	        String line = scanner.nextLine();
	        result += line;
	    }
	    scanner.close();
		return result;
	}
	
	public void writeFile(Path outputFileLocation, String fileName, String content, boolean appendFile){
		Path path = Paths.get(outputFileLocation.toString(), fileName);
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(path.toString(), appendFile);
			fileWriter.write(content);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
