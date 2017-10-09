package weather;

import static org.junit.Assert.*;
import java.io.IOException;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class WeatherForecastTest {
	
	private static WeatherForecast weatherForecast;
	
	@Before
	public void setUpTest() {
		weatherForecast = new WeatherForecast();
		weatherForecast.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		weatherForecast.setApiUrl("http://api.openweathermap.org/data/2.5/forecast?q=");
	}
	
	@Test
	public void testGetWeatherForecastInfoJson() {
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
	public void testGetWeatherForecastInfoForSingleDayJson() {
		JSONObject forecastInfo = null;
		try {
			forecastInfo = weatherForecast.getWeatherForecastInfo("Tallinn");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		JSONObject result = weatherForecast.getForecastForSingleDay(forecastInfo, 1);
		assertTrue(result == (JSONObject)result);
		assertFalse(result.toString().isEmpty());
		assertTrue(result.toString().startsWith("{"));
		assertTrue(result.toString().endsWith("}"));
	}
	
	@Test
	public void testGetTemperatureMetric() {
		weatherForecast.changeUnits("Metric");
		JSONObject forecastInfo = null;
		try {
			forecastInfo = weatherForecast.getWeatherForecastInfo("Tallinn");
		} catch (IOException e) {
			e.printStackTrace();
		}
		int result = weatherForecast.getTemperature(weatherForecast.getForecastForSingleDay(forecastInfo, 1));
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
		weatherForecast.changeUnits("Imperial");
		JSONObject forecastInfo = null;
		try {
			forecastInfo = weatherForecast.getWeatherForecastInfo("Tallinn");
		} catch (IOException e) {
			e.printStackTrace();
		}
		int result = weatherForecast.getTemperature(weatherForecast.getForecastForSingleDay(forecastInfo, 1));
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
		weatherForecast.changeUnits("Kelvin");
		JSONObject forecastInfo = null;
		try {
			forecastInfo = weatherForecast.getWeatherForecastInfo("Tallinn");
		} catch (IOException e) {
			e.printStackTrace();
		}
		int result = weatherForecast.getTemperature(weatherForecast.getForecastForSingleDay(forecastInfo, 1));
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
		weatherForecast.changeUnits("Metric");
		JSONObject forecastInfo = null;
		try {
			forecastInfo = weatherForecast.getWeatherForecastInfo("Tallinn");
		} catch (IOException e) {
			e.printStackTrace();
		}
		int result = weatherForecast.getMinTemperature(weatherForecast.getForecastForSingleDay(forecastInfo, 1));
		assertTrue(result == (int)result);
		String units = weatherForecast.getUnits();
		if (units == "Metric") { 
			assertTrue(result < 100);
			assertTrue(result > -100);
		}
	}
	
	@Test
	public void testGetMaxTemperature() {
		weatherForecast.changeUnits("Metric");
		JSONObject forecastInfo = null;
		try {
			forecastInfo = weatherForecast.getWeatherForecastInfo("Tallinn");
		} catch (IOException e) {
			e.printStackTrace();
		}
		int result = weatherForecast.getMaxTemperature(weatherForecast.getForecastForSingleDay(forecastInfo, 1));
		assertTrue(result == (int)result);
		String units = weatherForecast.getUnits();
		if (units == "Metric") {
			assertTrue(result < 100);
			assertTrue(result > -100);
		}
	}
	
	@Test
	public void testGetCoordinates() {
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
	public void testIfReturnedForecastInfoHasSameCityNameAsResult() {
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
	public void testChangeUnitsMetric() {
		String newUnit = "Metric";
		String result = weatherForecast.changeUnits(newUnit);
		assertEquals(newUnit, result);	
	}
	
	@Test
	public void testChangeUnitsImperial() {
		String newUnit = "Imperial";
		String result = weatherForecast.changeUnits(newUnit);
		System.out.println(result);
		assertEquals(newUnit, result);
	}
	
	@Test
	public void testChangeUnitsKelvin() {
		String newUnit = "Kelvin";
		String result = weatherForecast.changeUnits(newUnit);
		assertEquals(newUnit, result);
	}

	@Test
	public void testSetApiUrl() {
		String newUrl = "http://api.openweathermap.org/data/2.5/forecast?q=";
		weatherForecast.setApiKey(newUrl);
		String url = weatherForecast.getApiKey();
		assertEquals(newUrl, url);
	}
	
	@Test
	public void testSetApiKey() {
		String newKey = "1a8a3563bee4967e64490dbfadf83b7e";
		weatherForecast.setApiKey(newKey);
		String key = weatherForecast.getApiKey();
		assertEquals(newKey, key);
	}
	
}
