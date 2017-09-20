package weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WeatherForecast {

	private static String weatherInfoJson;
	
	public static boolean internetConnectionExists() {
	    /*try {
	        final URL url = new URL("http://www.google.com");
	        final URLConnection connection = url.openConnection();
	        connection.connect();
	        return true;
	    } catch (MalformedURLException e) {
	        throw new RuntimeException(e);
	        return false;
	    } catch (IOException e) {
	        return false;
	    }
		return false;*/
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	public static String getWeatherInfoJson(String apiUrl, String city) throws IOException{
		/*
		URL url = new URL(apiUrl + city);
        URLconnection urlCon = url.openConnection();
		BufferedReader reader = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
        String inputLine;
        while ((inputLine = reader.readLine()) != null) 
            weatherInfoJson += inputLine;
        reader.close();
        return weatherInfoJson;*/
		throw new UnsupportedOperationException("Not yet implemented");
	}

	public static int getCurrentTemperatureCelsius() {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	public static int getCurrentTemperatureFahrenheit() {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	public static String getThreeDayForecast(int day) {
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	public static int getMaxTemperatureCelsius() {
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	public static int getMaxTemperatureFahrenheit() {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	public static int getMinTemperatureCelsius() {
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	public static int getMinTemperatureFahrenheit() {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	public static String getCoordinates() {
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
}
