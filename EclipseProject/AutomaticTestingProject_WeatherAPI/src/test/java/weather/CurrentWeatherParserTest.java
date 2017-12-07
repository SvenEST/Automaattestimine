package weather;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import file.FileUtility;
import testhelpers.Validator;

public class CurrentWeatherParserTest {

	private static CurrentWeatherParser currentWeatherParser;
	private static String units;
	private static boolean testsInitialized;

	@Before
	public void setUpTests() throws IOException {
		if (testsInitialized != true) {
			Path inputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\CurrentWeatherParserTesting\\CurrentWeatherInfo.txt");
			FileUtility fileUtility = new FileUtility();
			JSONObject currentWeatherInfoFromFile = null;
			try {
				currentWeatherInfoFromFile = new JSONObject(fileUtility.readFile(inputPath));
			} catch (JSONException e) {
				fail("Failure cause: " + e.getMessage());
			}
			currentWeatherParser = new CurrentWeatherParser(currentWeatherInfoFromFile);
			units = "metric";
			testsInitialized = true;
		}
	}
	
	@Test
	public void testIfWeatherInfoIsInJsonFormat(){
		JSONObject weatherInfo = currentWeatherParser.getWeatherInfo();
		Validator.validateJsonFormat(weatherInfo);
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
	public void testThatReturnedCityNameIsntEmpty() {
		String cityName = currentWeatherParser.getCityName();
		assertFalse(cityName.isEmpty());
	}
}
