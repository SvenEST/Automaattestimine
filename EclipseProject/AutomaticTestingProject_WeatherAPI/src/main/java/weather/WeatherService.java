package weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import file.FileUtility;

public class WeatherService {
	
	private String cityName;
	private List<String> cityNamesList;
	private String units;
	private CurrentWeatherReport currentWeatherReport;
	private WeatherForecastReport weatherForecastReport;
	private String apiKey;

	public WeatherService(String cityName, String apiKey, String units) {
		this.cityName = cityName;
		this.apiKey = apiKey;
		changeUnits(units);
	}
	
	public WeatherService(Path inputFilePath, String apiKey, String units) {
		this.apiKey = apiKey;
		
    	FileUtility fileUtility = new FileUtility();
    	
        String allCityNames = fileUtility.readFile(inputFilePath);
        cityNamesList = new ArrayList<String>();
		cityNamesList = Arrays.asList(allCityNames.split(";"));
		
		//Removes possible spaces from city names
		List<String> cityNamesListTrimmed = new ArrayList<String>();
		for(String cityName: cityNamesList) {
			cityNamesListTrimmed.add(cityName.trim());
		}
		
		cityNamesList = cityNamesListTrimmed;
		changeUnits(units);
	}
	
	public WeatherService(String apiKey) {
		this.apiKey = apiKey;
		
    	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Please enter a city name and press Enter:");
		try {
			String userInputCityName = bufferedReader.readLine().trim();
			this.cityName = userInputCityName;
		} catch (IOException e) {
			System.out.println("Failed to get user input city name: " + e.getMessage());
		}
		
		System.out.println("Enter prefferred unit. Metric (default), imperial or kelvin?");
		String userInputUnits = null;
		try {
			userInputUnits = bufferedReader.readLine().trim();
		} catch (IOException e) {
			System.out.println("Failed to get user input units: " + e.getMessage());
		}
		
		if (userInputUnits.isEmpty()) {
			System.out.println("User inserted unit not recognized. Default unit metric wil be used.");
		}
		changeUnits(userInputUnits);
	}
	
	public void updateWeather(int dayNumber) {
		CurrentWeatherReportFactory currentWeatherReportFactory = new CurrentWeatherReportFactory(cityName, apiKey, units);
		WeatherForecastReportFactory weatherForecastReportFactory = new WeatherForecastReportFactory(cityName, apiKey, units);
		currentWeatherReport = updateCurrentWeather(currentWeatherReportFactory, cityName);
		weatherForecastReport = updateForecastWeather(weatherForecastReportFactory, cityName, dayNumber);
	}
	
	public CurrentWeatherReport updateCurrentWeather(CurrentWeatherReportFactory currentWeatherReportFactory, String cityName) {
		JSONObject currentWeatherInfo = currentWeatherReportFactory.getCurrentWeatherInfoFromApi(cityName);
		CurrentWeatherReport currentWeatherReport = new CurrentWeatherReport(currentWeatherInfo);
		return currentWeatherReport;
	}
	
	public WeatherForecastReport updateForecastWeather(WeatherForecastReportFactory weatherForecastReportFactory, String cityName, int dayNumber) {
		JSONObject weatherForecastInfo = weatherForecastReportFactory.getWeatherForecastInfoFromApi(cityName);
		WeatherForecastReport weatherForecastReport = new WeatherForecastReport(weatherForecastInfo, dayNumber);
		return weatherForecastReport;
	}
	
	
	public void WriteWeatherReportsInfoToFiles(Path outputFileLocation, boolean appendFile){
		for(String cityName: cityNamesList) {
			String outputContent = createFileOutputContent(cityName);
			FileUtility fileUtility = new FileUtility();
			String outputFileName = cityName + ".txt";
			fileUtility.writeFile(outputFileLocation, outputFileName, outputContent, appendFile);
		}
	}
	
	private String createFileOutputContent(String cityName) {
		String outputContent = null;
		
		//CurrentWeatherReport currentWeatherReport = new CurrentWeatherReport(cityName, "1a8a3563bee4967e64490dbfadf83b7e", units);
		String cityNameFromReport = currentWeatherReport.getCityName();
		String geoCoords = currentWeatherReport.getGeoCoordinates();
		int currentTemp = currentWeatherReport.getTemperature();
		
		String lineSeperator = System.getProperty("line.separator");
		outputContent = "city: " + cityNameFromReport + lineSeperator +  
						"coordinates: " + geoCoords + lineSeperator +  
				        "current temperature: " + currentTemp + lineSeperator;
		
		int[] days = {1, 2, 3};
		for(int dayNumber: days) {
			//WeatherForecastReport weatherForecastReport = new WeatherForecastReport(cityName, "1a8a3563bee4967e64490dbfadf83b7e", units, dayNumber);
			int forecastMaxTemp = weatherForecastReport.getMaxTemperature();
			int forecastMinTemp = weatherForecastReport.getMinTemperature();
			outputContent += "forecast day " + dayNumber + " info: " + lineSeperator + 
						"\t" + "maximum temperature: " + forecastMaxTemp + lineSeperator +
						"\t" + "minimum temperature: " + forecastMinTemp + lineSeperator;
		}
		
		return outputContent;
	}
	
	public int getCurrentTemperature() {
		//CurrentWeatherReport currentWeatherReport = new CurrentWeatherReport(cityName, "1a8a3563bee4967e64490dbfadf83b7e", units);
		int temperature = currentWeatherReport.getTemperature();
		return temperature;
	}
	
	public int getForecastTemperatureForDay(int dayNumber) {
		//WeatherForecastReport weatherForecastReport = new WeatherForecastReport(cityName, "1a8a3563bee4967e64490dbfadf83b7e", units, dayNumber);
		int temperature = weatherForecastReport.getTemperature();
		return temperature;
	}
	
	public int getForecastMinTemperatureForDay(int dayNumber) {
		//WeatherForecastReport weatherForecastReport = new WeatherForecastReport(cityName, "1a8a3563bee4967e64490dbfadf83b7e", units, dayNumber);
		int minTemperature = weatherForecastReport.getTemperature();
		return minTemperature;
	}
	
	public int getForecastMaxTemperatureForDay(int dayNumber) {
		//WeatherForecastReport weatherForecastReport = new WeatherForecastReport(cityName, "1a8a3563bee4967e64490dbfadf83b7e", units, dayNumber);
		int maxTemperature = weatherForecastReport.getTemperature();
		return maxTemperature;
	}
	
	public String getGeoCoordinates() {
		//CurrentWeatherReport currentWeatherReport = new CurrentWeatherReport(cityName, "1a8a3563bee4967e64490dbfadf83b7e", units);
		String geoCoordinates = currentWeatherReport.getGeoCoordinates();
		return geoCoordinates;
	}
	
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityName() {
		return cityName;
	}
	
	public void setCityNamesList(List<String> cityNamesList) {
		this.cityNamesList = cityNamesList;
	}
	
	public List<String> getCityNamesList() {
		return cityNamesList;
	}

	public void changeUnits(String newUnit) {
		if(newUnit.equalsIgnoreCase("Metric") || newUnit.equalsIgnoreCase("Imperial") || newUnit.equalsIgnoreCase("Kelvin")) {
			this.units = newUnit;
		} else {
			this.units = "metric";
		}
	}

	public String getUnits() {
		return units;
	}
}
