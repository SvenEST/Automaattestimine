package weather;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import file.FileUtility;

public class RunConsoleCodeTest {

	public static void main(String[] args) throws IOException{
		System.out.println("Getting info using the facade class:");
		WeatherService request = new WeatherService("Tallinn", "1a8a3563bee4967e64490dbfadf83b7e", "metric");
		System.out.println("Current temperature in " + request.getCityName() + ": " + request.getCurrentTemperature());
		System.out.println("Forecast temperature for day 1 in " + request.getCityName() + ": " + request.getForecastTemperatureForDay(1));
		System.out.println("Forecast temperature for day 2 in " + request.getCityName() + ": " + request.getForecastTemperatureForDay(2));
		System.out.println("Forecast temperature for day 3 in " + request.getCityName() + ": " + request.getForecastTemperatureForDay(3));
		System.out.println("Forecast min temperature for day 1 in " + request.getCityName() + ": " + request.getForecastMinTemperatureForDay(1));
		System.out.println("Forecast min temperature for day 2 in " + request.getCityName() + ": " + request.getForecastMinTemperatureForDay(2));
		System.out.println("Forecast min temperature for day 3 in " + request.getCityName() + ": " + request.getForecastMinTemperatureForDay(3));
		System.out.println("Forecast max temperature for day 1 in " + request.getCityName() + ": " + request.getForecastMaxTemperatureForDay(1));
		System.out.println("Forecast max temperature for day 2 in " + request.getCityName() + ": " + request.getForecastMaxTemperatureForDay(2));
		System.out.println("Forecast max temperature for day 3 in " + request.getCityName() + ": " + request.getForecastMaxTemperatureForDay(3));
		System.out.println("Weather request geo coordinates: " + request.getGeoCoordinates());
		
		System.out.println("");
		System.out.println("'Testing' file reading and writing:");
		Path inputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\Sisendfailid\\input.txt");
		Path outputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\Sisendfailid\\");
		
		FileUtility fileUtility = new FileUtility();
		System.out.println("Input file content: " + fileUtility.readFile(inputPath));
		
		WeatherService request2 = new WeatherService(inputPath, "1a8a3563bee4967e64490dbfadf83b7e", "metric");
		request2.WriteWeatherReportsInfoToFiles(outputPath, false);
		System.out.println("Wrote output files");
		
		System.out.println("");
		System.out.println("Testing weather request with user input from the console:");
		System.out.println("--------------------------------------------------");
		WeatherService request3 = new WeatherService("1a8a3563bee4967e64490dbfadf83b7e");
		System.out.println("Current temperature in " + request3.getCityName() + ": " + request3.getCurrentTemperature());
		System.out.println("Forecast temperature for day 1 in " + request3.getCityName() + ": " + request3.getForecastTemperatureForDay(1));
		System.out.println("Forecast temperature for day 2 in " + request3.getCityName() + ": " + request3.getForecastTemperatureForDay(2));
		System.out.println("Forecast temperature for day 3 in " + request3.getCityName() + ": " + request3.getForecastTemperatureForDay(3));
		System.out.println("Forecast min temperature for day 1 in " + request3.getCityName() + ": " + request3.getForecastMinTemperatureForDay(1));
		System.out.println("Forecast min temperature for day 2 in " + request3.getCityName() + ": " + request3.getForecastMinTemperatureForDay(2));
		System.out.println("Forecast min temperature for day 3 in " + request3.getCityName() + ": " + request3.getForecastMinTemperatureForDay(3));
		System.out.println("Forecast max temperature for day 1 in " + request3.getCityName() + ": " + request3.getForecastMaxTemperatureForDay(1));
		System.out.println("Forecast max temperature for day 2 in " + request3.getCityName() + ": " + request3.getForecastMaxTemperatureForDay(2));
		System.out.println("Forecast max temperature for day 3 in " + request3.getCityName() + ": " + request3.getForecastMaxTemperatureForDay(3));
		System.out.println("Weather request geo coordinates: " + request3.getGeoCoordinates());
		System.out.println("--------------------------------------------------");
	}
}
