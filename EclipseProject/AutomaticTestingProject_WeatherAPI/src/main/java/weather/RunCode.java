package weather;

import java.io.IOException;

import org.json.JSONObject;

public class RunCode {

	public static void main(String[] args) throws IOException{
		System.out.println("Internet connection exists: " + Connection.internetConnectionExists());
		JSONObject weatherInfoJson = CurrentWeather.getWeatherInfoJson("http://api.openweathermap.org/data/2.5/weather?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn, ee");
		//System.out.println("Current weather info: " + weatherInfoJson);
		System.out.println("Current temperature: " + CurrentWeather.getTemperature(weatherInfoJson));
		//System.out.println("Weather forecast info: " + WeatherForecast.getWeatherForecastInfo("http://api.openweathermap.org/data/2.5/forecast?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn,ee", "Metric"));
		//System.out.println("Weather forecast for day 1: " + WeatherForecast.getForecastForDay(WeatherForecast.getWeatherForecastInfo("http://api.openweathermap.org/data/2.5/forecast?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn,ee"), 1));
		//System.out.println("Weather forecast for day 2: " + WeatherForecast.getForecastForDay(WeatherForecast.getWeatherForecastInfo("http://api.openweathermap.org/data/2.5/forecast?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn,ee"), 2));
		//System.out.println("Weather forecast for day 3: " + WeatherForecast.getForecastForDay(WeatherForecast.getWeatherForecastInfo("http://api.openweathermap.org/data/2.5/forecast?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn,ee"), 3));
		System.out.println("Forecast temperature for day 1: " + WeatherForecast.getTemperature(WeatherForecast.getForecastForDay(WeatherForecast.getWeatherForecastInfo("http://api.openweathermap.org/data/2.5/forecast?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn,ee"), 1)));
		System.out.println("Forecast temperature for day 2: " + WeatherForecast.getTemperature(WeatherForecast.getForecastForDay(WeatherForecast.getWeatherForecastInfo("http://api.openweathermap.org/data/2.5/forecast?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn,ee"), 2)));
		System.out.println("Forecast temperature for day 3: " + WeatherForecast.getTemperature(WeatherForecast.getForecastForDay(WeatherForecast.getWeatherForecastInfo("http://api.openweathermap.org/data/2.5/forecast?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn,ee"), 3)));
		
		System.out.println("--------------------------------------------------");
		System.out.println("Let's change units!");
		System.out.println("Current weather: " + CurrentWeather.changeUnit("Imperial"));
		System.out.println("Weather forecast: " + WeatherForecast.changeUnit("Imperial"));
		JSONObject weatherInfoJson2 = CurrentWeather.getWeatherInfoJson("http://api.openweathermap.org/data/2.5/weather?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn, ee");
		System.out.println("Current temperature: " + CurrentWeather.getTemperature(weatherInfoJson2));
		System.out.println("Forecast temperature for day 1: " + WeatherForecast.getTemperature(WeatherForecast.getForecastForDay(WeatherForecast.getWeatherForecastInfo("http://api.openweathermap.org/data/2.5/forecast?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn,ee"), 1)));
		System.out.println("Forecast temperature for day 2: " + WeatherForecast.getTemperature(WeatherForecast.getForecastForDay(WeatherForecast.getWeatherForecastInfo("http://api.openweathermap.org/data/2.5/forecast?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn,ee"), 2)));
		System.out.println("Forecast temperature for day 3: " + WeatherForecast.getTemperature(WeatherForecast.getForecastForDay(WeatherForecast.getWeatherForecastInfo("http://api.openweathermap.org/data/2.5/forecast?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn,ee"), 3)));
		
		System.out.println("--------------------------------------------------");
		System.out.println("Let's change units!");
		System.out.println("Current weather: " + CurrentWeather.changeUnit("Kelvin"));
		System.out.println("Weather forecast: " + WeatherForecast.changeUnit("Kelvin"));
		JSONObject weatherInfoJson3 = CurrentWeather.getWeatherInfoJson("http://api.openweathermap.org/data/2.5/weather?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn, ee");
		System.out.println("Current temperature: " + CurrentWeather.getTemperature(weatherInfoJson3));
		System.out.println("Forecast temperature for day 1: " + WeatherForecast.getTemperature(WeatherForecast.getForecastForDay(WeatherForecast.getWeatherForecastInfo("http://api.openweathermap.org/data/2.5/forecast?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn,ee"), 1)));
		System.out.println("Forecast temperature for day 2: " + WeatherForecast.getTemperature(WeatherForecast.getForecastForDay(WeatherForecast.getWeatherForecastInfo("http://api.openweathermap.org/data/2.5/forecast?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn,ee"), 2)));
		System.out.println("Forecast temperature for day 3: " + WeatherForecast.getTemperature(WeatherForecast.getForecastForDay(WeatherForecast.getWeatherForecastInfo("http://api.openweathermap.org/data/2.5/forecast?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn,ee"), 3)));
		
		System.out.println("--------------------------------------------------");
		System.out.println("City name from JSON: " + CurrentWeather.getCityName(CurrentWeather.getWeatherInfoJson("http://api.openweathermap.org/data/2.5/weather?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn, ee")));
		System.out.println("City name from JSON: " + WeatherForecast.getCityName(WeatherForecast.getWeatherForecastInfo("http://api.openweathermap.org/data/2.5/forecast?q=", "1a8a3563bee4967e64490dbfadf83b7e", "Tallinn,ee")));
		
		System.out.println("--------------------------------------------------");
		System.out.println(WeatherRequest.getTemperature("Tallinn", "Metric"));
		/*
		System.out.println("Same functionalities with facade class");
		WeatherRequest.setApiUrl("http://api.openweathermap.org/data/2.5/weather?q=");
		System.out.println(WeatherRequest.getApiUrl());
		WeatherRequest.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		System.out.println(WeatherRequest.getApiKey());
		WeatherRequest.getWeatherInfo("Tallinn");
		System.out.println("Current temperature: " + WeatherRequest.getTemperature("Tallinn", "Metric"));*/
	}
}
