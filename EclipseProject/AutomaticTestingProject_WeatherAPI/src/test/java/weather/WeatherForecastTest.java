package weather;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import testhelpers.Validator;

public class WeatherForecastTest {
	
	private static WeatherForecast weatherForecast;
	private static JSONObject weatherForecastInfo;
	private static JSONObject forecastInfoForSingleDay;
	
	@Before
	public void setUpTest() {
		weatherForecast = new WeatherForecast();
		weatherForecast.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		weatherForecast.setApiUrl("http://api.openweathermap.org/data/2.5/forecast");
		weatherForecastInfo = weatherForecast.getWeatherForecastInfo("Tallinn");
		forecastInfoForSingleDay = weatherForecast.getForecastForSingleDay(weatherForecastInfo, 1);
	}
	
	@Test
	public void testIfReturnedWeatherForecastInfoIsInJsonFormat() {
		JSONObject forecastInfo = weatherForecast.getWeatherForecastInfo("Tallinn");
		Validator.validateJsonFormat(forecastInfo);
	}
	
	@Test
	public void testGetWeatherForecastInfoForSingleDayJson() {
		Validator.validateJsonFormat(forecastInfoForSingleDay);
	}
	
	@Test
	public void testIfReturnedTemperatureIsValid() {
		int temp = weatherForecast.getTemperature(forecastInfoForSingleDay);
		String units = weatherForecast.getUnits();
		try {
			Validator.validateTemperature(temp, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfReturnedMinTemperatureIsValid() {
		int minTemp = weatherForecast.getMinTemperature(forecastInfoForSingleDay);
		String units = weatherForecast.getUnits();
		try {
			Validator.validateTemperature(minTemp, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfReturnedMaxTemperatureIsValid() {
		int maxTemp = weatherForecast.getMaxTemperature(forecastInfoForSingleDay);
		String units = weatherForecast.getUnits();
		try {
			Validator.validateTemperature(maxTemp, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testGetCoordinates() {
		String geoCoords = weatherForecast.getCoordinates(weatherForecastInfo);
		try {
			Validator.validateGeoCoordinates(geoCoords);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfReturnedForecastInfoHasSameCityNameAsRequested() {
		String cityName = "Tallinn";
		assertEquals(cityName, weatherForecast.getCityName(weatherForecastInfo));
	}
	
	@Test
	public void testChangingUnitsToMetric() {
		String newUnit = "Metric";
		weatherForecast.changeUnits(newUnit);
		String resultUnit = weatherForecast.getUnits();
		assertEquals(newUnit, resultUnit);	
	}
	
	@Test
	public void testChangingUnitsToImperial() {
		String newUnit = "Imperial";
		weatherForecast.changeUnits(newUnit);
		String resultUnit = weatherForecast.getUnits();
		assertEquals(newUnit, resultUnit);
	}
	
	@Test
	public void testChangingUnitsToKelvin() {
		String newUnit = "Kelvin";
		weatherForecast.changeUnits(newUnit);
		String resultUnit = weatherForecast.getUnits();
		assertEquals(newUnit, resultUnit);
	}

	@Test
	public void testSettingNewApiUrl() {
		String newUrl = "http://api.openweathermap.org/data/2.5/forecast";
		weatherForecast.setApiKey(newUrl);
		String resultUrl = weatherForecast.getApiKey();
		assertEquals(newUrl, resultUrl);
	}
	
	@Test
	public void testSettingNewApiKey() {
		String newKey = "1a8a3563bee4967e64490dbfadf83b7e";
		weatherForecast.setApiKey(newKey);
		String resultKey = weatherForecast.getApiKey();
		assertEquals(newKey, resultKey);
	}
	
}
