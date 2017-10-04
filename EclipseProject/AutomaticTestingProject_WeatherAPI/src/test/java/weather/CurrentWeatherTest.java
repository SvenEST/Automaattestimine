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
		CurrentWeather.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		CurrentWeather.setApiUrl("http://api.openweathermap.org/data/2.5/weather?q=");
		JSONObject result;
		try {
			result = CurrentWeather.getWeatherInfoJson("Tallinn");
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
		CurrentWeather.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		CurrentWeather.setApiUrl("http://api.openweathermap.org/data/2.5/weather?q=");
		JSONObject weatherInfo = null;
		try {
			weatherInfo = CurrentWeather.getWeatherInfoJson("Tallinn");
		} catch (IOException e) {
			e.printStackTrace();
		}
		CurrentWeather.changeUnit("Metric");
		String units = CurrentWeather.getUnits();
		int result = CurrentWeather.getTemperature(weatherInfo);
		assertTrue(result == (int)result);
		if (units == "Metric") {
			assertTrue(result < 100);
			assertTrue(result > -100);
		}else{
			fail("Units not in metric");
		}
	}
	
	@Test
	public void testGetTemperatureImperial() {
		CurrentWeather.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		CurrentWeather.setApiUrl("http://api.openweathermap.org/data/2.5/weather?q=");
		JSONObject weatherInfo = null;
		try {
			weatherInfo = CurrentWeather.getWeatherInfoJson("Tallinn");
		} catch (IOException e) {
			e.printStackTrace();
		}
		CurrentWeather.changeUnit("Imperial");
		String units = CurrentWeather.getUnits();
		int result = CurrentWeather.getTemperature(weatherInfo);
		assertTrue(result == (int)result);
		if (units == "Imperial") {
			assertTrue(result < 212);
			assertTrue(result > -148);
		}else{
			fail("Units not in imperial");
		}
	}
	
	@Test
	public void testGetTemperatureKelvin() {
		CurrentWeather.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		CurrentWeather.setApiUrl("http://api.openweathermap.org/data/2.5/weather?q=");
		JSONObject weatherInfo = null;
		try {
			weatherInfo = CurrentWeather.getWeatherInfoJson("Tallinn");
		} catch (IOException e) {
			e.printStackTrace();
		}
		CurrentWeather.changeUnit("Kelvin");
		String units = CurrentWeather.getUnits();
		int result = CurrentWeather.getTemperature(weatherInfo);
		assertTrue(result == (int)result);
		if (units == "Kelvin") {
			assertTrue(result < 373);
			assertTrue(result > 173);
		}else{
			fail("Units not in kelvin");
		}
	}
	
	@Test
	public void testGetMaxTemperature() {
		String units = "Metric";
		int result = WeatherForecast.getMaxTemperature();
		assertTrue(result == (int)result);
		if (units == "Metric") {
			assertTrue(result < 100);
			assertTrue(result > -100);
		}
	}

	@Test
	public void testGetMinTemperature() {
		String units = "Metric";
		int result = WeatherForecast.getMinTemperature();
		assertTrue(result == (int)result);
		if (units == "Metric") {
			assertTrue(result < 100);
			assertTrue(result > -100); 
		}
	}
	
	@Test
	public void testGetCoordinates() {
		String result = CurrentWeather.getCoordinates();
		assertTrue(result == (String)result);
		assertFalse(result.isEmpty());
		assertTrue(result.length() == 7);
		assertEquals(result.charAt(4), ":");
	}
	
	@Test
	public void testIfReturnedInfoHasSameCityName() {
		String cityName = "Tallinn";
		JSONObject result;
		try {
			result = CurrentWeather.getWeatherInfoJson(cityName);
			assertEquals(cityName, CurrentWeather.getCityName(result).toString());
		} catch (IOException e) {
			fail("Failure caused by: " + e.getMessage());
		}
	}
	
	@Test
	public void testChangeUnits() {
		String newUnit =
	}
}
