package weather;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;

public class WeatherForecastTest {

	@Test
	public void testOpenApiConnection(){
		boolean result = false;
		try {
			result = WeatherForecast.openApiConnection("http://api.apixu.com/v1/current.json?key=14f133a36340433dbd6133833171309&q=London");
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(true, result);
	}

	@Test
	public void testGetWeatherInfo(){
		String result = null;
		try {
			result = WeatherForecast.getWeatherInfo("http://api.apixu.com/v1/current.json?key=14f133a36340433dbd6133833171309&q=", "London");
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(result.isEmpty());
	}

	@Test
	public void testGetCurrentTemperature() {
		int result = WeatherForecast.getCurrentTemperature();
		assertTrue(result != 0);
	}

	@Test
	public void testGetMaxTemperature() {
		int result = WeatherForecast.getMaxTemperature();
		assertTrue(result != 0);
	}

	@Test
	public void testGetMinTemperature() {
		int result = WeatherForecast.getMinTemperature();
		assertTrue(result != 0);
	}

	@Test
	public void testGetCoordinates() {
		String result = WeatherForecast.getCoordinates();
		assertTrue(result.isEmpty());
	}

}
