package weather;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import file.FileReader;
import testhelpers.Validator;

public class CurrentWeatherTest {
	
	private static CurrentWeather currentWeather;
	private static JSONObject currentWeatherInfo;
	
	@Before
	public void setUpTests() {
		currentWeather = new CurrentWeather();
		currentWeather.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		currentWeather.setApiUrl("http://api.openweathermap.org/data/2.5/weather");
		try {
			currentWeatherInfo = currentWeather.getWeatherInfo("Tallinn");
		} catch (IOException e) {
			fail("All test will be ignored. Cause: " + e.getMessage());
		}
	}
	
	@Mock
	CurrentWeather currentWeatherMock;
	
	@Test
	public void firtsMockTest() throws IOException {
		Path inputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\MockingInputs\\CurrentWeatherInfo.txt");
		FileReader fileReader = new FileReader();
		JSONObject weatherInfo = null;
		try {
			weatherInfo = new JSONObject(fileReader.readFile(inputPath));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		MockitoAnnotations.initMocks(this);
		when(currentWeatherMock.getWeatherInfo("Tallinn")).thenReturn(weatherInfo);
		currentWeatherMock.getWeatherInfo("Tallinn");
		verify(currentWeatherMock, times(1)).getWeatherInfo("Tallinn");
	}

	@Test
	public void testIfReturnedWeatherInfoIsInJsonFormat(){
		JSONObject weatherInfo;
		try {
			weatherInfo = currentWeather.getWeatherInfo("Tallinn");
			assertTrue("Response result must be a JSONObject", weatherInfo == (JSONObject)weatherInfo);
			assertFalse("Response result can't be empty", weatherInfo.toString().isEmpty());
			assertTrue("Response result must start with '{'", weatherInfo.toString().startsWith("{"));
			assertTrue("Response result must end with '}'", weatherInfo.toString().endsWith("}"));
		} catch (IOException e) {
			fail("Failure caused by: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfResponseCurrentTemperatureIsValid() {
		int temp = currentWeather.getTemperature(currentWeatherInfo);
		String units = currentWeather.getUnits();
		try {
			Validator.validateTemperature(temp, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfResponseMinTemperatureIsValid() {
		int minTemp = currentWeather.getMinTemperature(currentWeatherInfo);
		String units = currentWeather.getUnits();
		try {
			Validator.validateTemperature(minTemp, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfResponseMaxTemperatureIsValid() {
		int maxTemp = currentWeather.getMaxTemperature(currentWeatherInfo);
		String units = currentWeather.getUnits();
		try {
			Validator.validateTemperature(maxTemp, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfGeoCoordinatesAreInRequiredFormat() {
		String geoCoords = currentWeather.getCoordinates(currentWeatherInfo);
		assertTrue("Coordinates must be string type", geoCoords == (String)geoCoords);
		assertFalse("Returned coordinates can't be empty", geoCoords.isEmpty());
		assertTrue("Coordinates must include a ':' separator", geoCoords.contains(":"));
		//assertTrue(result.length() == 7);
	}
	
	@Test
	public void testIfReturnedWeatherInfoHasSameCityNameAsRequested() {
		String cityName = "Tallinn";
		assertEquals(cityName, currentWeather.getCityName(currentWeatherInfo));
	}
	
	@Test
	public void testChangingUnitsToMetric() {
		String newUnit = "Metric";
		currentWeather.changeUnits(newUnit);
		String resultUnit = currentWeather.getUnits();
		assertEquals(newUnit, resultUnit);	
	}
	
	@Test
	public void testChangingUnitsToImperial() {
		String newUnit = "Imperial";
		currentWeather.changeUnits(newUnit);
		String resultUnit = currentWeather.getUnits();
		assertEquals(newUnit, resultUnit);
	}
	
	@Test
	public void testChangingUnitsToKelvin() {
		String newUnit = "Kelvin"; 
		currentWeather.changeUnits(newUnit);
		String resultUnit = currentWeather.getUnits();
		assertEquals(newUnit, resultUnit);
	}
	
	@Test
	public void testSettingNewApiUrl() {
		String newUrl = "http://api.openweathermap.org/data/2.5/weather";
		currentWeather.setApiUrl(newUrl);
		String resultUrl = currentWeather.getApiUrl();
		assertEquals(newUrl, resultUrl);
	}
	
	@Test
	public void testSettingNewApiKey() {
		String newKey = "1a8a3563bee4967e64490dbfadf83b7e";
		currentWeather.setApiKey(newKey);
		String resultKey = currentWeather.getApiKey();
		assertEquals(newKey, resultKey);
	}
	
}
