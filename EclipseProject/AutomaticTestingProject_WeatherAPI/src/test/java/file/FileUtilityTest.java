package file;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class FileUtilityTest {
	
	FileUtility fileUtility;
	
	@Before
	public void setUpTests() {
		fileUtility = new FileUtility();
	}
	
	@Test
	public void testFileWriting() {
		String outputFileName = "FileTest.txt";
		Path outputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\FileWritingAndReading\\");
		Date currentDate = new Date();
		String fileContent = "File test at: " + currentDate.toString();
		
		boolean appendFile = false;
		try {
			fileUtility.writeFile(outputPath, outputFileName, fileContent, appendFile);
		} catch (IOException e1) {
			fail("Failure cause: " + e1.getMessage());
		} 
		
		String recievedContent = null;
		Path inputPath = Paths.get(outputPath.toString(), outputFileName);
		try {
			recievedContent = fileUtility.readFile(inputPath);
		} catch (IOException e2) {
			fail("Failure cause: " + e2.getMessage());
		}
		assertEquals(fileContent, recievedContent);
	}

	@Test
	public void testFileRead() {
		Path inputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\FileWritingAndReading\\input.txt\\");
		String result = null;
		try {
			result = fileUtility.readFile(inputPath);
		} catch (IOException e) {
			fail("Failure cause: " + e.getMessage());
		}
		assertFalse(result.isEmpty());
	}
}
