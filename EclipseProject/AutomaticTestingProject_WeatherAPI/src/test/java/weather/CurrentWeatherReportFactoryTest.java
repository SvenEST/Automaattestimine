package weather;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import file.FileUtility;
import testhelpers.Validator;

public class CurrentWeatherReportFactoryTest {

	final CurrentWeatherReportFactory currentWeatherReportFactory = new CurrentWeatherReportFactory("Tallinn", "1a8a3563bee4967e64490dbfadf83b7e", "metric") {
		@Override
		public JSONObject getCurrentWeatherInfoFromApi(String cityName) {
			Path inputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\CurrentWeatherReportFactoryTesting\\CurrentWeatherInfo.txt");
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

	@Test
	public void testIfReturnedWeatherInfoIsInJsonFormat(){
		JSONObject weatherInfo = currentWeatherReportFactory.getCurrentWeatherInfoFromApi("Tallinn");
		Validator.validateJsonFormat(weatherInfo);
	}
	
	@Test
	public void testChangingUnitsToMetric() {
		String newUnit = "Metric";
		currentWeatherReportFactory.changeUnits(newUnit);
		String resultUnit = currentWeatherReportFactory.getUnits();
		assertEquals(newUnit, resultUnit);
	}
	
	@Test
	public void testChangingUnitsToImperial() {
		String newUnit = "Imperial";
		currentWeatherReportFactory.changeUnits(newUnit);
		String resultUnit = currentWeatherReportFactory.getUnits();
		assertEquals(newUnit, resultUnit);
	}
	
	@Test
	public void testChangingUnitsToKelvin() {
		String newUnit = "Kelvin"; 
		currentWeatherReportFactory.changeUnits(newUnit);
		String resultUnit = currentWeatherReportFactory.getUnits();
		assertEquals(newUnit, resultUnit);
	}
	
	@Test
	public void testSettingNewApiKey() {
		String newKey = "1a8a3563bee4967e64490dbfadf83b7e";
		currentWeatherReportFactory.setApiKey(newKey);
		String resultKey = currentWeatherReportFactory.getApiKey();
		assertEquals(newKey, resultKey);
	}
}
