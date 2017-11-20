package weather;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import testhelpers.Validator;

public class CurrentWeatherReportTest {

	CurrentWeatherReport currentWeatherReport;
	String units;
	
	@Before
	public void setUpTests() {
		currentWeatherReport = new CurrentWeatherReport("Tallinn", "1a8a3563bee4967e64490dbfadf83b7e", "metric");
		units = currentWeatherReport.getUnits();
	}
	
	@Test
	public void testIfResponseTemperatureIsValid() {
		int temperature = currentWeatherReport.getTemperature();
		try {
			Validator.validateTemperature(temperature, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfResponseMinTemperatureIsValid() {
		int minTemperature = currentWeatherReport.getMinTemperature();
		try {
			Validator.validateTemperature(minTemperature, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfResponseMaxTemperatureIsValid() {
		int maxTemperature = currentWeatherReport.getMaxTemperature();
		try {
			Validator.validateTemperature(maxTemperature, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfGeoCoordinatesAreInRequiredFormat() {
		String geoCoords = currentWeatherReport.getGeoCoordinates();
		try {
			Validator.validateGeoCoordinates(geoCoords);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfReturnedWeatherInfoHasSameCityNameAsRequested() {
		String insertedCityName = "Tallinn";
		String returnedCityName = currentWeatherReport.getCityName();
		assertEquals(insertedCityName, returnedCityName);
	}
	
	@Test
	public void testIfReturnedWeatherInfoIsInJsonFormat(){
		JSONObject weatherInfo = currentWeatherReport.getWeatherInfo();
		Validator.validateJsonFormat(weatherInfo);
	}
}
