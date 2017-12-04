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

public class WeatherForecastReportTest {
	
	private WeatherForecastReport weatherForecastReport;
	private boolean testsInitialized;
	private String units;
	
	@Before
	public void setUpTests() {
		if (testsInitialized != true) {
			units = "metric";
			Path inputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\WeatherForecastReportTesting\\WeatherForecastInfo.txt");
			FileUtility fileUtility = new FileUtility();
			JSONObject weatherForecastInfoFromFile = null;
			try {
				weatherForecastInfoFromFile = new JSONObject(fileUtility.readFile(inputPath));
			} catch (JSONException e) {
				fail("Failure cause: " + e.getMessage());
			}
			weatherForecastReport = new WeatherForecastReport(weatherForecastInfoFromFile, 1);
			testsInitialized = true;
		}
	}
	
	@Test
	public void testIfResoponseTemperatureIsValid() {
		int temperature = weatherForecastReport.getTemperature();
		try {
			Validator.validateTemperature(temperature, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfReturnedMinTemperatureIsValid() {
		int minTemperature = weatherForecastReport.getMinTemperature();
		try {
			Validator.validateTemperature(minTemperature, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfReturnedMaxTemperatureIsValid() {
		int maxTemperature = weatherForecastReport.getMaxTemperature();
		try {
			Validator.validateTemperature(maxTemperature, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testGetCoordinates() {
		String geoCoords = weatherForecastReport.getGeoCoordinates();
		try {
			Validator.validateGeoCoordinates(geoCoords);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfReturnedForecastInfoHasSameCityNameAsRequested() {
		String insertedCityName = "Tallinn";
		String returnedCityName = weatherForecastReport.getCityName();
		assertEquals(insertedCityName, returnedCityName);
	}
	
	@Test
	public void testIfReturnedForecastInfoIsInJsonFormat(){
		JSONObject weatherInfo = weatherForecastReport.getForecastInfo();
		Validator.validateJsonFormat(weatherInfo);
	}
}
