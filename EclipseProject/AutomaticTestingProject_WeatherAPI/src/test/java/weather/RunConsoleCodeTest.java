package weather;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import file.FileUtility;

public class RunConsoleCodeTest {

	public static void main(String[] args) throws IOException{
		System.out.println("Getting info using the service class:");
		System.out.println("");
		
		WeatherService weatherService = new WeatherService("Tallinn", "1a8a3563bee4967e64490dbfadf83b7e", "metric");
		weatherService.updateWeather(1);
		System.out.println("Weather request geo coordinates: " + weatherService.getGeoCoordinates());
		System.out.println("Current temperature in " + weatherService.getCityName() + ": " + weatherService.getCurrentTemperature());
		System.out.println("");
		
		System.out.println("Forecast temperature for day 1 in " + weatherService.getCityName() + ": " + weatherService.getForecastTemperatureForDay());
		System.out.println("Forecast min temperature for day 1 in " + weatherService.getCityName() + ": " + weatherService.getForecastMinTemperatureForDay());
		System.out.println("Forecast max temperature for day 1 in " + weatherService.getCityName() + ": " + weatherService.getForecastMaxTemperatureForDay());
		System.out.println("");
		
		weatherService.updateWeather(2);
		System.out.println("Forecast temperature for day 2 in " + weatherService.getCityName() + ": " + weatherService.getForecastTemperatureForDay());
		System.out.println("Forecast min temperature for day 2 in " + weatherService.getCityName() + ": " + weatherService.getForecastMinTemperatureForDay());
		System.out.println("Forecast max temperature for day 2 in " + weatherService.getCityName() + ": " + weatherService.getForecastMaxTemperatureForDay());
		System.out.println("");
		
		weatherService.updateWeather(3);
		System.out.println("Forecast temperature for day 3 in " + weatherService.getCityName() + ": " + weatherService.getForecastTemperatureForDay());
		System.out.println("Forecast min temperature for day 3 in " + weatherService.getCityName() + ": " + weatherService.getForecastMinTemperatureForDay());
		System.out.println("Forecast max temperature for day 3 in " + weatherService.getCityName() + ": " + weatherService.getForecastMaxTemperatureForDay());
		
		/*-----------------------------------------------------------------------*/
		
		System.out.println("\n\n");
		System.out.println("'Testing' file reading and writing:");
		Path inputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\Sisendfailid\\input.txt");
		Path outputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\Sisendfailid\\");
		
		FileUtility fileUtility = new FileUtility();
		System.out.println("Input file content: " + fileUtility.readFile(inputPath));
		
		WeatherService weatherService2 = new WeatherService(inputPath, "1a8a3563bee4967e64490dbfadf83b7e", "metric");
		System.out.println(weatherService2.getCityName());
		weatherService2.updateWeather(1);
		weatherService2.WriteWeatherReportsInfoToFiles(outputPath, false);
		System.out.println("Wrote output files");
		
		/*-----------------------------------------------------------------------*/
		
		System.out.println("\n\n");
		System.out.println("Testing weather request with user input from the console:");
		System.out.println("--------------------------------------------------");
		WeatherService weatherService3 = new WeatherService("1a8a3563bee4967e64490dbfadf83b7e");
		weatherService3.updateWeather(1);
		System.out.println("Weather request geo coordinates: " + weatherService3.getGeoCoordinates());
		System.out.println("Current temperature in " + weatherService3.getCityName() + ": " + weatherService3.getCurrentTemperature());
		System.out.println("");
		
		System.out.println("Forecast temperature for day 1 in " + weatherService3.getCityName() + ": " + weatherService3.getForecastTemperatureForDay());
		System.out.println("Forecast min temperature for day 1 in " + weatherService3.getCityName() + ": " + weatherService3.getForecastMinTemperatureForDay());
		System.out.println("Forecast max temperature for day 1 in " + weatherService3.getCityName() + ": " + weatherService3.getForecastMaxTemperatureForDay());
		System.out.println("");
		
		weatherService3.updateWeather(2);
		System.out.println("Forecast temperature for day 2 in " + weatherService3.getCityName() + ": " + weatherService3.getForecastTemperatureForDay());
		System.out.println("Forecast min temperature for day 2 in " + weatherService3.getCityName() + ": " + weatherService3.getForecastMinTemperatureForDay());
		System.out.println("Forecast max temperature for day 2 in " + weatherService3.getCityName() + ": " + weatherService3.getForecastMaxTemperatureForDay());
		System.out.println("");
		
		weatherService3.updateWeather(3);
		System.out.println("Forecast temperature for day 3 in " + weatherService3.getCityName() + ": " + weatherService3.getForecastTemperatureForDay());
		System.out.println("Forecast min temperature for day 3 in " + weatherService3.getCityName() + ": " + weatherService3.getForecastMinTemperatureForDay());
		System.out.println("Forecast max temperature for day 3 in " + weatherService3.getCityName() + ": " + weatherService3.getForecastMaxTemperatureForDay());
		System.out.println("--------------------------------------------------");
	}
}
