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
	
	private WeatherRequest weatherRequest;
	
	private WeatherRequest weatherRequestWithInputFile = new WeatherRequest(Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\WeatherRequestTesting\\input.txt\\"), "metric"){
		@Override
		public void WriteWeatherReportsInfoToFiles(Path outputFileLocation, boolean appendFile){
			for(String cityName: cityNamesList) {
				String outputContent = cityName + " test!";
				FileUtility fileUtility = new FileUtility();
				String outputFileName = cityName + ".txt";
				fileUtility.writeFile(outputFileLocation, outputFileName, outputContent, appendFile);
			}
		}
	};

	@Before
	public void setUpTests() {
		weatherRequest = Mockito.mock(WeatherRequest.class);
	}
	
	@Test
	public void testWritingWeatherReportInfoToFile() {
		Path outputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\WeatherRequestTesting\\");
		boolean appendFile = false;
		weatherRequestWithInputFile.WriteWeatherReportsInfoToFiles(outputPath, appendFile);
		
		List<String> cityNamesList = weatherRequestWithInputFile.getCityNamesList();
		
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
		Mockito.when(weatherRequest.getCurrentTemperature()).thenReturn(1);
		Mockito.when(weatherRequest.getUnits()).thenReturn("metric");
		
		int temperature = weatherRequest.getCurrentTemperature();
		String units = weatherRequest.getUnits();
		try {
			Validator.validateTemperature(temperature, units);
		} catch (Exception e) {
			fail("Failure cause: " + e.getMessage());
		}
		
		Mockito.verify(weatherRequest, Mockito.times(1)).getCurrentTemperature();
		Mockito.verify(weatherRequest, Mockito.times(1)).getUnits();
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
		
		Mockito.verify(weatherRequest, Mockito.times(1)).getForecastTemperatureForDay(dayNumber);
		Mockito.verify(weatherRequest, Mockito.times(1)).getUnits();
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
		
		Mockito.verify(weatherRequest, Mockito.times(1)).getForecastMinTemperatureForDay(dayNumber);
		Mockito.verify(weatherRequest, Mockito.times(1)).getUnits();
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
		
		Mockito.verify(weatherRequest, Mockito.times(1)).getForecastMaxTemperatureForDay(dayNumber);
		Mockito.verify(weatherRequest, Mockito.times(1)).getUnits();
	}
	
	@Test
	public void testSettingNewCityName() {
		Mockito.when(weatherRequest.getCityName()).thenReturn("Tallinn");
		
		String newCityName = "Tallinn";
		weatherRequest.setCityName(newCityName);
		String resultCityName = weatherRequest.getCityName();
		assertEquals(newCityName, resultCityName);
		
		Mockito.verify(weatherRequest, Mockito.times(1)).getCityName();
	}
	
	@Test
	public void testChangingUnitsToMetric() {
		Mockito.when(weatherRequest.getUnits()).thenReturn("metric");
		
		String newUnit = "metric";
		weatherRequest.changeUnits(newUnit);
		String resultUnit = weatherRequest.getUnits();
		assertEquals(newUnit, resultUnit);
		
		Mockito.verify(weatherRequest, Mockito.times(1)).getUnits();
	}
	
	@Test
	public void testChangingUnitsToImperial() {
		Mockito.when(weatherRequest.getUnits()).thenReturn("imperial");
		
		String newUnit = "imperial";
		weatherRequest.changeUnits(newUnit);
		String resultUnit = weatherRequest.getUnits();
		assertEquals(newUnit, resultUnit);
		
		Mockito.verify(weatherRequest, Mockito.times(1)).getUnits();
	}
	
	@Test
	public void testChangingUnitsToKelvin() {
		Mockito.when(weatherRequest.getUnits()).thenReturn("kelvin");
		
		String newUnit = "kelvin"; 
		weatherRequest.changeUnits(newUnit);
		String resultUnit = weatherRequest.getUnits();
		assertEquals(newUnit, resultUnit);
		
		Mockito.verify(weatherRequest, Mockito.times(1)).getUnits();
	}
	
	@Test
	public void testForEveryCityInInputAReportIsWrittenToFile(){
	}
	
}
