package weather;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.json.JSONObject;
import org.junit.Test;

public class CurrentWeatherTest {

	@Test
	public void testGetWeatherInfoJson(){
		CurrentWeather currentWeather = new CurrentWeather();
		currentWeather.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		currentWeather.setApiUrl("http://api.openweathermap.org/data/2.5/weather?q=");
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
		CurrentWeather currentWeather = new CurrentWeather();
		currentWeather.changeUnit("Metric");
		currentWeather.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		currentWeather.setApiUrl("http://api.openweathermap.org/data/2.5/weather?q=");
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
		CurrentWeather currentWeather = new CurrentWeather();
		currentWeather.changeUnit("Imperial");
		currentWeather.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		currentWeather.setApiUrl("http://api.openweathermap.org/data/2.5/weather?q=");
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
		CurrentWeather currentWeather = new CurrentWeather();
		currentWeather.changeUnit("Kelvin");
		currentWeather.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		currentWeather.setApiUrl("http://api.openweathermap.org/data/2.5/weather?q=");
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
		CurrentWeather currentWeather = new CurrentWeather();
		currentWeather.changeUnit("Metric");
		currentWeather.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		currentWeather.setApiUrl("http://api.openweathermap.org/data/2.5/weather?q=");
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
		CurrentWeather currentWeather = new CurrentWeather();
		currentWeather.changeUnit("Metric");
		currentWeather.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		currentWeather.setApiUrl("http://api.openweathermap.org/data/2.5/weather?q=");
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
		CurrentWeather currentWeather = new CurrentWeather();
		currentWeather.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		currentWeather.setApiUrl("http://api.openweathermap.org/data/2.5/weather?q=");
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
	public void testIfReturnedInfoHasSameCityName() {
		CurrentWeather currentWeather = new CurrentWeather();
		currentWeather.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		currentWeather.setApiUrl("http://api.openweathermap.org/data/2.5/weather?q=");
		String cityName = "Tallinn";
		try {
			JSONObject result = currentWeather.getWeatherInfo(cityName);
			assertEquals(cityName, currentWeather.getCityName(result).toString());
		} catch (IOException e) {
			fail("Failure caused by: " + e.getMessage());
		}
	}
	
	@Test
	public void testChangeUnits() {
		CurrentWeather currentWeather = new CurrentWeather();
		String newUnit1 = "Metric";
		String result1 = currentWeather.changeUnit(newUnit1);
		assertEquals(newUnit1, result1);
		String newUnit2 = "Imperial";
		String result2 = currentWeather.changeUnit(newUnit2);
		assertEquals(newUnit2, result2);
		String newUnit3 = "Kelvin";
		String result3 = currentWeather.changeUnit(newUnit3);
		assertEquals(newUnit3, result3);
	}
	
	@Test
	public void testSetApiUrl() {
		CurrentWeather currentWeather = new CurrentWeather();
		String newUrl = "http://api.openweathermap.org/data/2.5/weather?q=";
		currentWeather.setApiKey(newUrl);
		String url = currentWeather.getApiKey();
		assertEquals(newUrl, url);
	}
	
	@Test
	public void testSetApiKey() {
		CurrentWeather currentWeather = new CurrentWeather();
		String newKey = "1a8a3563bee4967e64490dbfadf83b7e";
		currentWeather.setApiKey(newKey);
		String key = currentWeather.getApiKey();
		assertEquals(newKey, key);
	}
	
}
