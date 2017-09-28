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
		JSONObject result;
		try {
			result = CurrentWeather.getWeatherInfoJson("http://api.openweathermap.org/data/2.5/weather?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn, ee");
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
		int result;
		CurrentWeather.changeUnit("Metric");
		String units = CurrentWeather.getUnits();
		try {
			result = CurrentWeather.getTemperature(CurrentWeather.getWeatherInfoJson("http://api.openweathermap.org/data/2.5/weather?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn, ee"));
			assertTrue(result == (int)result);
			if (units == "Metric") {
				assertTrue(result < 100);
				assertTrue(result > -100);
			}else{
				fail("Units not in metric");
			}
		} catch (IOException e) {
			fail("Failure caused by: " + e.getMessage());
		}
	}
	
	@Test
	public void testGetTemperatureImperial() {
		int result;
		CurrentWeather.changeUnit("Imperial");
		String units = CurrentWeather.getUnits();
		try {
			result = CurrentWeather.getTemperature(CurrentWeather.getWeatherInfoJson("http://api.openweathermap.org/data/2.5/weather?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn, ee"));
			assertTrue(result == (int)result);
			if (units == "Imperial") {
				assertTrue(result < 212);
				assertTrue(result > -148);
			}else{
				fail("Units not in imperial");
			}
		} catch (IOException e) {
			fail("Failure caused by: " + e.getMessage());
		}
	}
	
	@Test
	public void testGetTemperatureKelvin() {
		int result;
		CurrentWeather.changeUnit("Kelvin");
		String units = CurrentWeather.getUnits();
		try {
			result = CurrentWeather.getTemperature(CurrentWeather.getWeatherInfoJson("http://api.openweathermap.org/data/2.5/weather?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn, ee"));
			assertTrue(result == (int)result);
			if (units == "Kelvin") {
				assertTrue(result < 373);
				assertTrue(result > 173);
			}else{
				fail("Units not in kelvin");
			}
		} catch (IOException e) {
			fail("Failure caused by: " + e.getMessage());
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
			result = CurrentWeather.getWeatherInfoJson("http://api.openweathermap.org/data/2.5/weather?q=", "1a8a3563bee4967e64490dbfadf83b7e", cityName);
			assertEquals(cityName, CurrentWeather.getCityName(result).toString());
		} catch (IOException e) {
			fail("Failure caused by: " + e.getMessage());
		}
	}
}
