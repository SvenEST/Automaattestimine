package weather;

import java.io.IOException;

import org.json.JSONObject;

import connection.ConnectionUtility;

public class CurrentWeatherReport {

	private String units = "metric";
	private String apiKey;
	CurrentWeatherParser currentWeatherParser;

	public CurrentWeatherReport(String cityName, String apiKey, String units) {
		setApiKey(apiKey);
		changeUnits(units);
		JSONObject weatherInfo = getCurrentWeatherInfoFromApi(cityName);
		currentWeatherParser = new CurrentWeatherParser(weatherInfo);
	}
	
	public JSONObject getCurrentWeatherInfoFromApi(String cityName) {
		String apiUrl = "http://api.openweathermap.org/data/2.5/weather";
		String inputFullUrl = apiUrl + "?q=" + cityName + "&units=" + units + "&appid=" + apiKey;
		ConnectionUtility connection = new ConnectionUtility(inputFullUrl);
		JSONObject weatherInfo = null;
		try {
			weatherInfo = connection.readJsonFromUrl();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return weatherInfo;
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
	
	public void changeUnits(String newUnit) {
		if(newUnit.equalsIgnoreCase("metric") || newUnit.equalsIgnoreCase("imperial") || newUnit.equalsIgnoreCase("Kelvin")) {
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
}
