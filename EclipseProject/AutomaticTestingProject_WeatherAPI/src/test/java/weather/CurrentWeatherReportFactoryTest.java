package weather;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CurrentWeatherReportFactoryTest {

	private CurrentWeatherReportFactory currentWeatherReportFactory;
	
	@Before
	public void setUpTests() {
		currentWeatherReportFactory = new CurrentWeatherReportFactory("Tallinn", "1a8a3563bee4967e64490dbfadf83b7e", "metric");
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
