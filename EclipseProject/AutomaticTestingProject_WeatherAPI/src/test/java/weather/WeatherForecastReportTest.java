package weather;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import testhelpers.Validator;

public class WeatherForecastReportTest {
	
	WeatherForecastReport weatherForecastReport;
	String units;
	
	@Before
	public void setUpTests() {
		weatherForecastReport = new WeatherForecastReport("Tallinn", "1a8a3563bee4967e64490dbfadf83b7e", "metric", 1);
		units = weatherForecastReport.getUnits();
	}
	
	@Test
	public void testIfResoponseTemperatureIsValid() {
		int temperature = weatherForecastReport.getTemperature();
		String units = weatherForecastReport.getUnits();
		try {
			Validator.validateTemperature(temperature, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfReturnedMinTemperatureIsValid() {
		int minTemperature = weatherForecastReport.getMinTemperature();
		String units = weatherForecastReport.getUnits();
		try {
			Validator.validateTemperature(minTemperature, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfReturnedMaxTemperatureIsValid() {
		int maxTemperature = weatherForecastReport.getMaxTemperature();
		String units = weatherForecastReport.getUnits();
		try {
			Validator.validateTemperature(maxTemperature, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testGetCoordinates() {
		String geoCoords = weatherForecastReport.getGeoCoordinates();
		try {
			Validator.validateGeoCoordinates(geoCoords);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfReturnedForecastInfoHasSameCityNameAsRequested() {
		String insertedCityName = "Tallinn";
		String returnedCityName = weatherForecastReport.getCityName();
		assertEquals(insertedCityName, returnedCityName);
	}
	
	@Test
	public void testIfReturnedForecastInfoIsInJsonFormat(){
		JSONObject weatherInfo = weatherForecastReport.getForecastInfo();
		Validator.validateJsonFormat(weatherInfo);
	}
}
