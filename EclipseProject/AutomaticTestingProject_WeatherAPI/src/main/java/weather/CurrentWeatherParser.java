package weather;

import org.json.JSONException;
import org.json.JSONObject;

public class CurrentWeatherParser{
	
	private JSONObject weatherInfo;
	
	public CurrentWeatherParser(JSONObject weatherInfo) {
		this.weatherInfo = weatherInfo;
	}

	public int getTemperature() {
		try {
			JSONObject mainObject = weatherInfo.getJSONObject("main");
			int temperature = mainObject.getInt("temp");
			return temperature;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int getMinTemperature() {
		try {
			JSONObject mainObject = weatherInfo.getJSONObject("main");
			int minTemperature = mainObject.getInt("temp_min");
			return minTemperature;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getMaxTemperature() {
		try {
			JSONObject mainObject = weatherInfo.getJSONObject("main");
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
			JSONObject coordinatesObject = weatherInfo.getJSONObject("coord");
			lon = coordinatesObject.getInt("lon");
			lat = coordinatesObject.getInt("lat");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String geoCoords = Integer.toString(lat) + ":" + Integer.toString(lon);
		return geoCoords;
	}
	
	public String getCityName() {
		String cityName = null;
		try {
			cityName = weatherInfo.getString("name");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return cityName;
	}

	public JSONObject getWeatherInfo() {
		return weatherInfo;
	}
}
