package weather;

import org.json.JSONObject;

public class WeatherForecastReport {

	private final JSONObject forecastInfo;
	private final WeatherForecastParser weatherForecastParser;
	
	public WeatherForecastReport(JSONObject forecastInfo) {
		this.forecastInfo = forecastInfo;
		weatherForecastParser = new WeatherForecastParser(forecastInfo);
	}
	
	public WeatherForecastReport(JSONObject forecastInfo, int dayNumber) {
		this.forecastInfo = forecastInfo;
		weatherForecastParser = new WeatherForecastParser(forecastInfo, dayNumber);
	}
	
	/*
	public WeatherForecastReport(String cityName, String apiKey, String units, int dayNumber) {
		weatherForecastReportFactory = new WeatherForecastReportFactory(cityName, apiKey, units);
		forecastInfo = weatherForecastReportFactory.getWeatherForecastInfoFromApi(cityName);
		weatherForecastParser = new WeatherForecastParser(forecastInfo, dayNumber);
	}*/
	
	public int getTemperature() {
		return weatherForecastParser.getTemperature();
	}
	
	public int getMinTemperature() {
		return weatherForecastParser.getMinTemperature();
	}
	
	public int getMaxTemperature() {
		return weatherForecastParser.getMaxTemperature();
	}
	
	public String getGeoCoordinates() {
		return weatherForecastParser.getGeoCoordinates();
	}
	
	public String getCityName() {
		return weatherForecastParser.getCityName();
	}

	public JSONObject getForecastInfo() {
		return forecastInfo;
	}
}
