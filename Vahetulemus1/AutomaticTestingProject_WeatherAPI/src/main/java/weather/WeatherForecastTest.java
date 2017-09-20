package weather;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;

public class WeatherForecastTest {

	@Test
	public void testInternetConnectionExists() {
		boolean result = WeatherForecast.internetConnectionExists();
		assertTrue(result);
	}

	@Test
	public void testGetWeatherInfoJson(){
		String result = null;
		try {
			result = WeatherForecast.getWeatherInfoJson("http://api.apixu.com/v1/current.json?key=14f133a36340433dbd6133833171309&q=", "London");
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(result == (String)result);
		assertFalse(result.isEmpty());
		assertTrue(result.startsWith("{"));
		assertTrue(result.endsWith("}"));
	}

	@Test
	public void testGetCurrentTemperatureCelsius() {
		int result = WeatherForecast.getCurrentTemperatureCelsius();
		assertTrue(result == (int)result);
		assertTrue(result < 100);
		assertTrue(result > -100);
	}
	
	@Test
	public void testGetCurrentTemperatureFahrenheit() {
		int result = WeatherForecast.getCurrentTemperatureFahrenheit();
		assertTrue(result == (int)result);
		assertTrue(result < 212);
		assertTrue(result > -148);
	}
	
	@Test
	public void testGetThreeDayForecast() {
		String result = WeatherForecast.getThreeDayForecast(1);
		assertTrue(result == (String)result);
		assertFalse(result.isEmpty());
		assertTrue(result.startsWith("{"));
		assertTrue(result.endsWith("}"));
	}

	@Test
	public void testGetMaxTemperatureCelsius() {
		int result = WeatherForecast.getMaxTemperatureCelsius();
		assertTrue(result == (int)result);
		assertTrue(result < 100);
		assertTrue(result > -100);
	}
	
	@Test
	public void testGetMaxTemperatureFahrenheit() {
		int result = WeatherForecast.getMaxTemperatureFahrenheit();
		assertTrue(result == (int)result);
		assertTrue(result < 212);
		assertTrue(result > -148);
	}

	@Test
	public void testGetMinTemperatureCelsius() {
		int result = WeatherForecast.getMinTemperatureCelsius();
		assertTrue(result == (int)result);
		assertTrue(result < 100);
		assertTrue(result > -100);
	}
	
	@Test
	public void testGetMinTemperatureFahrenheit() {
		int result = WeatherForecast.getMinTemperatureFahrenheit();
		assertTrue(result == (int)result);
		assertTrue(result < 212);
		assertTrue(result > -148);
	}

	@Test
	public void testGetCoordinates() {
		String result = WeatherForecast.getCoordinates();
		assertTrue(result == (String)result);
		assertFalse(result.isEmpty());
		assertTrue(result.length() == 7);
		assertEquals(result.charAt(4), ":");
	}

}
