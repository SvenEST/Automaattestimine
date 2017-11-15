package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileUtility {
	
	public String readFile(Path path) throws IOException{
		File file = new File(path.toString());
		FileInputStream inputStream = new FileInputStream(file);
	    Scanner scanner = new Scanner(inputStream, "UTF-8");
	    String result = "";
	    while (scanner.hasNextLine()) {
	        String line = scanner.nextLine();
	        result += line;
	    }
	    scanner.close();
		return result;
	}
	
	public void writeFile(Path outputFileLocation, String fileName, String content, boolean appendFile) throws IOException{
		Path path = Paths.get(outputFileLocation.toString(), fileName);
		FileWriter fileWriter= new FileWriter(path.toString(), appendFile);
		fileWriter.write(content);
		fileWriter.close();
	}
}
