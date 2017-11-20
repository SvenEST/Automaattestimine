package weather;

import java.io.IOException;

import org.json.JSONObject;

import connection.ConnectionUtility;

public class CurrentWeatherReportFactory {

	private String units = "metric";
	private String apiKey;
	
	public CurrentWeatherReportFactory(String cityName, String apiKey, String units) {
		this.apiKey = apiKey;
		changeUnits(units);
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
