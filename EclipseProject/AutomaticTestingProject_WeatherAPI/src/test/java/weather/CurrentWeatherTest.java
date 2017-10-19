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
		currentWeather.setApiUrl("http://api.openweathermap.org/data/2.5/weather?q=");
		try {
			currentWeatherInfo = currentWeather.getWeatherInfo("Tallinn");
		} catch (IOException e) {
			fail("All test will be ignored. Cause: " + e.getMessage());
		}
	}

	@Test
	public void testGetWeatherInfoJson(){
		JSONObject result;
		try {
			result = currentWeather.getWeatherInfo("Tallinn");
			assertTrue(result == (JSONObject)result);
			assertFalse(result.toString().isEmpty());
			assertTrue(result.toString().startsWith("{"));
			assertTrue(result.toString().endsWith("}"));
		} catch (IOException e) {
			fail("Failure caused by: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfResponseCurrentTemperatureIsValid() {
		int result = currentWeather.getTemperature(currentWeatherInfo);
		String units = currentWeather.getUnits();
		try {
			Validator.validateTemperature(result, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testGetTemperatureMetric() {
		currentWeather.changeUnits("Metric");
		JSONObject weatherInfo = null;
		try {
			weatherInfo = currentWeather.getWeatherInfo("Tallinn");
		} catch (IOException e) {
			e.printStackTrace();
		}
		int result = currentWeather.getTemperature(weatherInfo);
		assertTrue(result == (int)result);
		String units = currentWeather.getUnits();
		if (units == "Metric") {
			assertTrue(result < 100);
			assertTrue(result > -100);
		}else{
			fail("Units not in metric");
		}
	}
	
	@Test
	public void testGetTemperatureImperial() {
		currentWeather.changeUnits("Imperial");
		JSONObject weatherInfo = null;
		try {
			weatherInfo = currentWeather.getWeatherInfo("Tallinn");
		} catch (IOException e) {
			e.printStackTrace();
		}
		int result = currentWeather.getTemperature(weatherInfo);
		assertTrue(result == (int)result);
		String units = currentWeather.getUnits();
		if (units == "Imperial") {
			assertTrue(result < 212);
			assertTrue(result > -148);
		}else{
			fail("Units not in imperial");
		}
	}
	
	@Test
	public void testGetTemperatureKelvin() {
		currentWeather.changeUnits("Kelvin");
		JSONObject weatherInfo = null;
		try {
			weatherInfo = currentWeather.getWeatherInfo("Tallinn");
		} catch (IOException e) {
			e.printStackTrace();
		}
		int result = currentWeather.getTemperature(weatherInfo);
		assertTrue(result == (int)result);
		String units = currentWeather.getUnits();
		if (units == "Kelvin") {
			assertTrue(result < 373);
			assertTrue(result > 173);
		}else{
			fail("Units not in kelvin");
		}
	}
	
	@Test
	public void testGetMinTemperature() {
		currentWeather.changeUnits("Metric");
		JSONObject weatherInfo = null;
		try {
			weatherInfo = currentWeather.getWeatherInfo("Tallinn");
		} catch (IOException e) {
			e.printStackTrace();
		}
		int result = currentWeather.getMinTemperature(weatherInfo);
		assertTrue(result == (int)result);
		String units = currentWeather.getUnits();
		if (units == "Metric") {
			assertTrue(result < 100);
			assertTrue(result > -100); 
		}
	}
	
	@Test
	public void testGetMaxTemperature() {
		currentWeather.changeUnits("Metric");
		JSONObject weatherInfo = null;
		try {
			weatherInfo = currentWeather.getWeatherInfo("Tallinn");
		} catch (IOException e) {
			e.printStackTrace();
		}
		int result = currentWeather.getMaxTemperature(weatherInfo);
		assertTrue(result == (int)result);
		String units = currentWeather.getUnits();
		if (units == "Metric") {
			assertTrue(result < 100);
			assertTrue(result > -100);
		}
	}
	
	@Test
	public void testGetCoordinates() {
		JSONObject weatherInfo = null;
		try {
			weatherInfo = currentWeather.getWeatherInfo("Tallinn");
		} catch (IOException e) {
			e.printStackTrace();
		}
		String result = currentWeather.getCoordinates(weatherInfo);
		assertTrue(result == (String)result);
		assertFalse(result.isEmpty());
		assertTrue(result.contains(":"));
		//assertTrue(result.length() == 7);
	}
	
	@Test
	public void testIfReturnedWeatherInfoHasSameCityNameAsRequest() {
		String cityName = "Tallinn";
		assertEquals(cityName, currentWeather.getCityName(currentWeatherInfo).toString());
	}
	
	@Test
	public void testChangeUnitsToMetric() {
		String newUnit = "Metric";
		String result = currentWeather.changeUnits(newUnit);
		assertEquals(newUnit, result);	
	}
	
	@Test
	public void testChangeUnitsToImperial() {
		String newUnit = "Imperial";
		String result = currentWeather.changeUnits(newUnit);
		assertEquals(newUnit, result);
	}
	
	@Test
	public void testChangeUnitsToKelvin() {
		String newUnit = "Kelvin";
		String result = currentWeather.changeUnits(newUnit);
		assertEquals(newUnit, result);
	}
	
	@Test
	public void testSetApiUrl() {
		String newUrl = "http://api.openweathermap.org/data/2.5/weather?q=";
		currentWeather.setApiUrl(newUrl);
		String url = currentWeather.getApiUrl();
		assertEquals(newUrl, url);
	}
	
	@Test
	public void testSetApiKey() {
		String newKey = "1a8a3563bee4967e64490dbfadf83b7e";
		currentWeather.setApiKey(newKey);
		String key = currentWeather.getApiKey();
		assertEquals(newKey, key);
	}
	
}
