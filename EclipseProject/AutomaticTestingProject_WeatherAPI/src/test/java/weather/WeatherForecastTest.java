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

import file.FileReader;
import testhelpers.Validator;

public class WeatherForecastTest {
	
	private static WeatherForecast weatherForecast;
	/*private static JSONObject weatherForecastInfo;*/
	private static JSONObject weatherForecastInfoMock;
	private static JSONObject forecastInfoForSingleDay;
	private static boolean testsInitialized = false;
	
	@Mock
	WeatherForecast weatherForecastMock;
	
	@Before
	public void setUpTest() throws IOException {
		MockitoAnnotations.initMocks(this);
		if (testsInitialized == false) {
			Path inputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\MockingInputs\\WeatherForecastInfo.txt");
			FileReader fileReader = new FileReader();
			try {
				weatherForecastInfoMock = new JSONObject(fileReader.readFile(inputPath));
			} catch (JSONException e) {
				fail("Failure cause: " + e.getMessage());
			}
			testsInitialized = true;
		}
		weatherForecast = new WeatherForecast("1a8a3563bee4967e64490dbfadf83b7e", "metric");
		/*weatherForecastInfo = weatherForecast.getWeatherForecastInfo("Tallinn");*/
		forecastInfoForSingleDay = weatherForecast.getForecastForSingleDay(weatherForecastInfoMock, 1);
	}
	
	@Test
	public void testIfReturnedWeatherForecastInfoIsInJsonFormat() {
		Mockito.when(weatherForecastMock.getWeatherForecastInfo("Tallinn")).thenReturn(weatherForecastInfoMock);
		JSONObject forecastInfo = weatherForecastMock.getWeatherForecastInfo("Tallinn");
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
		String geoCoords = weatherForecast.getCoordinates(weatherForecastInfoMock);
		try {
			Validator.validateGeoCoordinates(geoCoords);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfReturnedForecastInfoHasSameCityNameAsRequested() {
		String cityName = "Tallinn";
		assertEquals(cityName, weatherForecast.getCityName(weatherForecastInfoMock));
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
