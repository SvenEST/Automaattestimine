package weather;

import static org.junit.Assert.*;
import java.io.IOException;

import org.json.JSONObject;
import org.junit.Test;

public class WeatherForecastTest {
	
	@Test
	public void testGetWeatherForecastInfo() {
		WeatherForecast weatherForecast = new WeatherForecast();
		weatherForecast.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		weatherForecast.setApiUrl("http://api.openweathermap.org/data/2.5/forecast?q=");
		JSONObject result;
		try {
			result = weatherForecast.getWeatherForecastInfo("Tallinn");
			assertTrue(result == (JSONObject)result);
			assertFalse(result.toString().isEmpty());
			assertTrue(result.toString().startsWith("{"));
			assertTrue(result.toString().endsWith("}"));
		} catch (IOException e) {
			fail("Failure caused by: " + e.getMessage());
		}
	}
	
	@Test
	public void testGetForecastForDay() {  //See on halvasti, ei ütle mida test teeb
		WeatherForecast weatherForecast = new WeatherForecast();
		weatherForecast.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		weatherForecast.setApiUrl("http://api.openweathermap.org/data/2.5/forecast?q=");
		JSONObject forecastInfo = null;
		try {
			forecastInfo = weatherForecast.getWeatherForecastInfo("Tallinn");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		JSONObject result = weatherForecast.getForecastForDay(forecastInfo, 1);
		assertTrue(result == (JSONObject)result);
		assertFalse(result.toString().isEmpty());
		assertTrue(result.toString().startsWith("{"));
		assertTrue(result.toString().endsWith("}"));
	}
	
	@Test
	public void testGetTemperatureMetric() {
		WeatherForecast weatherForecast = new WeatherForecast();
		weatherForecast.changeUnit("Metric");
		weatherForecast.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		weatherForecast.setApiUrl("http://api.openweathermap.org/data/2.5/forecast?q=");
		JSONObject forecastInfo = null;
		try {
			forecastInfo = weatherForecast.getWeatherForecastInfo("Tallinn");
		} catch (IOException e) {
			e.printStackTrace();
		}
		int result = weatherForecast.getTemperature(weatherForecast.getForecastForDay(forecastInfo, 1));
		assertTrue(result == (int)result);
		String units = weatherForecast.getUnits();
		if (units == "Metric") {
			assertTrue(result < 100);
			assertTrue(result > -100);
		}else{
			fail("Units not in metric");
		}
	}
	
	@Test
	public void testGetTemperatureImperial() {
		WeatherForecast weatherForecast = new WeatherForecast();
		weatherForecast.changeUnit("Imperial");
		weatherForecast.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		weatherForecast.setApiUrl("http://api.openweathermap.org/data/2.5/forecast?q=");
		JSONObject forecastInfo = null;
		try {
			forecastInfo = weatherForecast.getWeatherForecastInfo("Tallinn");
		} catch (IOException e) {
			e.printStackTrace();
		}
		int result = weatherForecast.getTemperature(weatherForecast.getForecastForDay(forecastInfo, 1));
		assertTrue(result == (int)result);
		String units = weatherForecast.getUnits();
		if (units == "Imperial") {
			assertTrue(result < 212);
			assertTrue(result > -148);
		}else{
			fail("Units not in imperial");
		}
	}
	
	@Test
	public void testGetTemperatureKelvin() {
		WeatherForecast weatherForecast = new WeatherForecast();
		weatherForecast.changeUnit("Kelvin");
		weatherForecast.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		weatherForecast.setApiUrl("http://api.openweathermap.org/data/2.5/forecast?q=");
		JSONObject forecastInfo = null;
		try {
			forecastInfo = weatherForecast.getWeatherForecastInfo("Tallinn");
		} catch (IOException e) {
			e.printStackTrace();
		}
		int result = weatherForecast.getTemperature(weatherForecast.getForecastForDay(forecastInfo, 1));
		assertTrue(result == (int)result);
		String units = weatherForecast.getUnits();
		if (units == "Kelvin") {
			assertTrue(result < 373);
			assertTrue(result > -173);
		}else{
			fail("Units not in Kelvin");
		}
	}

	@Test
	public void testGetMinTemperature() {
		WeatherForecast weatherForecast = new WeatherForecast();
		weatherForecast.changeUnit("Metric");
		weatherForecast.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		weatherForecast.setApiUrl("http://api.openweathermap.org/data/2.5/forecast?q=");
		JSONObject forecastInfo = null;
		try {
			forecastInfo = weatherForecast.getWeatherForecastInfo("Tallinn");
		} catch (IOException e) {
			e.printStackTrace();
		}
		int result = weatherForecast.getMinTemperature(weatherForecast.getForecastForDay(forecastInfo, 1));
		assertTrue(result == (int)result);
		String units = weatherForecast.getUnits();
		if (units == "Metric") { 
			assertTrue(result < 100);
			assertTrue(result > -100);
		}
	}
	
	@Test
	public void testGetMaxTemperature() {
		WeatherForecast weatherForecast = new WeatherForecast();
		weatherForecast.changeUnit("Metric");
		weatherForecast.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		weatherForecast.setApiUrl("http://api.openweathermap.org/data/2.5/forecast?q=");
		JSONObject forecastInfo = null;
		try {
			forecastInfo = weatherForecast.getWeatherForecastInfo("Tallinn");
		} catch (IOException e) {
			e.printStackTrace();
		}
		int result = weatherForecast.getMaxTemperature(weatherForecast.getForecastForDay(forecastInfo, 1));
		assertTrue(result == (int)result);
		String units = weatherForecast.getUnits();
		if (units == "Metric") {
			assertTrue(result < 100);
			assertTrue(result > -100);
		}
	}
	
	@Test
	public void testGetCoordinates() {
		WeatherForecast weatherForecast = new WeatherForecast();
		weatherForecast.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		weatherForecast.setApiUrl("http://api.openweathermap.org/data/2.5/forecast?q=");
		JSONObject weatherInfo = null;
		try {
			weatherInfo = weatherForecast.getWeatherForecastInfo("Tallinn");
		} catch (IOException e) {
			e.printStackTrace();
		}
		String result = weatherForecast.getCoordinates(weatherInfo);
		assertTrue(result == (String)result);
		assertFalse(result.isEmpty());
		assertTrue(result.contains(":"));
		//assertTrue(result.length() == 7);
	}
	
	@Test
	public void testIfReturnedInfoHasSameCityName() {
		WeatherForecast weatherForecast = new WeatherForecast();
		weatherForecast.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		weatherForecast.setApiUrl("http://api.openweathermap.org/data/2.5/forecast?q=");
		String cityName = "Tallinn";
		JSONObject result;
		try {
			result = weatherForecast.getWeatherForecastInfo(cityName);
			assertEquals(cityName, weatherForecast.getCityName(result).toString());
		} catch (IOException e) {
			fail("Failure caused by: " + e.getMessage());
		}
	}

	@Test
	public void testSetApiUrl() {
		WeatherForecast weatherForecast = new WeatherForecast();
		String newUrl = "http://api.openweathermap.org/data/2.5/forecast?q=";
		weatherForecast.setApiKey(newUrl);
		String url = weatherForecast.getApiKey();
		assertEquals(newUrl, url);
	}
	
	@Test
	public void testSetApiKey() {
		WeatherForecast weatherForecast = new WeatherForecast();
		String newKey = "1a8a3563bee4967e64490dbfadf83b7e";
		weatherForecast.setApiKey(newKey);
		String key = weatherForecast.getApiKey();
		assertEquals(newKey, key);
	}
	
}
