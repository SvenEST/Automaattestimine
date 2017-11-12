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
import file.FileReader;
import testhelpers.Validator;

public class ConnectionTest {
	
	public static JSONObject currentWeatherInfoMock;
	public static boolean testsInitialized = false;
	
	@Mock
	ConnectionUtility con;
	
	@Before
	public void setUpTests() throws IOException{
		MockitoAnnotations.initMocks(this);
		if (testsInitialized == false) {
			Path inputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\MockingInputs\\CurrentWeatherInfo.txt");
			FileReader fileReader = new FileReader();
			try {
				currentWeatherInfoMock = new JSONObject(fileReader.readFile(inputPath));
			} catch (JSONException e) {
				fail("Failure cause: " + e.getMessage());
			}
			testsInitialized = true;
		}
	}
	
	@Test
	public void testInternetConnectionExists() {
		/*ConnectionUtility con = new ConnectionUtility("https://www.google.com/");*/
		Mockito.when(con.internetConnectionExists()).thenReturn(true);
		boolean result = con.internetConnectionExists();
		assertTrue(result);
	}
	
	@Test
	public void testJsonReadingFromUrl() {
		/*ConnectionUtility con = new ConnectionUtility("http://api.openweathermap.org/data/2.5/weather?q=Tallinn,ee&appid=1a8a3563bee4967e64490dbfadf83b7e");*/
		try {
			Mockito.when(con.readJsonFromUrl()).thenReturn(currentWeatherInfoMock);
		} catch (IOException i) {
			fail("Failure cause: " + i.getMessage());
		}
		try {
			JSONObject result = con.readJsonFromUrl();
			Validator.validateJsonFormat(result);
		} catch (IOException e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
}
