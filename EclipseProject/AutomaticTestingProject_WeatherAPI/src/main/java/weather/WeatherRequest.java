package weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

import org.json.JSONObject;

import connection.Connection;
import file.FileReader;
import file.WriteFile;

public class WeatherRequest {
	
	public String cityName;
	public String units = "Metric";

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
        
        if (units != null) {
			if (units.equalsIgnoreCase("Metric") || units.equalsIgnoreCase("Imperial") || units.equalsIgnoreCase("Kelvin")) {
				this.units = units;
			}
		}
	}
	
	public WeatherRequest() {
		Connection con = new Connection();
        if (con.internetConnectionExists() != true) {
        	System.out.println("No internet connection!");
        }
        
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Please enter a city name and press Enter:");
		try {
			String userInputCityName = br.readLine().trim();
			this.cityName = userInputCityName;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Enter prefferred unit. Metric (default), imperial or kelvin?");
		String userInputUnits = null;
		try {
			userInputUnits = br.readLine().trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (userInputUnits != null) {
			if (userInputUnits.equalsIgnoreCase("Metric") || userInputUnits.equalsIgnoreCase("Imperial") || userInputUnits.equalsIgnoreCase("Kelvin")) {
				this.units = userInputUnits;
			}
		}else{
			System.out.println("User inserted nothing. Default unit metric wil be used.");
		}
	}
	
	public void WriteWeatherReportInfoToFile(Path outputFileLocation, String outputFileName, boolean appendFile) {
        CurrentWeather currentWeather = new CurrentWeather();
		currentWeather.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		currentWeather.setApiUrl("http://api.openweathermap.org/data/2.5/weather");
		currentWeather.changeUnits(units);
		
		JSONObject weatherInfoJson;
		try {
			weatherInfoJson = currentWeather.getWeatherInfo(cityName);
			String outputContent = weatherInfoJson.toString();
			WriteFile fileWriter = new WriteFile();
			fileWriter.writeFile(outputFileLocation, outputFileName, outputContent, appendFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getCurrentTemperature() {
        CurrentWeather currentWeather = new CurrentWeather();
		currentWeather.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		currentWeather.setApiUrl("http://api.openweathermap.org/data/2.5/weather");
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
	
	public int getForecastTemperatureForDay(int dayNumber) {
		WeatherForecast weatherForecast = new WeatherForecast();
		weatherForecast.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		weatherForecast.setApiUrl("http://api.openweathermap.org/data/2.5/forecast");
		weatherForecast.changeUnits(units);
		JSONObject forecastInfo;
		int temperature = 0;
		try {
			forecastInfo = weatherForecast.getWeatherForecastInfo(cityName);
			JSONObject forecastForDay = weatherForecast.getForecastForSingleDay(forecastInfo , dayNumber);
			temperature = weatherForecast.getTemperature(forecastForDay);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return temperature;
	}
	
	public int getForecastMinTemperatureForDay(int dayNumber) {
		WeatherForecast weatherForecast = new WeatherForecast();
		weatherForecast.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		weatherForecast.setApiUrl("http://api.openweathermap.org/data/2.5/forecast");
		weatherForecast.changeUnits(units);
		JSONObject forecastInfo;
		int temperature = 0;
		try {
			forecastInfo = weatherForecast.getWeatherForecastInfo(cityName);
			JSONObject forecastForDay = weatherForecast.getForecastForSingleDay(forecastInfo , dayNumber);
			temperature = weatherForecast.getMinTemperature(forecastForDay);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return temperature;
	}
	
	public int getForecastMaxTemperatureForDay(int day) {
		WeatherForecast weatherForecast = new WeatherForecast();
		weatherForecast.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		weatherForecast.setApiUrl("http://api.openweathermap.org/data/2.5/forecast");
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
