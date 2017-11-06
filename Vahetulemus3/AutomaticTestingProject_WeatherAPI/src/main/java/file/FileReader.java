package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class FileReader {
	
	public String readFile(Path path) throws IOException{
		String result = "";
		File file = new File(path.toString());
		FileInputStream inputStream = new FileInputStream(file);
	    Scanner sc = new Scanner(inputStream, "UTF-8");
	    while (sc.hasNextLine()) {
	        String line = sc.nextLine();
	        result += line;
	    }
	    sc.close();
		return result;
	}
}