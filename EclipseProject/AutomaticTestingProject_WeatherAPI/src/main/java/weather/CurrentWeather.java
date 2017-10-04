package weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;

public class CurrentWeather implements WeatherReport {
	
	private static String units;
	private static String apiKey;
	private static String apiUrl;
	
	public static JSONObject getWeatherInfoJson(String city) throws IOException{
		JSONObject weatherInfoJson = null;
		try {
			URL url = new URL(apiUrl + city + "&units=" + units + "&appid=" + apiKey);
			URLConnection urlCon = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
	        String result = reader.readLine();
	        reader.close();
			try {
				weatherInfoJson = new JSONObject(result);
				return weatherInfoJson;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return weatherInfoJson;
	}

	public static int getTemperature(JSONObject weatherInfoJson) {
		try {
			JSONObject main = weatherInfoJson.getJSONObject("main");
			int currentTemp = main.getInt("temp");
			return currentTemp;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int getMinTemperature() {
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	public static int getMaxTemperature() {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	public static String getCoordinates() {
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	public static String getCityName(JSONObject weatherInfo) {
		String city = null;
		try {
			city = weatherInfo.getString("name");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return city;
	}
	
	public static String changeUnit(String newUnit) {
		if(newUnit == "Metric" || newUnit == "Imperial" || newUnit == "Kelvin") {
			units = newUnit;
			return newUnit;
		} else {
			return "Unit change failed. Use Metric, Imperial or Kelvin";
		}
	}

	public static String getUnits() {
		return units;
	}
	
	public static void setApiUrl(String apiUrl) {
		CurrentWeather.apiUrl = apiUrl;
	}

	public static String getApiUrl() {
		return apiUrl;
	}
	
	public static void setApiKey(String apiKey) {
		CurrentWeather.apiKey = apiKey;
	}

	public static String getApiKey() {
		return apiKey;
	}
	
}
