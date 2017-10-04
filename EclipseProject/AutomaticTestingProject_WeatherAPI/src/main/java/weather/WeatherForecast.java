package weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.*;

public class WeatherForecast implements WeatherReport{
	
	private static String units;
	private static String apiKey;
	private static String apiUrl;

	public static JSONObject getWeatherForecastInfo(String city) throws IOException{
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
	
	public static JSONObject getForecastForDay(JSONObject forecastInfo, int day){
		JSONArray list;
		JSONObject forecast = null;
		try {
			list = forecastInfo.getJSONArray("list");
			forecast = list.getJSONObject(day);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return forecast;
	}
	
	public static int getTemperature(JSONObject forecastForDay) {
		try {
			JSONObject main = forecastForDay.getJSONObject("main");
			int temp = main.getInt("temp");
			return temp;
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
		JSONObject cityInfo;
		String cityName = null;
		try {
			cityInfo = weatherInfo.getJSONObject("city");
			cityName = cityInfo.getString("name");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return cityName;
	}
	
	public static String changeUnit(String newUnit) {
		if(newUnit == "Metric" || newUnit == "Imperial" || newUnit == "Kelvin") {
			units = newUnit;
			return "Unit changed to: " + newUnit;
		} else {
			return "Unit change failed. Use Metric, Imperial or Kelvin";
		}
	}

	public static String getUnits() {
		return units;
	}
	
	public static void setApiUrl(String apiUrl) {
		WeatherForecast.apiUrl = apiUrl;
	}

	public static String getApiUrl() {
		return apiUrl;
	}
	
	public static void setApiKey(String apiKey) {
		WeatherForecast.apiKey = apiKey;
	}

	public static String getApiKey() {
		return apiKey;
	}

}
