package weather;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import testHelpers.Validator;

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

	@Test
	public void testIfReturnedWeatherInfoIsInJSONformat(){
		JSONObject result;
		try {
			result = currentWeather.getWeatherInfo("Tallinn");
			assertTrue("Response result must be a JSONObject", result == (JSONObject)result);
			assertFalse("Response result can't be empty", result.toString().isEmpty());
			assertTrue("Response result must start with '{'", result.toString().startsWith("{"));
			assertTrue("Response result must end with '}'", result.toString().endsWith("}"));
		} catch (IOException e) {
			fail("Failure caused by: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfResponseCurrentTemperatureIsValid() {
		int temperature = currentWeather.getTemperature(currentWeatherInfo);
		String units = currentWeather.getUnits();
		try {
			Validator.validateTemperature(temperature, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfResponseMinTemperatureIsValid() {
		int minTemperature = currentWeather.getMinTemperature(currentWeatherInfo);
		String units = currentWeather.getUnits();
		try {
			Validator.validateTemperature(minTemperature, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfResponseMaxTemperatureIsValid() {
		int maxTemperature = currentWeather.getMaxTemperature(currentWeatherInfo);
		String units = currentWeather.getUnits();
		try {
			Validator.validateTemperature(maxTemperature, units);
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
		assertEquals(cityName, currentWeather.getCityName(currentWeatherInfo).toString());
	}
	
	@Test
	public void testChangingUnitsToMetric() {
		String newUnit = "Metric";
		String result = currentWeather.changeUnits(newUnit);
		assertEquals(newUnit, result);	
	}
	
	@Test
	public void testChangingUnitsToImperial() {
		String newUnit = "Imperial";
		String result = currentWeather.changeUnits(newUnit);
		assertEquals(newUnit, result);
	}
	
	@Test
	public void testChangingUnitsToKelvin() {
		String newUnit = "Kelvin"; 
		String result = currentWeather.changeUnits(newUnit);
		assertEquals(newUnit, result);
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
