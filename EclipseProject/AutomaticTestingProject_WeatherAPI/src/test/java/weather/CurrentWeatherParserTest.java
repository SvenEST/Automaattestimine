package weather;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import file.FileUtility;
import testhelpers.Validator;

public class CurrentWeatherParserTest {

	private JSONObject currentWeatherInfoFromFile;
	private CurrentWeatherParser currentWeatherParser;
	private String units = "metric";
	private boolean testsInitialized;

	@Before
	public void setUpTests() throws IOException {
		MockitoAnnotations.initMocks(this);
		if (testsInitialized != true) {
			Path inputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\CurrentWeatherParserTesting\\CurrentWeatherInfo.txt");
			FileUtility fileUtility = new FileUtility();
			try {
				currentWeatherInfoFromFile = new JSONObject(fileUtility.readFile(inputPath));
			} catch (JSONException e) {
				fail("Failure cause: " + e.getMessage());
			}
			currentWeatherParser = new CurrentWeatherParser(currentWeatherInfoFromFile);
			testsInitialized = true;
		}
	}
	
	@Test
	public void testIfWeatherInfoIsInJsonFormat(){
		Validator.validateJsonFormat(currentWeatherInfoFromFile);
	}
	
	@Test
	public void testIfTemperatureIsValid() {
		int temperature = currentWeatherParser.getTemperature();
		try {
			Validator.validateTemperature(temperature, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfMinTemperatureIsValid() {
		int minTemperature = currentWeatherParser.getMinTemperature();
		try {
			Validator.validateTemperature(minTemperature, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfMaxTemperatureIsValid() {
		int maxTemperature = currentWeatherParser.getMaxTemperature();
		try {
			Validator.validateTemperature(maxTemperature, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfGeoCoordinatesAreValid() {
		String geoCoords = currentWeatherParser.getGeoCoordinates();
		try {
			Validator.validateGeoCoordinates(geoCoords);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}

	@Test
	public void testThatCityNameIsntEmpty() {
		String cityName = currentWeatherParser.getCityName();
		assertFalse(cityName.isEmpty());
	}
}