package weather;

import java.io.IOException;

import file.FileReader;
import file.WriteFile;

public class RunConsoleCodeTest {

	public static void main(String[] args) throws IOException{
		System.out.println("Getting info using facade class:");
		WeatherRequest request = new WeatherRequest("Tallinn", "Metric");
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
		
		System.out.println("Testing file reading:");
		FileReader fileReader = new FileReader();
		String inputPath = "C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\Sisendfailid\\input.txt";
		System.out.println("Input file content: " + fileReader.readFile(inputPath));
		String inputContent = fileReader.readFile(inputPath);
		String outputPath = "C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\Sisendfailid\\";
		WriteFile fileWriter = new WriteFile();
		System.out.println("Wrote file: " + fileWriter.writeFile(outputPath, "output", inputContent, false));
		
		System.out.println("Testing weather request with txt files:");
		WeatherRequest request2 = new WeatherRequest(inputPath);
	}
}
