package weather;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class CurrentWeatherTest {
	
	private static CurrentWeather currentWeather;
	
	@Before
	public void setUpTest() {
		currentWeather = new CurrentWeather();
		currentWeather.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		currentWeather.setApiUrl("http://api.openweathermap.org/data/2.5/weather?q=");
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
		try {
			JSONObject result = currentWeather.getWeatherInfo(cityName);
			assertEquals(cityName, currentWeather.getCityName(result).toString());
		} catch (IOException e) {
			fail("Failure caused by: " + e.getMessage());
		}
	}
	
	@Test
	public void testChangeUnitsMetric() {
		String newUnit = "Metric";
		String result = currentWeather.changeUnits(newUnit);
		assertEquals(newUnit, result);	
	}
	
	@Test
	public void testChangeUnitsImperial() {
		String newUnit = "Imperial";
		String result = currentWeather.changeUnits(newUnit);
		assertEquals(newUnit, result);
	}
	
	@Test
	public void testChangeUnitsKelvin() {
		String newUnit = "Kelvin";
		String result = currentWeather.changeUnits(newUnit);
		assertEquals(newUnit, result);
	}
	
	@Test
	public void testSetApiUrl() {
		String newUrl = "http://api.openweathermap.org/data/2.5/weather?q=";
		currentWeather.setApiKey(newUrl);
		String url = currentWeather.getApiKey();
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
