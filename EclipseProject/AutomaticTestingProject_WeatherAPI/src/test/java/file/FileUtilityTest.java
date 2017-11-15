package file;

import static org.junit.Assert.*;

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
		Path outputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\FileUtilityTesting\\");
		Date currentDate = new Date();
		String fileContent = "File test at: " + currentDate.toString();
		
		boolean appendFile = false;
		fileUtility.writeFile(outputPath, outputFileName, fileContent, appendFile); 
		
		String recievedContent = null;
		Path inputPath = Paths.get(outputPath.toString(), outputFileName);
		recievedContent = fileUtility.readFile(inputPath);
		assertEquals(fileContent, recievedContent);
	}

	@Test
	public void testFileRead() {
		Path inputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\FileUtilityTesting\\input.txt\\");
		String result = null;
		result = fileUtility.readFile(inputPath);
		assertFalse(result.isEmpty());
	}
}
