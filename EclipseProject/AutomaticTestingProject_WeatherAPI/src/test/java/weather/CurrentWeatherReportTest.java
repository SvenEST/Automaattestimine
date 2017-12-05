package weather;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import file.FileUtility;
import testhelpers.Validator;

public class CurrentWeatherReportTest {
	
	private CurrentWeatherReport currentWeatherReport;
	private String units;
	private boolean testsInitialized;
	
	@Before
	public void setUpTests() {
		if (testsInitialized != true) {
			units = "metric";
			Path inputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\CurrentWeatherReportTesting\\CurrentWeatherInfo.txt");
			FileUtility fileUtility = new FileUtility();
			JSONObject currentWeatherInfoFromFile = null;
			try {
				currentWeatherInfoFromFile = new JSONObject(fileUtility.readFile(inputPath));
			} catch (JSONException e) {
				fail("Failure cause: " + e.getMessage());
			}
			currentWeatherReport = new CurrentWeatherReport(currentWeatherInfoFromFile);
		}
	}
	
	@Test
	public void testIfResponseTemperatureIsValid() {
		int temperature = currentWeatherReport.getTemperature();
		try {
			Validator.validateTemperature(temperature, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfResponseMinTemperatureIsValid() {
		int minTemperature = currentWeatherReport.getMinTemperature();
		try {
			Validator.validateTemperature(minTemperature, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfResponseMaxTemperatureIsValid() {
		int maxTemperature = currentWeatherReport.getMaxTemperature();
		try {
			Validator.validateTemperature(maxTemperature, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfGeoCoordinatesAreInRequiredFormat() {
		String geoCoords = currentWeatherReport.getGeoCoordinates();
		try {
			Validator.validateGeoCoordinates(geoCoords);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfReturnedWeatherInfoHasSameCityNameAsRequested() {
		String insertedCityName = "Tallinn";
		String returnedCityName = currentWeatherReport.getCityName();
		assertEquals(insertedCityName, returnedCityName);
	}
	
	@Test
	public void testIfReturnedWeatherInfoIsInJsonFormat(){
		JSONObject weatherInfo = currentWeatherReport.getWeatherInfo();
		Validator.validateJsonFormat(weatherInfo);
	}
}
