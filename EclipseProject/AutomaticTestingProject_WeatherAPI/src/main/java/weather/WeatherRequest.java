package weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import connection.ConnectionUtility;
import file.FileReader;
import file.WriteFile;

public class WeatherRequest {
	
	public String cityName;
	public String units = "Metric";
	List<String> cityNamesList;

	public WeatherRequest(String cityName, String units) {
		this.cityName = cityName;
		this.units = units;
		ConnectionUtility con = new ConnectionUtility("https://www.google.com/");
        if (con.internetConnectionExists() != true) {
        	System.out.println("No internet connection!");
        }
	}
	
	public WeatherRequest(Path inputFilePath, String units) {
		ConnectionUtility con = new ConnectionUtility("https://www.google.com/");
        if (con.internetConnectionExists() == true) {
        	FileReader fileReader = new FileReader();
        	cityNamesList = new ArrayList<String>();
            try {
    			String cityNames = fileReader.readFile(inputFilePath);
            	cityNamesList = Arrays.asList(cityNames.split(";"));
            	
            	List<String> cityNamesListTrimmed = new ArrayList<String>();
            	for(String cityName: cityNamesList) {
            		cityNamesListTrimmed.add(cityName.trim());
            	}
            	cityNamesList = cityNamesListTrimmed;     	
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        } else {
        	System.out.println("No internet connection!");
        }
	}
	
	public WeatherRequest() {
		ConnectionUtility con = new ConnectionUtility("https://www.google.com/");
        if (con.internetConnectionExists() == true) {
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
        } else {
        	System.out.println("No internet connection!");
        }
	}
	
	public void WriteWeatherReportInfoToFile(Path outputFileLocation, boolean appendFile) {
        CurrentWeather currentWeather = new CurrentWeather();
		currentWeather.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		currentWeather.setApiUrl("http://api.openweathermap.org/data/2.5/weather");
		currentWeather.changeUnits(units);
		
		WeatherForecast weatherForecast = new WeatherForecast();
		weatherForecast.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		weatherForecast.setApiUrl("http://api.openweathermap.org/data/2.5/forecast");
		weatherForecast.changeUnits(units);
		
		JSONObject currentWeatherInfo;
		JSONObject weatherForecastInfo;
		for(String cityName: cityNamesList) {
			currentWeatherInfo = currentWeather.getWeatherInfo(cityName);
			String outputContent = currentWeatherInfo.toString();
			WriteFile fileWriter = new WriteFile();
			String outputFileName = cityName + "_" + "current" + ".txt";
			fileWriter.writeFile(outputFileLocation, outputFileName, outputContent, appendFile);
		}
		for(String cityName: cityNamesList) {
			weatherForecastInfo = weatherForecast.getWeatherForecastInfo(cityName);
			String outputContent = weatherForecastInfo.toString();
			WriteFile fileWriter = new WriteFile();
			String outputFileName = cityName + "_" + "forecast" + ".txt";
			fileWriter.writeFile(outputFileLocation, outputFileName, outputContent, appendFile);
		}
	}
	
	public int getCurrentTemperature() {
        CurrentWeather currentWeather = new CurrentWeather();
		currentWeather.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		currentWeather.setApiUrl("http://api.openweathermap.org/data/2.5/weather");
		currentWeather.changeUnits(units);
		JSONObject weatherInfoJson;
		weatherInfoJson = currentWeather.getWeatherInfo(cityName);
		int temperature = currentWeather.getTemperature(weatherInfoJson);
		return temperature;
	}
	
	public int getForecastTemperatureForDay(int dayNumber) {
		WeatherForecast weatherForecast = new WeatherForecast();
		weatherForecast.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		weatherForecast.setApiUrl("http://api.openweathermap.org/data/2.5/forecast");
		weatherForecast.changeUnits(units);
		JSONObject forecastInfo;
		int temperature = 0;
		forecastInfo = weatherForecast.getWeatherForecastInfo(cityName);
		JSONObject forecastForDay = weatherForecast.getForecastForSingleDay(forecastInfo , dayNumber);
		temperature = weatherForecast.getTemperature(forecastForDay);
		return temperature;
	}
	
	public int getForecastMinTemperatureForDay(int dayNumber) {
		WeatherForecast weatherForecast = new WeatherForecast();
		weatherForecast.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		weatherForecast.setApiUrl("http://api.openweathermap.org/data/2.5/forecast");
		weatherForecast.changeUnits(units);
		JSONObject forecastInfo;
		int temperature = 0;
		forecastInfo = weatherForecast.getWeatherForecastInfo(cityName);
		JSONObject forecastForDay = weatherForecast.getForecastForSingleDay(forecastInfo , dayNumber);
		temperature = weatherForecast.getMinTemperature(forecastForDay);
		return temperature;
	}
	
	public int getForecastMaxTemperatureForDay(int day) {
		WeatherForecast weatherForecast = new WeatherForecast();
		weatherForecast.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		weatherForecast.setApiUrl("http://api.openweathermap.org/data/2.5/forecast");
		weatherForecast.changeUnits(units);
		JSONObject forecastInfo;
		int temperature = 0;
		forecastInfo = weatherForecast.getWeatherForecastInfo(cityName);
		JSONObject forecastForDay = weatherForecast.getForecastForSingleDay(forecastInfo , day);
		temperature = weatherForecast.getMaxTemperature(forecastForDay);
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
