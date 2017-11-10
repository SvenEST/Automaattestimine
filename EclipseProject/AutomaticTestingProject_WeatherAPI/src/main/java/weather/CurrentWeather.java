package weather;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import connection.ConnectionUtility;

public class CurrentWeather implements WeatherReport {
	
	private String units = "Metric";
	private String apiKey;
	private String apiUrl;
	
	public CurrentWeather(String apiKey, String units) {
		setApiKey(apiKey);
		setApiUrl("http://api.openweathermap.org/data/2.5/weather");
		changeUnits(units);
	}
	
	public JSONObject getWeatherInfo(String city) {
		String url = apiUrl + "?q=" + city + "&units=" + units + "&appid=" + apiKey;
		ConnectionUtility connection = new ConnectionUtility(url);
		JSONObject weatherInfoJson = null;
		try {
			weatherInfoJson = connection.readJsonFromUrl();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return weatherInfoJson;
	}

	public int getTemperature(JSONObject weatherInfoJson) {
		try {
			JSONObject main = weatherInfoJson.getJSONObject("main");
			int currentTemp = main.getInt("temp");
			return currentTemp;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getMinTemperature(JSONObject weatherInfoJson) {
		try {
			JSONObject main = weatherInfoJson.getJSONObject("main");
			int minTemp = main.getInt("temp_min");
			return minTemp;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getMaxTemperature(JSONObject weatherInfoJson) {
		try {
			JSONObject main = weatherInfoJson.getJSONObject("main");
			int maxTemp = main.getInt("temp_max");
			return maxTemp;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public String getCoordinates(JSONObject weatherInfoJson) {
		int lon = 0;
		int lat = 0;
		try {
			JSONObject coord = weatherInfoJson.getJSONObject("coord");
			lon = coord.getInt("lon");
			lat = coord.getInt("lat");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String geoCoord = Integer.toString(lat) + ":" + Integer.toString(lon);
		return geoCoord;
	}
	
	public String getCityName(JSONObject weatherInfo) {
		String cityName = null;
		try {
			cityName = weatherInfo.getString("name");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return cityName;
	}
	
	public void changeUnits(String newUnit) {
		if(newUnit.equalsIgnoreCase("Metric") || newUnit.equalsIgnoreCase("Imperial") || newUnit.equalsIgnoreCase("Kelvin")) {
			this.units = newUnit;
		}
	}

	public String getUnits() {
		return units;
	}
	
	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getApiUrl() {
		return apiUrl;
	}
	
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiKey() {
		return apiKey;
	}
	
}
