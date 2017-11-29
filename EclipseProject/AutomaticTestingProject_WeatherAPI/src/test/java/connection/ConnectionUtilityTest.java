package connection;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import connection.ConnectionUtility;
import file.FileUtility;
import testhelpers.Validator;

public class ConnectionUtilityTest {
	
	public static JSONObject currentWeatherInfoFromFile;
	public static boolean testsInitialized;
	
	@Mock
	ConnectionUtility connectionUtility;
	
	@Before
	public void setUpTests() throws IOException{
		MockitoAnnotations.initMocks(this);
		if (testsInitialized != true) {
			Path inputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\ConnectionUtilityTesting\\CurrentWeatherInfo.txt");
			FileUtility fileUtility = new FileUtility();
			try {
				currentWeatherInfoFromFile = new JSONObject(fileUtility.readFile(inputPath));
			} catch (JSONException e) {
				fail("Failure cause: " + e.getMessage());
			}
			testsInitialized = true;
		}
	}
	
	@Test
	public void testIfInternetConnectionExists() {
		Mockito.when(connectionUtility.internetConnectionExists()).thenReturn(true);
		boolean result = connectionUtility.internetConnectionExists();
		assertTrue(result);
		Mockito.verify(connectionUtility, Mockito.times(1)).internetConnectionExists();
	}
	
	@Test
	public void testReadingJsonFromUrl() {
		Mockito.when(connectionUtility.readJsonFromUrl()).thenReturn(currentWeatherInfoFromFile);
		JSONObject result = connectionUtility.readJsonFromUrl();
		Validator.validateJsonFormat(result);
		Mockito.verify(connectionUtility, Mockito.times(1)).readJsonFromUrl();
	}
}
