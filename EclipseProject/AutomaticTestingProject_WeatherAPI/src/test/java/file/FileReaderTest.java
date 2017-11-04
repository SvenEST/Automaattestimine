package file;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class FileReaderTest {

	@Test
	public void testFileRead() {
		FileReader fileReader = new FileReader();
		Path inputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\FileWritingAndReading\\input.txt");
		String result = null;
		try {
			result = fileReader.readFile(inputPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertFalse(result.isEmpty());
	}

}
