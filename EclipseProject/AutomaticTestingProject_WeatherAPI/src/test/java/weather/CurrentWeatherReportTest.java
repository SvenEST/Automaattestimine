package weather;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import file.FileUtility;

import testhelpers.Validator;

public class CurrentWeatherReportTest {
	
	final CurrentWeatherReport currentWeatherReport = new CurrentWeatherReport("Tallinn", "1a8a3563bee4967e64490dbfadf83b7e", "metric") {
		@Override
		public JSONObject getCurrentWeatherInfoFromApi(String cityName) {
			Path inputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\CurrentWeatherReportTesting\\CurrentWeatherInfo.txt");
			FileUtility fileUtility = new FileUtility();
			JSONObject currentWeatherInfoFromFile = null;
			try {
				currentWeatherInfoFromFile = new JSONObject(fileUtility.readFile(inputPath));
			} catch (JSONException e) {
				fail("Failure cause: " + e.getMessage());
			}
			return currentWeatherInfoFromFile;
		}
	};
	
	@Before
	public void setUpTests() throws IOException {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testIfReturnedWeatherInfoIsInJsonFormat(){
		JSONObject weatherInfo = currentWeatherReport.getCurrentWeatherInfoFromApi("Tallinn");
		Validator.validateJsonFormat(weatherInfo);
	}
	
	@Test
	public void testIfResponseTemperatureIsValid() {
		int temperature = currentWeatherReport.getTemperature();
		String units = currentWeatherReport.getUnits();
		try {
			Validator.validateTemperature(temperature, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfResponseMinTemperatureIsValid() {
		int minTemperature = currentWeatherReport.getMinTemperature();
		String units = currentWeatherReport.getUnits();
		try {
			Validator.validateTemperature(minTemperature, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfResponseMaxTemperatureIsValid() {
		int maxTemperature = currentWeatherReport.getMaxTemperature();
		String units = currentWeatherReport.getUnits();
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
		//Mockito.verify(currentWeatherReport, times(1)).getCityName();
	}
	
	@Test
	public void testChangingUnitsToMetric() {
		String newUnit = "Metric";
		currentWeatherReport.changeUnits(newUnit);
		String resultUnit = currentWeatherReport.getUnits();
		assertEquals(newUnit, resultUnit);
	}
	
	@Test
	public void testChangingUnitsToImperial() {
		String newUnit = "Imperial";
		currentWeatherReport.changeUnits(newUnit);
		String resultUnit = currentWeatherReport.getUnits();
		assertEquals(newUnit, resultUnit);
	}
	
	@Test
	public void testChangingUnitsToKelvin() {
		String newUnit = "Kelvin"; 
		currentWeatherReport.changeUnits(newUnit);
		String resultUnit = currentWeatherReport.getUnits();
		assertEquals(newUnit, resultUnit);
	}
	
	@Test
	public void testSettingNewApiKey() {
		String newKey = "1a8a3563bee4967e64490dbfadf83b7e";
		currentWeatherReport.setApiKey(newKey);
		String resultKey = currentWeatherReport.getApiKey();
		assertEquals(newKey, resultKey);
	}
}
