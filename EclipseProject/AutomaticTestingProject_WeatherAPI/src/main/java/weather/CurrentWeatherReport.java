package weather;

import org.json.JSONObject;

public class CurrentWeatherReport {

	private final JSONObject weatherInfo;
	private final CurrentWeatherParser currentWeatherParser;

	public CurrentWeatherReport(JSONObject weatherInfo) {
		this.weatherInfo = weatherInfo;
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

	public CurrentWeatherParser getCurrentWeatherParser() {
		return currentWeatherParser;
	}
}
