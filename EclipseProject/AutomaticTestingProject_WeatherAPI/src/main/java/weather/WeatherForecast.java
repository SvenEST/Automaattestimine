package weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.*;

public class WeatherForecast implements WeatherReport{
	
	private static String units = "Metric";

	public static JSONObject getWeatherForecastInfo(String apiUrl, String apiKey, String city) throws IOException{
        URL url;
		JSONObject weatherInfoJson = null;
		try {
			url = new URL(apiUrl + city + "&units=" + units + "&appid=" + apiKey);
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
	
	public static int getMaxTemperature() {
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	public static int getMinTemperature() {
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	public static String getCoordinates() {
		throw new UnsupportedOperationException("Not yet implemented");
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
	
	public static String getCityName(JSONObject weatherInfo) {
		JSONObject cityInfo = null;
		String cityName = null;
		try {
			cityInfo = weatherInfo.getJSONObject("city");
			cityName = cityInfo.getString("name");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return cityName;
	}

}
