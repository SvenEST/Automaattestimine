package weather;

import java.io.IOException;
import java.nio.file.Path;

import org.json.JSONObject;

import connection.Connection;
import file.FileReader;
import file.WriteFile;

public class WeatherRequest {
	
	public String cityName;
	public String units;

	public WeatherRequest(String cityName, String units) {
		this.cityName = cityName;
		this.units = units;
		
		Connection con = new Connection();
        if (con.internetConnectionExists() != true) {
        	System.out.println("No internet connection!");
        }
	}
	
	public WeatherRequest(Path inputFilePath, String units) {
		Connection con = new Connection();
        if (con.internetConnectionExists() != true) {
        	System.out.println("No internet connection!");
        }
        
        FileReader fileReader = new FileReader();
        try {
			String cityName = fileReader.readFile(inputFilePath);
			this.cityName = cityName;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void WriteWeatherReportInfoToFile(Path outputFileLocation, String outputFileName) {
        CurrentWeather currentWeather = new CurrentWeather();
		currentWeather.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		currentWeather.setApiUrl("http://api.openweathermap.org/data/2.5/weather?q=");
		currentWeather.changeUnits(units);
		
		JSONObject weatherInfoJson;
		try {
			weatherInfoJson = currentWeather.getWeatherInfo(cityName);
			String outputContent = weatherInfoJson.toString();
			WriteFile fileWriter = new WriteFile();
			fileWriter.writeFile(outputFileLocation, outputFileName, outputContent, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getCurrentTemperature() {
        CurrentWeather currentWeather = new CurrentWeather();
		currentWeather.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		currentWeather.setApiUrl("http://api.openweathermap.org/data/2.5/weather?q=");
		currentWeather.changeUnits(units);
		JSONObject weatherInfoJson;
		try {
			weatherInfoJson = currentWeather.getWeatherInfo(cityName);
			int temperature = currentWeather.getTemperature(weatherInfoJson);
			return temperature;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getForecastTemperatureForDay(int day) {
		WeatherForecast weatherForecast = new WeatherForecast();
		weatherForecast.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		weatherForecast.setApiUrl("http://api.openweathermap.org/data/2.5/forecast?q=");
		weatherForecast.changeUnits(units);
		JSONObject forecastInfo;
		int temperature = 0;
		try {
			forecastInfo = weatherForecast.getWeatherForecastInfo(cityName);
			JSONObject forecastForDay = weatherForecast.getForecastForSingleDay(forecastInfo , day);
			temperature = weatherForecast.getTemperature(forecastForDay);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return temperature;
	}
	
	public int getForecastMinTemperatureForDay(int day) {
		WeatherForecast weatherForecast = new WeatherForecast();
		weatherForecast.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		weatherForecast.setApiUrl("http://api.openweathermap.org/data/2.5/forecast?q=");
		weatherForecast.changeUnits(units);
		JSONObject forecastInfo;
		int temperature = 0;
		try {
			forecastInfo = weatherForecast.getWeatherForecastInfo(cityName);
			JSONObject forecastForDay = weatherForecast.getForecastForSingleDay(forecastInfo , day);
			temperature = weatherForecast.getMinTemperature(forecastForDay);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return temperature;
	}
	
	public int getForecastMaxTemperatureForDay(int day) {
		WeatherForecast weatherForecast = new WeatherForecast();
		weatherForecast.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		weatherForecast.setApiUrl("http://api.openweathermap.org/data/2.5/forecast?q=");
		weatherForecast.changeUnits(units);
		JSONObject forecastInfo;
		int temperature = 0;
		try {
			forecastInfo = weatherForecast.getWeatherForecastInfo(cityName);
			JSONObject forecastForDay = weatherForecast.getForecastForSingleDay(forecastInfo , day);
			temperature = weatherForecast.getMaxTemperature(forecastForDay);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return temperature;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

}
