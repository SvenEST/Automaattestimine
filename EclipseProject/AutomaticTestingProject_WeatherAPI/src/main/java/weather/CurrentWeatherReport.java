package weather;

import org.json.JSONObject;

public class CurrentWeatherReport {

	private final JSONObject weatherInfo;
	private final CurrentWeatherReportFactory currentWeatherReportFactory;
	private final CurrentWeatherParser currentWeatherParser;

	public CurrentWeatherReport(String cityName, String apiKey, String units) {
		currentWeatherReportFactory = new CurrentWeatherReportFactory(cityName, apiKey, units);
		weatherInfo = currentWeatherReportFactory.getCurrentWeatherInfoFromApi(cityName);
		currentWeatherParser = new CurrentWeatherParser(weatherInfo);
	}

	public int getTemperature() {
		return currentWeatherParser.getTemperature();
	}
	
	public int getMinTemperature() {
		return currentWeatherParser.getMinTemperature();
	}
	
	public int getMaxTemperature() {
		return currentWeatherParser.getMaxTemperature();
	}
	
	public String getGeoCoordinates() {
		return currentWeatherParser.getGeoCoordinates();
	}
	
	public String getCityName() {
		return currentWeatherParser.getCityName();
	}

	public JSONObject getWeatherInfo() {
		return weatherInfo;
	}
	
	public String getUnits() {
		String units = currentWeatherReportFactory.getUnits();
		return units;
	}
}
