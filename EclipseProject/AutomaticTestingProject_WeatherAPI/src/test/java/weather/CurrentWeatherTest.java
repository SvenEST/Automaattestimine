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
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import file.FileReader;
import testhelpers.Validator;

public class CurrentWeatherTest {
	
	private static JSONObject currentWeatherInfoMock;
	private static boolean testsInitialized = false;
	
	@Mock
	CurrentWeather currentWeatherMock;
	
	@Before
	public void setUpTests() throws IOException {
		MockitoAnnotations.initMocks(this);
		currentWeatherMock = new CurrentWeather("1a8a3563bee4967e64490dbfadf83b7e", "metric");
		
		if (testsInitialized == false) {
			Path inputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\MockingInputs\\CurrentWeatherInfo.txt");
			FileReader fileReader = new FileReader();
			try {
				currentWeatherInfoMock = new JSONObject(fileReader.readFile(inputPath));
			} catch (JSONException e) {
				fail("Failure cause: " + e.getMessage());
			}
			testsInitialized = true;
		}
	}

	@Test
	public void testIfReturnedWeatherInfoIsInJsonFormat(){
		when(currentWeatherMock.getWeatherInfo("Tallinn")).thenReturn(currentWeatherInfoMock);
		JSONObject weatherInfo = currentWeatherMock.getWeatherInfo("Tallinn");
		System.out.println(weatherInfo);
		Validator.validateJsonFormat(weatherInfo);
		/*verify(currentWeatherMock, times(1)).getWeatherInfo("Tallinn");*/
	}
	
	@Test
	public void testIfResponseCurrentTemperatureIsValid() {
		int temp = currentWeatherMock.getTemperature(currentWeatherInfoMock);
		String units = currentWeatherMock.getUnits();
		try {
			Validator.validateTemperature(temp, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
		verify(currentWeatherMock, times(1)).getTemperature(currentWeatherInfoMock);
		verify(currentWeatherMock, times(1)).getUnits();
	}
	
	@Test
	public void testIfResponseMinTemperatureIsValid() {
		int minTemp = currentWeatherMock.getMinTemperature(currentWeatherInfoMock);
		String units = currentWeatherMock.getUnits();
		try {
			Validator.validateTemperature(minTemp, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
		verify(currentWeatherMock, times(1)).getMinTemperature(currentWeatherInfoMock);
		verify(currentWeatherMock, times(1)).getUnits();
	}
	
	@Test
	public void testIfResponseMaxTemperatureIsValid() {
		int maxTemp = currentWeatherMock.getMaxTemperature(currentWeatherInfoMock);
		String units = currentWeatherMock.getUnits();
		try {
			Validator.validateTemperature(maxTemp, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
		verify(currentWeatherMock, times(1)).getMaxTemperature(currentWeatherInfoMock);
		verify(currentWeatherMock, times(1)).getUnits();
	}
	
	@Test
	public void testIfGeoCoordinatesAreInRequiredFormat() {
		String geoCoords = currentWeatherMock.getCoordinates(currentWeatherInfoMock);
		try {
			Validator.validateGeoCoordinates(geoCoords);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
		verify(currentWeatherMock, times(1)).getCoordinates(currentWeatherInfoMock);
	}
	
	@Test
	public void testIfReturnedWeatherInfoHasSameCityNameAsRequested() {
		String cityName = "Tallinn";
		assertEquals(cityName, currentWeatherMock.getCityName(currentWeatherInfoMock));
		verify(currentWeatherMock, times(1)).getCityName(currentWeatherInfoMock);
	}
	
	@Test
	public void testChangingUnitsToMetric() {
		String newUnit = "Metric";
		currentWeatherMock.changeUnits(newUnit);
		String resultUnit = currentWeatherMock.getUnits();
		assertEquals(newUnit, resultUnit);
		verify(currentWeatherMock, times(1)).changeUnits(newUnit);
		verify(currentWeatherMock, times(1)).getUnits();
	}
	
	@Test
	public void testChangingUnitsToImperial() {
		String newUnit = "Imperial";
		currentWeatherMock.changeUnits(newUnit);
		String resultUnit = currentWeatherMock.getUnits();
		assertEquals(newUnit, resultUnit);
		verify(currentWeatherMock, times(1)).changeUnits(newUnit);
		verify(currentWeatherMock, times(1)).getUnits();
	}
	
	@Test
	public void testChangingUnitsToKelvin() {
		String newUnit = "Kelvin"; 
		currentWeatherMock.changeUnits(newUnit);
		String resultUnit = currentWeatherMock.getUnits();
		assertEquals(newUnit, resultUnit);
		verify(currentWeatherMock, times(1)).changeUnits(newUnit);
		verify(currentWeatherMock, times(1)).getUnits();
	}
	
	@Test
	public void testSettingNewApiUrl() {
		String newUrl = "http://api.openweathermap.org/data/2.5/weather";
		currentWeatherMock.setApiUrl(newUrl);
		String resultUrl = currentWeatherMock.getApiUrl();
		assertEquals(newUrl, resultUrl);
		verify(currentWeatherMock, times(1)).setApiUrl(newUrl);
		verify(currentWeatherMock, times(1)).getApiUrl();
	}
	
	@Test
	public void testSettingNewApiKey() {
		String newKey = "1a8a3563bee4967e64490dbfadf83b7e";
		currentWeatherMock.setApiKey(newKey);
		String resultKey = currentWeatherMock.getApiKey();
		assertEquals(newKey, resultKey);
		verify(currentWeatherMock, times(1)).setApiKey(newKey);
		verify(currentWeatherMock, times(1)).getApiKey();
	}
	
}
