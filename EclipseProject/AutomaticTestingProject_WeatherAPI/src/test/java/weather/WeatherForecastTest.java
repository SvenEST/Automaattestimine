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

public class WeatherForecastTest {
	
	private static WeatherForecast weatherForecast;
	private static JSONObject weatherForecastInfo;
	
	@Before
	public void setUpTest() {
		weatherForecast = new WeatherForecast();
		weatherForecast.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		weatherForecast.setApiUrl("http://api.openweathermap.org/data/2.5/forecast");
		try {
			weatherForecastInfo = weatherForecast.getWeatherForecastInfo("Tallinn");
		} catch (IOException e) {
			fail("All test will be ignored. Cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfReturnedWeatherForecastInfoIsInJsonFormat() {
		JSONObject forecastInfo;
		try {
			forecastInfo = weatherForecast.getWeatherForecastInfo("Tallinn");
			assertTrue("Response result must be a JSONObject", forecastInfo == (JSONObject)forecastInfo);
			assertFalse("Response result can't be empty", forecastInfo.toString().isEmpty());
			assertTrue("Response result must start with '{'", forecastInfo.toString().startsWith("{"));
			assertTrue("Response result must end with '}'", forecastInfo.toString().endsWith("}"));
		} catch (IOException e) {
			fail("Failure caused by: " + e.getMessage());
		}
	}
	
	@Test
	public void testGetWeatherForecastInfoForSingleDayJson() {
		JSONObject forecastInfoForSingleDay = weatherForecast.getForecastForSingleDay(weatherForecastInfo, 1);
		assertTrue("Response result must be a JSONObject", forecastInfoForSingleDay == (JSONObject)forecastInfoForSingleDay);
		assertFalse("Response result can't be empty", forecastInfoForSingleDay.toString().isEmpty());
		assertTrue("Response result must start with '{'", forecastInfoForSingleDay.toString().startsWith("{"));
		assertTrue("Response result must end with '}'", forecastInfoForSingleDay.toString().endsWith("}"));
	}
	
	@Test
	public void testIfReturnedTemperatureIsValid() {
		int temp = weatherForecast.getTemperature(weatherForecastInfo);
		String units = weatherForecast.getUnits();
		try {
			Validator.validateTemperature(temp, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfReturnedMinTemperatureIsValid() {
		int minTemp = weatherForecast.getMinTemperature(weatherForecastInfo);
		String units = weatherForecast.getUnits();
		try {
			Validator.validateTemperature(minTemp, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfReturnedMaxTemperatureIsValid() {
		int maxTemp = weatherForecast.getMaxTemperature(weatherForecastInfo);
		String units = weatherForecast.getUnits();
		try {
			Validator.validateTemperature(maxTemp, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testGetCoordinates() {
		String geoCoords = weatherForecast.getCoordinates(weatherForecastInfo);
		assertTrue("Coordinates must be string type", geoCoords == (String)geoCoords);
		assertFalse("Returned coordinates can't be empty", geoCoords.isEmpty());
		assertTrue("Coordinates must include a ':' separator", geoCoords.contains(":"));
		//assertTrue(result.length() == 7);
	}
	
	@Test
	public void testIfReturnedForecastInfoHasSameCityNameAsRequested() {
		String cityName = "Tallinn";
		assertEquals(cityName, weatherForecast.getCityName(weatherForecastInfo));
	}
	
	@Test
	public void testChangingUnitsToMetric() {
		String newUnit = "Metric";
		weatherForecast.changeUnits(newUnit);
		String resultUnit = weatherForecast.getUnits();
		assertEquals(newUnit, resultUnit);	
	}
	
	@Test
	public void testChangingUnitsToImperial() {
		String newUnit = "Imperial";
		weatherForecast.changeUnits(newUnit);
		String resultUnit = weatherForecast.getUnits();
		assertEquals(newUnit, resultUnit);
	}
	
	@Test
	public void testChangingUnitsToKelvin() {
		String newUnit = "Kelvin";
		weatherForecast.changeUnits(newUnit);
		String resultUnit = weatherForecast.getUnits();
		assertEquals(newUnit, resultUnit);
	}

	@Test
	public void testSettingNewApiUrl() {
		String newUrl = "http://api.openweathermap.org/data/2.5/forecast";
		weatherForecast.setApiKey(newUrl);
		String resultUrl = weatherForecast.getApiKey();
		assertEquals(newUrl, resultUrl);
	}
	
	@Test
	public void testSettingNewApiKey() {
		String newKey = "1a8a3563bee4967e64490dbfadf83b7e";
		weatherForecast.setApiKey(newKey);
		String resultKey = weatherForecast.getApiKey();
		assertEquals(newKey, resultKey);
	}
	
}
