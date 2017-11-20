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

public class WeatherForecastReportFactoryTest {

	final WeatherForecastReportFactory weatherForecastReportFactory = new WeatherForecastReportFactory("Tallinn", "1a8a3563bee4967e64490dbfadf83b7e", "metric") {
		@Override
		public JSONObject getWeatherForecastInfoFromApi(String city){
			Path inputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\WeatherForecastReportTesting\\WeatherForecastInfo.txt");
			FileUtility fileUtility = new FileUtility();
			JSONObject weatherForecastInfoFromFile = null;
			try {
				weatherForecastInfoFromFile = new JSONObject(fileUtility.readFile(inputPath));
			} catch (JSONException e) {
				fail("Failure cause: " + e.getMessage());
			}
			return weatherForecastInfoFromFile;
		}
	};
	
	@Test
	public void testIfReturnedWeatherForecastInfoIsInJsonFormat() {
		JSONObject forecastInfo = weatherForecastReportFactory.getWeatherForecastInfoFromApi("Tallinn");
		Validator.validateJsonFormat(forecastInfo);
	}
	
	@Test
	public void testChangingUnitsToMetric() {
		String newUnit = "Metric";
		weatherForecastReportFactory.changeUnits(newUnit);
		String resultUnit = weatherForecastReportFactory.getUnits();
		assertEquals(newUnit, resultUnit);	
	}
	
	@Test
	public void testChangingUnitsToImperial() {
		String newUnit = "Imperial";
		weatherForecastReportFactory.changeUnits(newUnit);
		String resultUnit = weatherForecastReportFactory.getUnits();
		assertEquals(newUnit, resultUnit);
	}
	
	@Test
	public void testChangingUnitsToKelvin() {
		String newUnit = "Kelvin";
		weatherForecastReportFactory.changeUnits(newUnit);
		String resultUnit = weatherForecastReportFactory.getUnits();
		assertEquals(newUnit, resultUnit);
	}
	
	@Test
	public void testSettingNewApiKey() {
		String newKey = "1a8a3563bee4967e64490dbfadf83b7e";
		weatherForecastReportFactory.setApiKey(newKey);
		String resultKey = weatherForecastReportFactory.getApiKey();
		assertEquals(newKey, resultKey);
	}
}
