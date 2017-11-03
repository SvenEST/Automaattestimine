package weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.*;

public class WeatherForecast implements WeatherReport{
	
	private String units = "Metric";
	private String apiKey;
	private String apiUrl;

	public JSONObject getWeatherForecastInfo(String city) throws IOException{
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
	
	public JSONObject getForecastForSingleDay(JSONObject forecastInfo, int day){
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
	
	public int getTemperature(JSONObject forecastForDay) {
		try {
			JSONObject main = forecastForDay.getJSONObject("main");
			int temp = main.getInt("temp");
			return temp;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getMinTemperature(JSONObject forecastForDay) {
		try {
			JSONObject main = forecastForDay.getJSONObject("main");
			int minTemp = main.getInt("temp_min");
			return minTemp;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getMaxTemperature(JSONObject forecastForDay) {
		try {
			JSONObject main = forecastForDay.getJSONObject("main");
			int maxTemp = main.getInt("temp_max");
			return maxTemp;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public String getCoordinates(JSONObject forecastInfo) {
		int lon = 0;
		int lat = 0;
		try {
			JSONObject city = forecastInfo.getJSONObject("city");
			JSONObject coord = city.getJSONObject("coord");
			lon = coord.getInt("lon");
			lat = coord.getInt("lat");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String GEOcoord = Integer.toString(lat) + ":" + Integer.toString(lon);
		return GEOcoord;
	}
	
	public String getCityName(JSONObject weatherInfo) {
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
	
	public String changeUnits(String newUnit) {
		if(newUnit.equalsIgnoreCase("Metric") || newUnit.equalsIgnoreCase("Imperial") || newUnit.equalsIgnoreCase("Kelvin")) {
			this.units = newUnit;
			return newUnit;
		} else {
			return "Unit change failed. Use Metric, Imperial or Kelvin";
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
