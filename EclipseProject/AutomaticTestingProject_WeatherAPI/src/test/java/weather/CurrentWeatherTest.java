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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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
		Mockito.when(currentWeatherMock.getWeatherInfo("Tallinn")).thenReturn(currentWeatherInfoMock);
		JSONObject weatherInfo = currentWeatherMock.getWeatherInfo("Tallinn");
		Validator.validateJsonFormat(weatherInfo);
		verify(currentWeatherMock, times(1)).getWeatherInfo("Tallinn");
	}
	
	@Test
	public void testIfResponseCurrentTemperatureIsValid() {
		Mockito.when(currentWeatherMock.getTemperature(currentWeatherInfoMock)).thenReturn(6);
		Mockito.when(currentWeatherMock.getUnits()).thenReturn("metric");
		int temp = currentWeatherMock.getTemperature(currentWeatherInfoMock);
		String units = currentWeatherMock.getUnits();
		try {
			Validator.validateTemperature(temp, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
		Mockito.verify(currentWeatherMock, times(1)).getTemperature(currentWeatherInfoMock);
		Mockito.verify(currentWeatherMock, times(1)).getUnits();
	}
	
	@Test
	public void testIfResponseMinTemperatureIsValid() {
		Mockito.when(currentWeatherMock.getTemperature(currentWeatherInfoMock)).thenReturn(-6);
		Mockito.when(currentWeatherMock.getUnits()).thenReturn("metric");
		int minTemp = currentWeatherMock.getMinTemperature(currentWeatherInfoMock);
		String units = currentWeatherMock.getUnits();
		try {
			Validator.validateTemperature(minTemp, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
		Mockito.verify(currentWeatherMock, times(1)).getMinTemperature(currentWeatherInfoMock);
		Mockito.verify(currentWeatherMock, times(1)).getUnits();
	}
	
	@Test
	public void testIfResponseMaxTemperatureIsValid() {
		Mockito.when(currentWeatherMock.getTemperature(currentWeatherInfoMock)).thenReturn(6);
		Mockito.when(currentWeatherMock.getUnits()).thenReturn("metric");
		int maxTemp = currentWeatherMock.getMaxTemperature(currentWeatherInfoMock);
		String units = currentWeatherMock.getUnits();
		try {
			Validator.validateTemperature(maxTemp, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
		Mockito.verify(currentWeatherMock, times(1)).getMaxTemperature(currentWeatherInfoMock);
		Mockito.verify(currentWeatherMock, times(1)).getUnits();
	}
	
	@Test
	public void testIfGeoCoordinatesAreInRequiredFormat() {
		Mockito.when(currentWeatherMock.getCoordinates(currentWeatherInfoMock)).thenReturn("24:94");
		String geoCoords = currentWeatherMock.getCoordinates(currentWeatherInfoMock);
		try {
			Validator.validateGeoCoordinates(geoCoords);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
		Mockito.verify(currentWeatherMock, times(1)).getCoordinates(currentWeatherInfoMock);
	}
	
	@Test
	public void testIfReturnedWeatherInfoHasSameCityNameAsRequested() {
		Mockito.when(currentWeatherMock.getCityName(currentWeatherInfoMock)).thenReturn("Tallinn");
		String insertedCityName = "Tallinn";
		String returnedCityName = currentWeatherMock.getCityName(currentWeatherInfoMock);
		assertEquals(insertedCityName, returnedCityName);
		Mockito.verify(currentWeatherMock, times(1)).getCityName(currentWeatherInfoMock);
	}
	
	@Test
	public void testChangingUnitsToMetric() {
		Mockito.when(currentWeatherMock.getUnits()).thenReturn("Metric");
		String newUnit = "Metric";
		currentWeatherMock.changeUnits(newUnit);
		String resultUnit = currentWeatherMock.getUnits();
		assertEquals(newUnit, resultUnit);
		Mockito.verify(currentWeatherMock, times(1)).changeUnits(newUnit);
		Mockito.verify(currentWeatherMock, times(1)).getUnits();
	}
	
	@Test
	public void testChangingUnitsToImperial() {
		Mockito.when(currentWeatherMock.getUnits()).thenReturn("Imperial");
		String newUnit = "Imperial";
		currentWeatherMock.changeUnits(newUnit);
		String resultUnit = currentWeatherMock.getUnits();
		assertEquals(newUnit, resultUnit);
		Mockito.verify(currentWeatherMock, times(1)).changeUnits(newUnit);
		Mockito.verify(currentWeatherMock, times(1)).getUnits();
	}
	
	@Test
	public void testChangingUnitsToKelvin() {
		Mockito.when(currentWeatherMock.getUnits()).thenReturn("Kelvin");
		String newUnit = "Kelvin"; 
		currentWeatherMock.changeUnits(newUnit);
		String resultUnit = currentWeatherMock.getUnits();
		assertEquals(newUnit, resultUnit);
		Mockito.verify(currentWeatherMock, times(1)).changeUnits(newUnit);
		Mockito.verify(currentWeatherMock, times(1)).getUnits();
	}
	
	@Test
	public void testSettingNewApiUrl() {
		Mockito.when(currentWeatherMock.getApiUrl()).thenReturn("http://api.openweathermap.org/data/2.5/weather");
		String newUrl = "http://api.openweathermap.org/data/2.5/weather";
		currentWeatherMock.setApiUrl(newUrl);
		String resultUrl = currentWeatherMock.getApiUrl();
		assertEquals(newUrl, resultUrl);
		Mockito.verify(currentWeatherMock, times(1)).setApiUrl(newUrl);
		Mockito.verify(currentWeatherMock, times(1)).getApiUrl();
	}
	
	@Test
	public void testSettingNewApiKey() {
		Mockito.when(currentWeatherMock.getApiKey()).thenReturn("1a8a3563bee4967e64490dbfadf83b7e");
		String newKey = "1a8a3563bee4967e64490dbfadf83b7e";
		currentWeatherMock.setApiKey(newKey);
		String resultKey = currentWeatherMock.getApiKey();
		assertEquals(newKey, resultKey);
		Mockito.verify(currentWeatherMock, times(1)).setApiKey(newKey);
		Mockito.verify(currentWeatherMock, times(1)).getApiKey();
	}
}
