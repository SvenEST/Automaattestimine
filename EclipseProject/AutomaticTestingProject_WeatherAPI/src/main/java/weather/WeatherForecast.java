package weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WeatherForecast {

	static String weatherInfo;
	private static URLConnection urlCon;
	
	public static boolean openApiConnection(String url) throws IOException {
		/*
		URL apiUrl = new URL(url);
        urlCon = apiUrl.openConnection();
        if (!(urlCon.toString().isEmpty())) {
        	return true;
        }*/
        return false;
	}
	
	public static String getWeatherInfo(String url, String city) throws IOException{
		/*
		if(openApiConnection(url + city) == true) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
	        String inputLine;
	        while ((inputLine = reader.readLine()) != null) 
	            weatherInfo += inputLine;
	        reader.close();
	        return weatherInfo;
		};
		*/
		return "Connection error!";
	}

	public static int getCurrentTemperature() {
		return 0; //not yet implemented
	}

	public static int getMaxTemperature() {
		return 0; //not yet implemented
	}

	public static int getMinTemperature() {
		return 0; //not yet implemented
	}

	public static String getCoordinates() {
		return "Not yet implemented";
	}
	
}
