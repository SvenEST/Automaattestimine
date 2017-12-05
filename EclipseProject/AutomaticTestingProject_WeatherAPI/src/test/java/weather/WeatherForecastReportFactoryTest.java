package weather;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class WeatherForecastReportFactoryTest {

	private WeatherForecastReportFactory weatherForecastReportFactory;
	
	@Before
	public void setUpTests() {
		weatherForecastReportFactory = new WeatherForecastReportFactory("Tallinn", "1a8a3563bee4967e64490dbfadf83b7e", "metric");
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
