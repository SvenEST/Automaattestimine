package weather;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import file.FileReader;
import testhelpers.Validator;

public class WeatherRequestTest {
	
	private static WeatherRequest weatherRequest;

	@Before
	public void setUpTests() {
		weatherRequest = new WeatherRequest("Tallinn", "metric");
	}
	
	@Test
	public void testWriteWeatherReportInfoToFile() {
		Path outputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\Sisendfailid\\");
		boolean appendFile = false;
		weatherRequest.WriteWeatherReportInfoToFile(outputPath, appendFile);
		
		/*
		FileReader fileReader = new FileReader();
		String recievedOutputContent = null;
		try {
			recievedOutputContent = fileReader.readFile(outputPath);           //Ainult path, Faili nimi puudu
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertFalse(recievedOutputContent.isEmpty());*/
	}

	@Test
	public void testIfResponseCurrentTemperatureIsValid() {
		int temp = weatherRequest.getCurrentTemperature();
		String units = weatherRequest.getUnits();
		try {
			Validator.validateTemperature(temp, units);
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
