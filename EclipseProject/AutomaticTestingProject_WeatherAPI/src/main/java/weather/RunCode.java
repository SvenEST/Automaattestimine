package weather;

import java.io.IOException;

import org.json.JSONObject;

public class RunCode {

	public static void main(String[] args) throws IOException{
		System.out.println("Internet connection exists: " + Connection.internetConnectionExists());
		JSONObject weatherInfoJson = CurrentWeather.getWeatherInfoJson("http://api.openweathermap.org/data/2.5/weather?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn, ee", "Metric");
		//System.out.println("Current weather info: " + weatherInfoJson);
		System.out.println("Current temperature: " + CurrentWeather.getTemperature(weatherInfoJson));
		//System.out.println("Weather forecast info: " + WeatherForecast.getWeatherForecastInfo("http://api.openweathermap.org/data/2.5/forecast?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn,ee", "Metric"));
		//System.out.println("Weather forecast for day 1: " + WeatherForecast.getForecastForDay(WeatherForecast.getWeatherForecastInfo("http://api.openweathermap.org/data/2.5/forecast?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn,ee", "Metric"), 1));
		//System.out.println("Weather forecast for day 2: " + WeatherForecast.getForecastForDay(WeatherForecast.getWeatherForecastInfo("http://api.openweathermap.org/data/2.5/forecast?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn,ee", "Metric"), 2));
		//System.out.println("Weather forecast for day 3: " + WeatherForecast.getForecastForDay(WeatherForecast.getWeatherForecastInfo("http://api.openweathermap.org/data/2.5/forecast?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn,ee", "Metric"), 3));
		System.out.println("Forecast temperature for day 1: " + WeatherForecast.getTemperature(WeatherForecast.getForecastForDay(WeatherForecast.getWeatherForecastInfo("http://api.openweathermap.org/data/2.5/forecast?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn,ee", "Metric"), 1)));
		System.out.println("Forecast temperature for day 2: " + WeatherForecast.getTemperature(WeatherForecast.getForecastForDay(WeatherForecast.getWeatherForecastInfo("http://api.openweathermap.org/data/2.5/forecast?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn,ee", "Metric"), 2)));
		System.out.println("Forecast temperature for day 3: " + WeatherForecast.getTemperature(WeatherForecast.getForecastForDay(WeatherForecast.getWeatherForecastInfo("http://api.openweathermap.org/data/2.5/forecast?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn,ee", "Metric"), 3)));
	}
}
