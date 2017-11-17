package weather;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import file.FileUtility;
import testhelpers.Validator;

public class WeatherRequestTest {

	private boolean testsInitialized;
	//private JSONObject currentWeatherInfoFromFile;
	//private JSONObject weatherForecastInfoFromFile;
	
	@Mock
	WeatherRequest weatherRequest;

	@Before
	public void setUpTests() {
		if (testsInitialized != true) {
			/*FileUtility fileUtility = new FileUtility();
			
			Path currentWeatherInputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\WeatherRequestTesting\\CurrentWeatherInfo.txt");
			try {
				currentWeatherInfoFromFile = new JSONObject(fileUtility.readFile(currentWeatherInputPath));
			} catch (JSONException e) {
				fail("Failure cause: " + e.getMessage());
			}
		
			Path weatherForecastinputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\WeatherRequestTesting\\WeatherForecastInfo.txt");
			try {
				weatherForecastInfoFromFile = new JSONObject(fileUtility.readFile(weatherForecastinputPath));
			} catch (JSONException e) {
				fail("Failure cause: " + e.getMessage());
			}*/
			
			//weatherRequest = new WeatherRequest("Tallinn", "metric");
			testsInitialized = true;
		}
	}
	
	@Test
	public void testWriteWeatherReportInfoToFile() {
		Path outputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\WeatherRequestTesting\\");
		Path inputFilePath = Paths.get(outputPath.toString(), "input.txt");
		WeatherRequest weatherRequestWithInputFile = new WeatherRequest(inputFilePath, "metric");
		
		boolean appendFile = false;
		weatherRequestWithInputFile.WriteWeatherReportInfoToFile(outputPath, appendFile);
		
		List<String> cityNamesList = weatherRequestWithInputFile.getCityNamesList();
		
		List<String> fileNames = new ArrayList<String>();
		for (String cityName: cityNamesList) {
			fileNames.add(cityName + "_current.txt");
			fileNames.add(cityName + "_forecast.txt");
		}
		
		FileUtility fileUtility = new FileUtility();
		for(String fileName: fileNames) {
			Path readPath = Paths.get(outputPath.toString(), fileName);
			String recievedOutputContent = fileUtility.readFile(readPath);
			assertFalse(recievedOutputContent.isEmpty());
		}
	}
	
	@Test
	public void testIfResponseTemperatureIsValid() {
		Mockito.when(weatherRequest.getCurrentTemperature()).thenReturn(1);
		Mockito.when(weatherRequest.getUnits()).thenReturn("metric");
		int temperature = weatherRequest.getCurrentTemperature();
		String units = weatherRequest.getUnits();
		System.out.println(temperature);
		System.out.println(units);
		try {
			Validator.validateTemperature(temperature, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfForecastTemperatureForSingleDayIsValid() {
		int dayNumber = 1;
		int temp = weatherRequest.getForecastTemperatureForDay(dayNumber);
		String units = weatherRequest.getUnits();
		try {
			Validator.validateTemperature(temp, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfForecastMinTemperatureForSingleDayIsValid() {
		int dayNumber = 1;
		int minTemp = weatherRequest.getForecastMinTemperatureForDay(dayNumber);
		String units = weatherRequest.getUnits();
		try {
			Validator.validateTemperature(minTemp, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfForecastMaxTemperatureForSingleDayIsValid() {
		int dayNumber = 1;
		int maxTemp = weatherRequest.getForecastMaxTemperatureForDay(dayNumber);
		String units = weatherRequest.getUnits();
		try {
			Validator.validateTemperature(maxTemp, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testSettingNewCityName() {
		String newCityName = "Tallinn";
		weatherRequest.setCityName(newCityName);
		String resultCityName = weatherRequest.getCityName();
		assertEquals(newCityName, resultCityName);
	}
	
	@Test
	public void testChangingUnitsToMetric() {
		String newUnit = "Metric";
		weatherRequest.changeUnits(newUnit);
		String resultUnit = weatherRequest.getUnits();
		assertEquals(newUnit, resultUnit);
	}
	
	@Test
	public void testChangingUnitsToImperial() {
		String newUnit = "Imperial";
		weatherRequest.changeUnits(newUnit);
		String resultUnit = weatherRequest.getUnits();
		assertEquals(newUnit, resultUnit);
	}
	
	@Test
	public void testChangingUnitsToKelvin() {
		String newUnit = "Kelvin"; 
		weatherRequest.changeUnits(newUnit);
		String resultUnit = weatherRequest.getUnits();
		assertEquals(newUnit, resultUnit);
	}
}
