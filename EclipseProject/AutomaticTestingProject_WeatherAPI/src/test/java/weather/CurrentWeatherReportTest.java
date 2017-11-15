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

import static org.mockito.Mockito.times;

import testhelpers.Validator;

public class CurrentWeatherReportTest {
	
	private JSONObject currentWeatherInfoFromFile;
	private static boolean testsInitialized;
	private CurrentWeatherReport currentWeatherReport;
	
	@Before
	public void setUpTests() throws IOException {
		MockitoAnnotations.initMocks(this);
		if (testsInitialized != true) {
			Path inputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\CurrentWeatherReportTesting\\CurrentWeatherInfo.txt");
			FileUtility fileUtility = new FileUtility();
			try {
				currentWeatherInfoFromFile = new JSONObject(fileUtility.readFile(inputPath));
			} catch (JSONException e) {
				fail("Failure cause: " + e.getMessage());
			}
			testsInitialized = true;
		}
		currentWeatherReport = new CurrentWeatherReport("Tallinn", "1a8a3563bee4967e64490dbfadf83b7e", "metric");
	}

	@Test
	public void testIfReturnedWeatherInfoIsInJsonFormat(){
		//Mockito.when(currentWeatherReport.getCurrentWeatherInfoFromApi("Tallinn")).thenReturn(currentWeatherInfoFromFile);
		Mockito.when(currentWeatherParser.)
		JSONObject weatherInfo = currentWeatherReport.getCurrentWeatherInfoFromApi("Tallinn");
		System.out.println(weatherInfo);
		System.out.println(currentWeatherInfoFromFile);
		Validator.validateJsonFormat(weatherInfo);
		//verify(currentWeatherReport, times(1)).getCurrentWeatherInfoFromApi("Tallinn");
	}
	
	@Test
	public void testIfResponseTemperatureIsValid() { 
		int temperature = currentWeatherReport.getTemperature();
		String units = currentWeatherReport.getUnits();
		try {
			Validator.validateTemperature(temperature, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
		Mockito.verify(currentWeatherReport, times(1)).getTemperature();
		Mockito.verify(currentWeatherReport, times(1)).getUnits();
	}
	
	@Test
	public void testIfResponseMinTemperatureIsValid() {
		int minTemperature = currentWeatherReport.getMinTemperature();
		String units = currentWeatherReport.getUnits();
		try {
			Validator.validateTemperature(minTemperature, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
		Mockito.verify(currentWeatherReport, times(1)).getMinTemperature();
		Mockito.verify(currentWeatherReport, times(1)).getUnits();
	}
	
	@Test
	public void testIfResponseMaxTemperatureIsValid() {
		int maxTemperature = currentWeatherReport.getMaxTemperature();
		String units = currentWeatherReport.getUnits();
		try {
			Validator.validateTemperature(maxTemperature, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
		Mockito.verify(currentWeatherReport, times(1)).getMaxTemperature();
		Mockito.verify(currentWeatherReport, times(1)).getUnits();
	}
	
	@Test
	public void testIfGeoCoordinatesAreInRequiredFormat() {
		String geoCoords = currentWeatherReport.getGeoCoordinates();
		try {
			Validator.validateGeoCoordinates(geoCoords);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
		Mockito.verify(currentWeatherReport, times(1)).getGeoCoordinates();
	}
	
	@Test
	public void testIfReturnedWeatherInfoHasSameCityNameAsRequested() {
		String insertedCityName = "Tallinn";
		String returnedCityName = currentWeatherReport.getCityName();
		assertEquals(insertedCityName, returnedCityName);
		Mockito.verify(currentWeatherReport, times(1)).getCityName();
	}
	
	@Test
	public void testChangingUnitsToMetric() {
		String newUnit = "Metric";
		currentWeatherReport.changeUnits(newUnit);
		String resultUnit = currentWeatherReport.getUnits();
		assertEquals(newUnit, resultUnit);
		Mockito.verify(currentWeatherReport, times(1)).changeUnits(newUnit);
		Mockito.verify(currentWeatherReport, times(1)).getUnits();
	}
	
	@Test
	public void testChangingUnitsToImperial() {
		String newUnit = "Imperial";
		currentWeatherReport.changeUnits(newUnit);
		String resultUnit = currentWeatherReport.getUnits();
		assertEquals(newUnit, resultUnit);
		Mockito.verify(currentWeatherReport, times(1)).changeUnits(newUnit);
		Mockito.verify(currentWeatherReport, times(1)).getUnits();
	}
	
	@Test
	public void testChangingUnitsToKelvin() {
		String newUnit = "Kelvin"; 
		currentWeatherReport.changeUnits(newUnit);
		String resultUnit = currentWeatherReport.getUnits();
		assertEquals(newUnit, resultUnit);
		Mockito.verify(currentWeatherReport, times(1)).changeUnits(newUnit);
		Mockito.verify(currentWeatherReport, times(1)).getUnits();
	}
	
	/*
	@Test
	public void testSettingNewApiUrl() {
		Mockito.when(currentWeatherMock.getApiUrl()).thenReturn("http://api.openweathermap.org/data/2.5/weather");
		String newUrl = "http://api.openweathermap.org/data/2.5/weather";
		currentWeatherMock.setApiUrl(newUrl);
		String resultUrl = currentWeatherMock.getApiUrl();
		assertEquals(newUrl, resultUrl);
		Mockito.verify(currentWeatherMock, times(1)).setApiUrl(newUrl);
		Mockito.verify(currentWeatherMock, times(1)).getApiUrl();
	}
	*/
	
	@Test
	public void testSettingNewApiKey() {
		String newKey = "1a8a3563bee4967e64490dbfadf83b7e";
		currentWeatherReport.setApiKey(newKey);
		String resultKey = currentWeatherReport.getApiKey();
		assertEquals(newKey, resultKey);
		Mockito.verify(currentWeatherReport, times(1)).setApiKey(newKey);
		Mockito.verify(currentWeatherReport, times(1)).getApiKey();
	}
}
