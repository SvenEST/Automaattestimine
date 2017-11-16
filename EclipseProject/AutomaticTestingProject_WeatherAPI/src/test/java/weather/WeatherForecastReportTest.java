package weather;

import static org.junit.Assert.assertEquals;
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

import file.FileUtility;
import testhelpers.Validator;

public class WeatherForecastReportTest {
	
	private static JSONObject weatherForecastInfoFromFile;
	private static boolean testsInitialized = false;
	
	@Mock
	WeatherForecastReport weatherForecastReport;
	
	@Before
	public void setUpTest() throws IOException {
		MockitoAnnotations.initMocks(this);
		if (testsInitialized == false) {
			Path inputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\WeatherForecastReportTesting\\WeatherForecastInfo.txt");
			FileUtility fileUtility = new FileUtility();
			try {
				weatherForecastInfoFromFile = new JSONObject(fileUtility.readFile(inputPath));
			} catch (JSONException e) {
				fail("Failure cause: " + e.getMessage());
			}
			testsInitialized = true;
		}
		weatherForecastReport = new WeatherForecastReport("Tallinn", "1a8a3563bee4967e64490dbfadf83b7e", "metric", 1);
	}
	
	@Test
	public void testIfReturnedWeatherForecastInfoIsInJsonFormat() {
		//Mockito.when(weatherForecastMock.getWeatherForecastInfo("Tallinn")).thenReturn(weatherForecastInfoMock);
		JSONObject forecastInfo = weatherForecastReport.getWeatherForecastInfo("Tallinn");
		Validator.validateJsonFormat(forecastInfo);
	}
	
	@Test
	public void testIfResoponseTemperatureIsValid() {
		int temperature = weatherForecastReport.getTemperature();
		String units = weatherForecastReport.getUnits();
		System.out.println(temperature);
		System.out.println(units);
		try {
			Validator.validateTemperature(temperature, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfReturnedMinTemperatureIsValid() {
		int minTemperature = weatherForecastReport.getMinTemperature();
		String units = weatherForecastReport.getUnits();
		try {
			Validator.validateTemperature(minTemperature, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfReturnedMaxTemperatureIsValid() {
		int maxTemperature = weatherForecastReport.getMaxTemperature();
		String units = weatherForecastReport.getUnits();
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
	public void testChangingUnitsToMetric() {
		String newUnit = "Metric";
		weatherForecastReport.changeUnits(newUnit);
		String resultUnit = weatherForecastReport.getUnits();
		assertEquals(newUnit, resultUnit);	
	}
	
	@Test
	public void testChangingUnitsToImperial() {
		String newUnit = "Imperial";
		weatherForecastReport.changeUnits(newUnit);
		String resultUnit = weatherForecastReport.getUnits();
		assertEquals(newUnit, resultUnit);
	}
	
	@Test
	public void testChangingUnitsToKelvin() {
		String newUnit = "Kelvin";
		weatherForecastReport.changeUnits(newUnit);
		String resultUnit = weatherForecastReport.getUnits();
		assertEquals(newUnit, resultUnit);
	}
	/*
	@Test
	public void testSettingNewApiUrl() {
		String newUrl = "http://api.openweathermap.org/data/2.5/forecast";
		weatherForecastReport.setApiUrl(newUrl);
		String resultUrl = weatherForecastReport.getApiUrl();
		assertEquals(newUrl, resultUrl);
	}*/
	
	@Test
	public void testSettingNewApiKey() {
		String newKey = "1a8a3563bee4967e64490dbfadf83b7e";
		weatherForecastReport.setApiKey(newKey);
		String resultKey = weatherForecastReport.getApiKey();
		assertEquals(newKey, resultKey);
	}
	
}
