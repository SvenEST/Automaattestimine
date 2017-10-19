package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileReader {
	
	public String readFile(String path) throws IOException{
		String result = "";
		File file = new File(path);
		FileInputStream inputStream = new FileInputStream(file);
	    Scanner sc = new Scanner(inputStream, "UTF-8");
	    while (sc.hasNextLine()) {
	        String line = sc.nextLine();
	        result += line;
	    }
		return result;
	}
}