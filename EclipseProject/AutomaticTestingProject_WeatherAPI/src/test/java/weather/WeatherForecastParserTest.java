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

public class WeatherForecastParserTest {

	private static WeatherForecastParser weatherForecastParser;
	private static String units;
	private static boolean testsInitialized;

	@Before
	public void setUpTests() throws IOException {
		if (testsInitialized != true) {
			Path inputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\WeatherForecastParserTesting\\WeatherForecastInfo.txt");
			FileUtility fileUtility = new FileUtility();
			JSONObject forecastInfoFromFile = null;
			try {
				forecastInfoFromFile = new JSONObject(fileUtility.readFile(inputPath));
			} catch (JSONException e) {
				fail("Failure cause: " + e.getMessage());
			}
			int dayNumber = 1;
			weatherForecastParser = new WeatherForecastParser(forecastInfoFromFile, dayNumber);
			units = "metric";
			testsInitialized = true;
		}
	}
	
	@Test
	public void testIfForecastInfoIsInJsonFormat(){
		JSONObject forecastInfo = weatherForecastParser.getForecastInfo();
		Validator.validateJsonFormat(forecastInfo);
	}
	
	@Test
	public void testIfForecastInfoForSingleDayIsValid() {
		JSONObject forecastInfoForSingleDay = weatherForecastParser.getSingleDayForecastInfo();
		Validator.validateJsonFormat(forecastInfoForSingleDay);
	}
	
	@Test
	public void testIfTemperatureIsValid() {
		int temperature = weatherForecastParser.getTemperature();
		try {
			Validator.validateTemperature(temperature, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfMinTemperatureIsValid() {
		int minTemperature = weatherForecastParser.getMinTemperature();
		try {
			Validator.validateTemperature(minTemperature, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfMaxTemperatureIsValid() {
		int maxTemperature = weatherForecastParser.getMaxTemperature();
		try {
			Validator.validateTemperature(maxTemperature, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfGeoCoordinatesAreValid() {
		String geoCoords = weatherForecastParser.getGeoCoordinates();
		try {
			Validator.validateGeoCoordinates(geoCoords);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}

	@Test
	public void testThatCityNameIsntEmpty() {
		String cityName = weatherForecastParser.getCityName();
		assertFalse(cityName.isEmpty());
	}
	
}
