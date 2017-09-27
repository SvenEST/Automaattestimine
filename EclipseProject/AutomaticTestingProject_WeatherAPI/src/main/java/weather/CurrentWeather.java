package weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;

public class CurrentWeather {
	
	public static JSONObject getWeatherInfoJson(String apiUrl, String apiKey, String city, String units) throws IOException{
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
	
	public static int getMaxTemperature() {
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	public static int getMinTemperature() {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	public static String getCoordinates() {
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
}
