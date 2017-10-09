package weather;

import static org.junit.Assert.*;
import java.io.IOException;

import org.json.JSONObject;
import org.junit.Test;

public class WeatherForecastTest {
	
	@Test
	public void testGetWeatherForecastInfo() {
		WeatherForecast.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		WeatherForecast.setApiUrl("http://api.openweathermap.org/data/2.5/forecast?q=");
		JSONObject result;
		try {
			result = WeatherForecast.getWeatherForecastInfo("Tallinn");
			assertTrue(result == (JSONObject)result);
			assertFalse(result.toString().isEmpty());
			assertTrue(result.toString().startsWith("{"));
			assertTrue(result.toString().endsWith("}"));
		} catch (IOException e) {
			fail("Failure caused by: " + e.getMessage());
		}
	}
	
	@Test
	public void testGetForecastForDay() {                                      //See on halvasti, ei ütle mida test teeb
		WeatherForecast.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		WeatherForecast.setApiUrl("http://api.openweathermap.org/data/2.5/forecast?q=");
		JSONObject forecastInfo = null;
		try {
			forecastInfo = WeatherForecast.getWeatherForecastInfo("Tallinn");  //Staatiline meetod, halvasti
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		JSONObject result = WeatherForecast.getForecastForDay(forecastInfo, 1);
		assertTrue(result == (JSONObject)result);
		assertFalse(result.toString().isEmpty());
		assertTrue(result.toString().startsWith("{"));
		assertTrue(result.toString().endsWith("}"));
	}
	
	@Test
	public void testGetTemperatureMetric() {
		WeatherForecast.changeUnit("Metric");
		WeatherForecast.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		WeatherForecast.setApiUrl("http://api.openweathermap.org/data/2.5/forecast?q=");
		JSONObject forecastInfo = null;
		try {
			forecastInfo = WeatherForecast.getWeatherForecastInfo("Tallinn");
		} catch (IOException e) {
			e.printStackTrace();
		}
		int result = WeatherForecast.getTemperature(WeatherForecast.getForecastForDay(forecastInfo, 1));
		assertTrue(result == (int)result);
		String units = WeatherForecast.getUnits();
		if (units == "Metric") {
			assertTrue(result < 100);
			assertTrue(result > -100);
		}else{
			fail("Units not in metric");
		}
	}
	
	@Test
	public void testGetTemperatureImperial() {
		WeatherForecast.changeUnit("Imperial");
		WeatherForecast.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		WeatherForecast.setApiUrl("http://api.openweathermap.org/data/2.5/forecast?q=");
		JSONObject forecastInfo = null;
		try {
			forecastInfo = WeatherForecast.getWeatherForecastInfo("Tallinn");
		} catch (IOException e) {
			e.printStackTrace();
		}
		int result = WeatherForecast.getTemperature(WeatherForecast.getForecastForDay(forecastInfo, 1));
		assertTrue(result == (int)result);
		String units = WeatherForecast.getUnits();
		if (units == "Imperial") {
			assertTrue(result < 212);
			assertTrue(result > -148);
		}else{
			fail("Units not in imperial");
		}
	}
	
	@Test
	public void testGetTemperatureKelvin() {
		WeatherForecast.changeUnit("Kelvin");
		WeatherForecast.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		WeatherForecast.setApiUrl("http://api.openweathermap.org/data/2.5/forecast?q=");
		JSONObject forecastInfo = null;
		try {
			forecastInfo = WeatherForecast.getWeatherForecastInfo("Tallinn");
		} catch (IOException e) {
			e.printStackTrace();
		}
		int result = WeatherForecast.getTemperature(WeatherForecast.getForecastForDay(forecastInfo, 1));
		assertTrue(result == (int)result);
		String units = WeatherForecast.getUnits();
		if (units == "Kelvin") {
			assertTrue(result < 373);
			assertTrue(result > -173);
		}else{
			fail("Units not in Kelvin");
		}
	}

	@Test
	public void testGetMinTemperature() {
		WeatherForecast.changeUnit("Metric");
		WeatherForecast.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		WeatherForecast.setApiUrl("http://api.openweathermap.org/data/2.5/forecast?q=");
		JSONObject forecastInfo = null;
		try {
			forecastInfo = WeatherForecast.getWeatherForecastInfo("Tallinn");
		} catch (IOException e) {
			e.printStackTrace();
		}
		int result = WeatherForecast.getMinTemperature(WeatherForecast.getForecastForDay(forecastInfo, 1));
		assertTrue(result == (int)result);
		String units = WeatherForecast.getUnits();
		if (units == "Metric") { 
			assertTrue(result < 100);
			assertTrue(result > -100);
		}
	}
	
	@Test
	public void testGetMaxTemperature() {
		WeatherForecast.changeUnit("Metric");
		WeatherForecast.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		WeatherForecast.setApiUrl("http://api.openweathermap.org/data/2.5/forecast?q=");
		JSONObject forecastInfo = null;
		try {
			forecastInfo = WeatherForecast.getWeatherForecastInfo("Tallinn");
		} catch (IOException e) {
			e.printStackTrace();
		}
		int result = WeatherForecast.getMaxTemperature(WeatherForecast.getForecastForDay(forecastInfo, 1));
		assertTrue(result == (int)result);
		String units = WeatherForecast.getUnits();
		if (units == "Metric") {
			assertTrue(result < 100);
			assertTrue(result > -100);
		}
	}
	
	@Test
	public void testGetCoordinates() {
		WeatherForecast.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		WeatherForecast.setApiUrl("http://api.openweathermap.org/data/2.5/forecast?q=");
		JSONObject weatherInfo = null;
		try {
			weatherInfo = WeatherForecast.getWeatherForecastInfo("Tallinn");
		} catch (IOException e) {
			e.printStackTrace();
		}
		String result = WeatherForecast.getCoordinates(weatherInfo);
		assertTrue(result == (String)result);
		assertFalse(result.isEmpty());
		assertTrue(result.contains(":"));
		//assertTrue(result.length() == 7);
	}
	
	@Test
	public void testIfReturnedInfoHasSameCityName() {
		WeatherForecast.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		WeatherForecast.setApiUrl("http://api.openweathermap.org/data/2.5/forecast?q=");
		String cityName = "Tallinn";
		JSONObject result;
		try {
			result = WeatherForecast.getWeatherForecastInfo(cityName);
			assertEquals(cityName, WeatherForecast.getCityName(result).toString());
		} catch (IOException e) {
			fail("Failure caused by: " + e.getMessage());
		}
	}

	@Test
	public void testSetApiUrl() {
		String newUrl = "http://api.openweathermap.org/data/2.5/forecast?q=";
		WeatherForecast.setApiKey(newUrl);
		String url = WeatherForecast.getApiKey();
		assertEquals(newUrl, url);
	}
	
	@Test
	public void testSetApiKey() {
		String newKey = "1a8a3563bee4967e64490dbfadf83b7e";
		WeatherForecast.setApiKey(newKey);
		String key = WeatherForecast.getApiKey();
		assertEquals(newKey, key);
	}
	
}
