package weather;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import file.FileReader;
import file.WriteFile;

public class RunConsoleCodeTest {

	public static void main(String[] args) throws IOException{
		System.out.println("Getting info using the facade class:");
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
		
		System.out.println("");
		System.out.println("'Testing' file reading and writing:");
		Path inputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\Sisendfailid\\input.txt");
		Path outputPath = Paths.get("C:\\Users\\SvenEST School\\Documents\\GitHub\\Automaattestimine\\Sisendfailid\\");
		
		FileReader fileReader = new FileReader();
		System.out.println("Input file content: " + fileReader.readFile(inputPath));
		String inputContent = fileReader.readFile(inputPath);
		
		/*
		WriteFile fileWriter = new WriteFile();
		String outputFileName = "output.txt";
		fileWriter.writeFile(outputPath, outputFileName, inputContent, false);
		FileReader fileReader2 = new FileReader();
		String recievedOutputContent = fileReader2.readFile(Paths.get(outputPath.toString(), outputFileName));
		System.out.println("Wrote into output file: " + recievedOutputContent);
		*/
		
		WeatherRequest request2 = new WeatherRequest(inputPath, "metric");
		String outputFileName = "output.txt";
		request2.WriteWeatherReportInfoToFile(outputPath, outputFileName);
		FileReader fileReader2 = new FileReader();
		String recievedOutputContent = fileReader2.readFile(Paths.get(outputPath.toString(), outputFileName));
		System.out.println("Wrote into output file: " + recievedOutputContent);
		
		System.out.println("");
		System.out.println("Testing weather request with user input from the console:");
		System.out.println("--------------------------------------------------");
		WeatherRequest request3 = new WeatherRequest();
		System.out.println("Current temperature in " + request.getCityName() + ": " + request.getCurrentTemperature());
		System.out.println("Forecast temperature for day 1 in " + request3.getCityName() + ": " + request3.getForecastTemperatureForDay(1));
		System.out.println("Forecast temperature for day 2 in " + request3.getCityName() + ": " + request3.getForecastTemperatureForDay(2));
		System.out.println("Forecast temperature for day 3 in " + request3.getCityName() + ": " + request3.getForecastTemperatureForDay(3));
		System.out.println("Forecast min temperature for day 1 in " + request3.getCityName() + ": " + request3.getForecastMinTemperatureForDay(1));
		System.out.println("Forecast min temperature for day 2 in " + request3.getCityName() + ": " + request3.getForecastMinTemperatureForDay(2));
		System.out.println("Forecast min temperature for day 3 in " + request3.getCityName() + ": " + request3.getForecastMinTemperatureForDay(3));
		System.out.println("Forecast max temperature for day 1 in " + request3.getCityName() + ": " + request3.getForecastMaxTemperatureForDay(1));
		System.out.println("Forecast max temperature for day 2 in " + request3.getCityName() + ": " + request3.getForecastMaxTemperatureForDay(2));
		System.out.println("Forecast max temperature for day 3 in " + request3.getCityName() + ": " + request3.getForecastMaxTemperatureForDay(3));
		System.out.println("--------------------------------------------------");
	}
}
