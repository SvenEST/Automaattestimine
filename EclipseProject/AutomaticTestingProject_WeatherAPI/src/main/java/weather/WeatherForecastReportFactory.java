package weather;

import java.io.IOException;

import org.json.JSONObject;

import connection.ConnectionUtility;

public class WeatherForecastReportFactory {

	private String units = "metric";
	private String apiKey;
	
	public WeatherForecastReportFactory(String cityName, String apiKey, String units) {
		this.apiKey = apiKey;
		changeUnits(units);
	}

	public JSONObject getWeatherForecastInfoFromApi(String city){
		String apiUrl = "http://api.openweathermap.org/data/2.5/forecast";
		String url = apiUrl + "?q=" + city + "&units=" + units + "&appid=" + apiKey;
		ConnectionUtility connection = new ConnectionUtility(url);
		JSONObject forecastInfo = null;
		try {
			forecastInfo = connection.readJsonFromUrl();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return forecastInfo;
	}
	
	public void changeUnits(String newUnit) {
		if(newUnit.equalsIgnoreCase("Metric") || newUnit.equalsIgnoreCase("Imperial") || newUnit.equalsIgnoreCase("Kelvin")) {
			this.units = newUnit;
		}
	}
	
	public String getUnits() {
		return units;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiKey() {
		return apiKey;
	}
}
