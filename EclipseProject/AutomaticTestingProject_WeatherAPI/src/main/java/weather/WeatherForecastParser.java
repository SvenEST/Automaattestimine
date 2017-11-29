package weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherForecastParser{
	
	private JSONObject forecastInfo;
	private JSONObject singleDayForecastInfo;
	
	public WeatherForecastParser(JSONObject forecastInfo) {
		this.forecastInfo = forecastInfo;
	}
	
	public WeatherForecastParser(JSONObject forecastInfo, int dayNumber) {
		this.forecastInfo = forecastInfo;
		singleDayForecastInfo = getForecastForSingleDay(forecastInfo, dayNumber);
	}
	
	private JSONObject getForecastForSingleDay(JSONObject weatherForecastInfo, int dayNumber){
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
	
	public int getTemperature() {
		try {
			JSONObject mainObject = singleDayForecastInfo.getJSONObject("main");
			int temperature = mainObject.getInt("temp");
			return temperature;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getMinTemperature() {
		try {
			JSONObject mainObject = singleDayForecastInfo.getJSONObject("main");
			int minTemperature = mainObject.getInt("temp_min");
			return minTemperature;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getMaxTemperature() {
		try {
			JSONObject mainObject = singleDayForecastInfo.getJSONObject("main");
			int maxTemperature = mainObject.getInt("temp_max");
			return maxTemperature;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public String getGeoCoordinates() {
		int lon = 0;
		int lat = 0;
		try {
			JSONObject cityInfo = forecastInfo.getJSONObject("city");
			JSONObject coordinatesObject = cityInfo.getJSONObject("coord");
			lon = coordinatesObject.getInt("lon");
			lat = coordinatesObject.getInt("lat");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String geoCcoords = Integer.toString(lat) + ":" + Integer.toString(lon);
		return geoCcoords;
	}
	
	public String getCityName() {
		String cityName = null;
		try {
			JSONObject cityInfo = forecastInfo.getJSONObject("city");
			cityName = cityInfo.getString("name");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return cityName;
	}

	public JSONObject getForecastInfo() {
		return forecastInfo;
	}

	public JSONObject getSingleDayForecastInfo() {
		return singleDayForecastInfo;
	}
}
