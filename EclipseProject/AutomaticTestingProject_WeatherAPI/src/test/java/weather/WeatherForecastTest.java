package weather;

import static org.junit.Assert.*;
import java.io.IOException;

import org.json.JSONObject;
import org.junit.Test;

public class WeatherForecastTest {
	
	@Test
	public void testGetWeatherForecastInfo() {
		JSONObject result = null;
		try {
			result = WeatherForecast.getWeatherForecastInfo("http://api.openweathermap.org/data/2.5/forecast?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn,ee");
		} catch (IOException e) {
			fail("Failure caused by: " + e.getMessage());
		}
		assertTrue(result == (JSONObject)result);
		assertFalse(result.toString().isEmpty());
		assertTrue(result.toString().startsWith("{"));
		assertTrue(result.toString().endsWith("}"));
	}
	
	@Test
	public void getForecastForDay() {
		JSONObject result = null;
		try {
			result = WeatherForecast.getForecastForDay(WeatherForecast.getWeatherForecastInfo("http://api.openweathermap.org/data/2.5/forecast?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn,ee"), 1);
		} catch (IOException e) {
			fail("Failure caused by: " + e.getMessage());
		}
		assertTrue(result == (JSONObject)result);
		assertFalse(result.toString().isEmpty());
		assertTrue(result.toString().startsWith("{"));
		assertTrue(result.toString().endsWith("}"));
	}
	
	@Test
	public void testGetTemperatureMetric() {
		int result;
		WeatherForecast.changeUnit("Metric");
		String units = WeatherForecast.getUnits();
		try {
			result = WeatherForecast.getTemperature(WeatherForecast.getForecastForDay(WeatherForecast.getWeatherForecastInfo("http://api.openweathermap.org/data/2.5/forecast?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn,ee"), 1));
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
		WeatherForecast.changeUnit("Imperial");
		String units = WeatherForecast.getUnits();
		try {
			result = WeatherForecast.getTemperature(WeatherForecast.getForecastForDay(WeatherForecast.getWeatherForecastInfo("http://api.openweathermap.org/data/2.5/forecast?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn,ee"), 1));
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
		WeatherForecast.changeUnit("Kelvin");
		String units = WeatherForecast.getUnits();
		try {
			result = WeatherForecast.getTemperature(WeatherForecast.getForecastForDay(WeatherForecast.getWeatherForecastInfo("http://api.openweathermap.org/data/2.5/forecast?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn,ee"), 1));
			assertTrue(result == (int)result);
			if (units == "Kelvin") {
				assertTrue(result < 373);
				assertTrue(result > -173);
			}else{
				fail("Units not in Kelvin");
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
			result = WeatherForecast.getForecastForDay(WeatherForecast.getWeatherForecastInfo("http://api.openweathermap.org/data/2.5/forecast?q=", "1a8a3563bee4967e64490dbfadf83b7e", cityName), 1);
			assertEquals(cityName, WeatherForecast.getCityName(result).toString());
		} catch (IOException e) {
			fail("Failure caused by: " + e.getMessage());
		}
	}

}
