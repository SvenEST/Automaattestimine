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
import org.mockito.Mockito;

import file.FileUtility;
import testhelpers.Validator;

public class WeatherRequestTest {
	
	private WeatherService weatherService;
	private boolean testsInitialized;
	
	/*
	private WeatherRequest weatherRequestWithI
	nputFile = new WeatherRequest(Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\WeatherRequestTesting\\input.txt\\"), "metric"){
		@Override
		public void WriteWeatherReportsInfoToFiles(Path outputFileLocation, boolean appendFile){
			for(String cityName: cityNamesList) {
				String outputContent = cityName + " test!";
				FileUtility fileUtility = new FileUtility();
				String outputFileName = cityName + ".txt";
				fileUtility.writeFile(outputFileLocation, outputFileName, outputContent, appendFile);
			}
		}
	};*/

	@Before
	public void setUpTests() {
		if (testsInitialized != true) {
			weatherService = new WeatherService("Tallinn", "1a8a3563bee4967e64490dbfadf83b7e", "metric");
			weatherService.updateWeather(1);
			Mockito.mock();
		}
	}
	
	@Test
	public void testWritingWeatherReportInfoToFile() {
		Path outputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\WeatherRequestTesting\\");
		boolean appendFile = false;
		//weatherRequestWithInputFile.WriteWeatherReportsInfoToFiles(outputPath, appendFile);
		//List<String> cityNamesList = weatherRequestWithInputFile.getCityNamesList();
		
		weatherService.WriteWeatherReportsInfoToFiles(outputPath, appendFile);
		List<String> cityNamesList = weatherService.getCityNamesList();
		
		List<String> fileNames = new ArrayList<String>();
		for (String cityName: cityNamesList) {
			fileNames.add(cityName + ".txt");
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
		//Mockito.when(weatherService.getCurrentTemperature()).thenReturn(1);
		//Mockito.when(weatherService.getUnits()).thenReturn("metric");
		
		int temperature = weatherService.getCurrentTemperature();
		String units = weatherService.getUnits();
		try {
			Validator.validateTemperature(temperature, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
		
		//Mockito.verify(weatherService, Mockito.times(1)).getCurrentTemperature();
		//Mockito.verify(weatherService, Mockito.times(1)).getUnits();
	}
	
	@Test
	public void testIfForecastTemperatureForSingleDayIsValid() {
		int dayNumber = 1;
		
		//Mockito.when(weatherService.getForecastTemperatureForDay(dayNumber)).thenReturn(1);
		//Mockito.when(weatherService.getUnits()).thenReturn("metric");
		
		int temp = weatherService.getForecastTemperatureForDay(dayNumber);
		String units = weatherService.getUnits();
		try {
			Validator.validateTemperature(temp, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
		
		//Mockito.verify(weatherService, Mockito.times(1)).getForecastTemperatureForDay(dayNumber);
		//Mockito.verify(weatherService, Mockito.times(1)).getUnits();
	}
	
	@Test
	public void testIfForecastMinTemperatureForSingleDayIsValid() {
		int dayNumber = 1;
		
		//Mockito.when(weatherService.getForecastTemperatureForDay(dayNumber)).thenReturn(1);
		//Mockito.when(weatherService.getUnits()).thenReturn("metric");
		
		int minTemp = weatherService.getForecastMinTemperatureForDay(dayNumber);
		String units = weatherService.getUnits();
		try {
			Validator.validateTemperature(minTemp, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
		
		//Mockito.verify(weatherService, Mockito.times(1)).getForecastMinTemperatureForDay(dayNumber);
		//Mockito.verify(weatherService, Mockito.times(1)).getUnits();
	}
	
	@Test
	public void testIfForecastMaxTemperatureForSingleDayIsValid() {
		int dayNumber = 1;
		
		//Mockito.when(weatherService.getForecastTemperatureForDay(dayNumber)).thenReturn(1);
		//Mockito.when(weatherService.getUnits()).thenReturn("metric");
		
		int maxTemp = weatherService.getForecastMaxTemperatureForDay(dayNumber);
		String units = weatherService.getUnits();
		try {
			Validator.validateTemperature(maxTemp, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
		
		//Mockito.verify(weatherService, Mockito.times(1)).getForecastMaxTemperatureForDay(dayNumber);
		//Mockito.verify(weatherService, Mockito.times(1)).getUnits();
	}
	
	@Test
	public void testSettingNewCityName() {
		//Mockito.when(weatherService.getCityName()).thenReturn("Tallinn");
		
		String newCityName = "Tallinn";
		weatherService.setCityName(newCityName);
		String resultCityName = weatherService.getCityName();
		assertEquals(newCityName, resultCityName);
		
		//Mockito.verify(weatherService, Mockito.times(1)).getCityName();
	}
	
	@Test
	public void testChangingUnitsToMetric() {
		//Mockito.when(weatherService.getUnits()).thenReturn("metric");
		
		String newUnit = "metric";
		weatherService.changeUnits(newUnit);
		String resultUnit = weatherService.getUnits();
		assertEquals(newUnit, resultUnit);
		
		//Mockito.verify(weatherService, Mockito.times(1)).getUnits();
	}
	
	@Test
	public void testChangingUnitsToImperial() {
		//Mockito.when(weatherService.getUnits()).thenReturn("imperial");
		
		String newUnit = "imperial";
		weatherService.changeUnits(newUnit);
		String resultUnit = weatherService.getUnits();
		assertEquals(newUnit, resultUnit);
		
		//Mockito.verify(weatherService, Mockito.times(1)).getUnits();
	}
	
	@Test
	public void testChangingUnitsToKelvin() {
		//Mockito.when(weatherService.getUnits()).thenReturn("kelvin");
		
		String newUnit = "kelvin"; 
		weatherService.changeUnits(newUnit);
		String resultUnit = weatherService.getUnits();
		assertEquals(newUnit, resultUnit);
		
		//Mockito.verify(weatherService, Mockito.times(1)).getUnits();
	}
	
	@Test
	public void testForEveryCityInInputAReportIsWrittenToFile(){
	}
	
}
