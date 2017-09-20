package weather;

import java.io.IOException;

public class RunCode {

	public static void main(String[] args) throws IOException{
		System.out.println("Internet connection exists: " + WeatherForecast.internetConnectionExists());
		System.out.println(WeatherForecast.getWeatherInfoJson("http://api.apixu.com/v1/current.json?key=14f133a36340433dbd6133833171309&q=", "London"));
	}
}
