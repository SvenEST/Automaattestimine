package file;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class WriteFileTest {
	
	String fileName;
	Path inputPath;
	Path outputPath;
	Date currentDate;
	String inputContent;
	
	@Before
	public void setUpTests() {
		fileName = "FileTest.txt";
		inputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\FailiTestid\\", fileName);
		outputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\FailiTestid\\");
		currentDate = new Date();
		inputContent = "File test at: " + currentDate.toString();
	}

	@Test
	public void testFileWriting() {
		WriteFile fileWriter = new WriteFile();
		boolean appendFile = false;
		fileWriter.writeFile(outputPath, fileName, inputContent, appendFile); 
		
		FileReader fileReader = new FileReader();
		String outputContent = null;
		try {
			outputContent = fileReader.readFile(inputPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(inputContent, outputContent);
	}

}
