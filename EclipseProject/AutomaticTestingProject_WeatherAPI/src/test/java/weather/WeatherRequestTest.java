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
	
	private WeatherRequest weatherRequest;

	@Before
	public void setUpTests() {
		weatherRequest = Mockito.mock(WeatherRequest.class);
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
		try {
			Validator.validateTemperature(temperature, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfForecastTemperatureForSingleDayIsValid() {
		int dayNumber = 1;
		
		Mockito.when(weatherRequest.getForecastTemperatureForDay(dayNumber)).thenReturn(1);
		Mockito.when(weatherRequest.getUnits()).thenReturn("metric");
		
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
		
		Mockito.when(weatherRequest.getForecastTemperatureForDay(dayNumber)).thenReturn(1);
		Mockito.when(weatherRequest.getUnits()).thenReturn("metric");
		
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
		
		Mockito.when(weatherRequest.getForecastTemperatureForDay(dayNumber)).thenReturn(1);
		Mockito.when(weatherRequest.getUnits()).thenReturn("metric");
		
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
		Mockito.when(weatherRequest.getCityName()).thenReturn("Tallinn");
		
		String newCityName = "Tallinn";
		weatherRequest.setCityName(newCityName);
		String resultCityName = weatherRequest.getCityName();
		assertEquals(newCityName, resultCityName);
	}
	
	@Test
	public void testChangingUnitsToMetric() {
		Mockito.when(weatherRequest.getUnits()).thenReturn("metric");
		
		String newUnit = "metric";
		weatherRequest.changeUnits(newUnit);
		String resultUnit = weatherRequest.getUnits();
		assertEquals(newUnit, resultUnit);
	}
	
	@Test
	public void testChangingUnitsToImperial() {
		Mockito.when(weatherRequest.getUnits()).thenReturn("imperial");
		
		String newUnit = "imperial";
		weatherRequest.changeUnits(newUnit);
		String resultUnit = weatherRequest.getUnits();
		assertEquals(newUnit, resultUnit);
	}
	
	@Test
	public void testChangingUnitsToKelvin() {
		Mockito.when(weatherRequest.getUnits()).thenReturn("kelvin");
		
		String newUnit = "kelvin"; 
		weatherRequest.changeUnits(newUnit);
		String resultUnit = weatherRequest.getUnits();
		assertEquals(newUnit, resultUnit);
	}
}
