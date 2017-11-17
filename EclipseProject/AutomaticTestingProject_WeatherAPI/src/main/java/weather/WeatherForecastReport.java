package weather;

import java.io.IOException;

import org.json.JSONObject;

import connection.ConnectionUtility;

public class WeatherForecastReport {

	private String units = "metric";
	private String apiKey;
	private WeatherForecastParser weatherForecastParser;
	private JSONObject forecastInfo;
	
	public WeatherForecastReport(String cityName, String apiKey, String units) {
		setApiKey(apiKey);
		changeUnits(units);
		forecastInfo = getWeatherForecastInfoFromApi(cityName);
		weatherForecastParser = new WeatherForecastParser(forecastInfo);
	}
	
	public WeatherForecastReport(String cityName, String apiKey, String units, int dayNumber) {
		setApiKey(apiKey);
		changeUnits(units);
		forecastInfo = getWeatherForecastInfoFromApi(cityName);
		weatherForecastParser = new WeatherForecastParser(forecastInfo, dayNumber);
	}

	public JSONObject getWeatherForecastInfoFromApi(String city){
		String apiUrl = "http://api.openweathermap.org/data/2.5/forecast";
		String url = apiUrl + "?q=" + city + "&units=" + units + "&appid=" + apiKey;
		ConnectionUtility connection = new ConnectionUtility(url);
		JSONObject forecastInfo = null;
		try {
			forecastInfo = connection.readJsonFromUrl();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return forecastInfo;
	}
	
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
	
	public void changeUnits(String newUnit) {
		if(newUnit.equalsIgnoreCase("Metric") || newUnit.equalsIgnoreCase("Imperial") || newUnit.equalsIgnoreCase("Kelvin")) {
			this.units = newUnit;
		}
	}
	
	public String getUnits() {
		return units;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiKey() {
		return apiKey;
	}

	public JSONObject getForecastInfo() {
		return forecastInfo;
	}
}
