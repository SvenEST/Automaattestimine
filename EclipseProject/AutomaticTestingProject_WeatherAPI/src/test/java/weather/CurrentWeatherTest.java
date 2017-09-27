package weather;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.json.JSONObject;
import org.junit.Test;

public class CurrentWeatherTest {

	@Test
	public void testGetWeatherInfoJson(){
		JSONObject result;
		try {
			result = CurrentWeather.getWeatherInfoJson("http://api.openweathermap.org/data/2.5/weather?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn, ee", "Metric");
			assertTrue(result == (JSONObject)result);
			assertFalse(result.toString().isEmpty());
			assertTrue(result.toString().startsWith("{"));
			assertTrue(result.toString().endsWith("}"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetTemperature() {
		int result;
		String units = "Metric";
		try {
			result = CurrentWeather.getTemperature(CurrentWeather.getWeatherInfoJson("http://api.openweathermap.org/data/2.5/weather?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn, ee", units));
			assertTrue(result == (int)result);
			if (units == "Metric") {
				assertTrue(result < 100);
				assertTrue(result > -100);
			}
		} catch (IOException e) {
			e.printStackTrace();
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
}
