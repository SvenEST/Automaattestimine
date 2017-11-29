package weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import connection.ConnectionUtility;
import file.FileUtility;

public class WeatherRequest {
	
	public String cityName;
	List<String> cityNamesList;
	public String units = "Metric";

	public WeatherRequest(String cityName, String units) {
		this.cityName = cityName;
		this.units = units;
	}
	
	public WeatherRequest(Path inputFilePath, String units) {
    	FileUtility fileUtility = new FileUtility();
    	cityNamesList = new ArrayList<String>();
        String allCityNames = fileUtility.readFile(inputFilePath);
		cityNamesList = Arrays.asList(allCityNames.split(";"));
		
		List<String> cityNamesListTrimmed = new ArrayList<String>();
		for(String cityName: cityNamesList) {
			cityNamesListTrimmed.add(cityName.trim());
		}
		cityNamesList = cityNamesListTrimmed;
	}
	
	public WeatherRequest() {
		ConnectionUtility con = new ConnectionUtility("https://www.google.com/");
        if (con.internetConnectionExists()) {
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
    		
    		if (userInputUnits != null) {
    			if (userInputUnits.equalsIgnoreCase("Metric") || userInputUnits.equalsIgnoreCase("Imperial") || userInputUnits.equalsIgnoreCase("Kelvin")) {
    				this.units = userInputUnits;
    			}
    		}else{
    			System.out.println("User inserted unit not recognized. Default unit metric wil be used.");
    		}
        } else {
        	System.out.println("No internet connection!");
        }
	}
	
	
	public void WriteWeatherReportsInfoToFiles(Path outputFileLocation, boolean appendFile){
		for(String cityName: cityNamesList) {
			String outputContent = null;
			
			CurrentWeatherReport currentWeatherReport = new CurrentWeatherReport(cityName, "1a8a3563bee4967e64490dbfadf83b7e", units);
			String geoCoords = currentWeatherReport.getGeoCoordinates();
			int currentTemp = currentWeatherReport.getTemperature();
			
			String lineSeperator = System.getProperty("line.separator");
			outputContent = "city: " + cityName + lineSeperator +  
							"coordinates: " + geoCoords + lineSeperator +  
					        "current temperature: " + currentTemp + lineSeperator;
			
			int[] days = {1, 2, 3};
			for(int dayNumber: days) {
				WeatherForecastReport weatherForecastReport = new WeatherForecastReport(cityName, "1a8a3563bee4967e64490dbfadf83b7e", units, dayNumber);
				int forecastMaxTemp = weatherForecastReport.getMaxTemperature();
				int forecastMinTemp = weatherForecastReport.getMinTemperature();
				outputContent += "forecast day " + dayNumber + " info: " + lineSeperator + 
							"\t" + "maximum temperature: " + forecastMaxTemp + lineSeperator +
							"\t" + "minimum temperature: " + forecastMinTemp + lineSeperator;
			}
			
			FileUtility fileUtility = new FileUtility();
			String outputFileName = cityName + ".txt";
			fileUtility.writeFile(outputFileLocation, outputFileName, outputContent, appendFile);
		}
	}
	
	public int getCurrentTemperature() {
		CurrentWeatherReport currentWeatherReport = new CurrentWeatherReport(cityName, "1a8a3563bee4967e64490dbfadf83b7e", units);
		int temperature = currentWeatherReport.getTemperature();
		return temperature;
	}
	
	public int getForecastTemperatureForDay(int dayNumber) {
		WeatherForecastReport weatherForecastReport = new WeatherForecastReport(cityName, "1a8a3563bee4967e64490dbfadf83b7e", units, dayNumber);
		int temperature = weatherForecastReport.getTemperature();
		return temperature;
	}
	
	public int getForecastMinTemperatureForDay(int dayNumber) {
		WeatherForecastReport weatherForecastReport = new WeatherForecastReport(cityName, "1a8a3563bee4967e64490dbfadf83b7e", units, dayNumber);
		int minTemperature = weatherForecastReport.getTemperature();
		return minTemperature;
	}
	
	public int getForecastMaxTemperatureForDay(int dayNumber) {
		WeatherForecastReport weatherForecastReport = new WeatherForecastReport(cityName, "1a8a3563bee4967e64490dbfadf83b7e", units, dayNumber);
		int maxTemperature = weatherForecastReport.getTemperature();
		return maxTemperature;
	}
	
	public String getGeoCoordinates() {
		CurrentWeatherReport currentWeatherReport = new CurrentWeatherReport(cityName, "1a8a3563bee4967e64490dbfadf83b7e", units);
		String coordinates = currentWeatherReport.getGeoCoordinates();
		return coordinates;
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
		}
	}

	public String getUnits() {
		return units;
	}
}
