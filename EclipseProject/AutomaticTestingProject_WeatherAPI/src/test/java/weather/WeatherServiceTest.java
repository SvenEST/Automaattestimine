package weather;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import file.FileUtility;
import testhelpers.Validator;

public class WeatherServiceTest {
	
	private static WeatherService weatherService;
	private static boolean testsInitialized;

	@Before
	public void setUpTests() {
		if (testsInitialized != true) {
			weatherService = new WeatherService("Tallinn", "1a8a3563bee4967e64490dbfadf83b7e", "metric");
			
			Path currentWeatherInputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\WeatherServiceTesting\\CurrentWeatherInfo.txt");
			FileUtility fileUtility = new FileUtility();
			JSONObject currentWeatherInfoFromFile = null;
			try {
				currentWeatherInfoFromFile = new JSONObject(fileUtility.readFile(currentWeatherInputPath));
			} catch (JSONException e) {
				fail("Failure cause: " + e.getMessage());
			}
			
			Path weatherForecastInputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\WeatherServiceTesting\\WeatherForecastInfo.txt");
			FileUtility fileUtility2 = new FileUtility();
			JSONObject weatherForecastInfoFromFile = null;
			try {
				weatherForecastInfoFromFile = new JSONObject(fileUtility2.readFile(weatherForecastInputPath));
			} catch (JSONException e) {
				fail("Failure cause: " + e.getMessage());
			}
			
			CurrentWeatherReportFactory currentWeatherReportFactoryMock = Mockito.mock(CurrentWeatherReportFactory.class);
			CurrentWeatherReport dummyCurrentWeatherReport = new CurrentWeatherReport(currentWeatherInfoFromFile);
			Mockito.when(currentWeatherReportFactoryMock.createCurrentWeatherReport()).thenReturn(dummyCurrentWeatherReport);
			weatherService.updateCurrentWeather(currentWeatherReportFactoryMock);
			
			WeatherForecastReportFactory weatherForecastReportFactoryMock = Mockito.mock(WeatherForecastReportFactory.class);
			int dayNumber = 1;
			WeatherForecastReport dummyWeatherForecastReport = new WeatherForecastReport(weatherForecastInfoFromFile, dayNumber);
			Mockito.when(weatherForecastReportFactoryMock.createWeatherForecastReport(Mockito.anyInt())).thenReturn(dummyWeatherForecastReport);
			weatherService.updateForecastWeather(weatherForecastReportFactoryMock, dayNumber);
			
			testsInitialized = true;
		}
	}
	
	@Test
	public void testIfResponseTemperatureIsValid() {
		int temperature = weatherService.getCurrentTemperature();
		String units = weatherService.getUnits();
		try {
			Validator.validateTemperature(temperature, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfForecastTemperatureForSingleDayIsValid() {
		int temp = weatherService.getForecastTemperatureForDay();
		String units = weatherService.getUnits();
		try {
			Validator.validateTemperature(temp, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfForecastMinTemperatureForSingleDayIsValid() {
		int minTemp = weatherService.getForecastMinTemperatureForDay();
		String units = weatherService.getUnits();
		try {
			Validator.validateTemperature(minTemp, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfForecastMaxTemperatureForSingleDayIsValid() {
		int maxTemp = weatherService.getForecastMaxTemperatureForDay();
		String units = weatherService.getUnits();
		try {
			Validator.validateTemperature(maxTemp, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfReturnedGeoCoordinatesAreValid() {
		String geoCoordinates = weatherService.getGeoCoordinates();
		try {
			Validator.validateGeoCoordinates(geoCoordinates);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testSettingNewCityName() {
		String newCityName = "London";
		weatherService.setCityName(newCityName);
		String resultCityName = weatherService.getCityName();
		assertEquals(newCityName, resultCityName);
	}
	
	@Test
	public void testSettingNewCityNamesList() {
		List<String> newCityNames= new ArrayList<>();
		newCityNames.add("London");
		newCityNames.add("Los Angeles");
		weatherService.setCityNamesList(newCityNames);
		List<String> resultCityNames = weatherService.getCityNamesList();
		assertEquals(newCityNames, resultCityNames);
	}
	
	@Test
	public void testChangingUnitsToMetric() {
		String newUnit = "metric";
		weatherService.changeUnits(newUnit);
		String resultUnit = weatherService.getUnits();
		assertEquals(newUnit, resultUnit);
	}
	
	@Test
	public void testChangingUnitsToImperial() {
		String newUnit = "imperial";
		weatherService.changeUnits(newUnit);
		String resultUnit = weatherService.getUnits();
		assertEquals(newUnit, resultUnit);
	}
	
	@Test
	public void testChangingUnitsToKelvin() {
		String newUnit = "kelvin"; 
		weatherService.changeUnits(newUnit);
		String resultUnit = weatherService.getUnits();
		assertEquals(newUnit, resultUnit);
	}
	
	@Test
	public void testSettingNewApiKey() {
		String newKey = "1a8a3563bee4967e64490dbfadf83b7eTEST";
		weatherService.setApiKey(newKey);
		String resultKey = weatherService.getApiKey();
		assertEquals(newKey, resultKey);
	}
}
