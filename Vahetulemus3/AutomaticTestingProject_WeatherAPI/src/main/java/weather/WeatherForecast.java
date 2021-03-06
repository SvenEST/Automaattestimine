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
		JSONObject weatherForecastInfo = null;
		try {
			URL url = new URL(apiUrl + "?q=" + city + "&units=" + units + "&appid=" + apiKey);
			URLConnection urlCon = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
	        String result = reader.readLine();
	        reader.close();
			try {
				weatherForecastInfo = new JSONObject(result);
				return weatherForecastInfo;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return weatherForecastInfo;
	}
	
	public JSONObject getForecastForSingleDay(JSONObject weatherForecastInfo, int dayNumber){
		JSONArray forecastPerDayList;
		JSONObject singleDayForecastInfo = null;
		try {
			forecastPerDayList = weatherForecastInfo.getJSONArray("list");
			singleDayForecastInfo = forecastPerDayList.getJSONObject(dayNumber);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return singleDayForecastInfo;
	}
	
	public int getTemperature(JSONObject singleDayForecastInfo) {
		try {
			JSONObject main = singleDayForecastInfo.getJSONObject("main");
			int temp = main.getInt("temp");
			return temp;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getMinTemperature(JSONObject singleDayForecastInfo) {
		try {
			JSONObject main = singleDayForecastInfo.getJSONObject("main");
			int minTemp = main.getInt("temp_min");
			return minTemp;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getMaxTemperature(JSONObject singleDayForecastInfo) {
		try {
			JSONObject main = singleDayForecastInfo.getJSONObject("main");
			int maxTemp = main.getInt("temp_max");
			return maxTemp;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public String getCoordinates(JSONObject singleDayForecastInfo) {
		int lon = 0;
		int lat = 0;
		try {
			JSONObject cityInfo = singleDayForecastInfo.getJSONObject("city");
			JSONObject coord = cityInfo.getJSONObject("coord");
			lon = coord.getInt("lon");
			lat = coord.getInt("lat");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String geoCcoord = Integer.toString(lat) + ":" + Integer.toString(lon);
		return geoCcoord;
	}
	
	public String getCityName(JSONObject singleDayForecastInfo) {
		String cityName = null;
		try {
			JSONObject cityInfo = singleDayForecastInfo.getJSONObject("city");
			cityName = cityInfo.getString("name");
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
