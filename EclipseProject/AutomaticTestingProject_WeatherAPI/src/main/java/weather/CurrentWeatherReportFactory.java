package weather;

import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

import connection.ConnectionUtility;

public class CurrentWeatherReportFactory {

	private String units;
	private String apiKey;
	
	public CurrentWeatherReportFactory(String cityName, String apiKey, String units) {
		this.apiKey = apiKey;
		changeUnits(units);
	}

	public JSONObject getCurrentWeatherInfoFromApi(String cityName) {
		String apiUrl = "http://api.openweathermap.org/data/2.5/weather";
		JSONObject weatherInfo = null;
		try {
			URL inputFullUrl = new URL(apiUrl + "?q=" + cityName + "&units=" + units + "&appid=" + apiKey);
			ConnectionUtility connection = new ConnectionUtility(inputFullUrl);
			weatherInfo = connection.readJsonFromUrl();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return weatherInfo;
	}
	
	public void changeUnits(String newUnit) {
		if(newUnit.equalsIgnoreCase("metric") || newUnit.equalsIgnoreCase("imperial") || newUnit.equalsIgnoreCase("Kelvin")) {
			this.units = newUnit;
		} else {
			this.units = "metric";
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
