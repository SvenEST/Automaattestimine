package weather;

import java.net.MalformedURLException;
import java.net.URL;

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
		JSONObject forecastInfo = null;
		try {
			URL url = new URL(apiUrl + "?q=" + city + "&units=" + units + "&appid=" + apiKey);
			ConnectionUtility connection = new ConnectionUtility(url);
			forecastInfo = connection.readJsonFromUrl();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
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
