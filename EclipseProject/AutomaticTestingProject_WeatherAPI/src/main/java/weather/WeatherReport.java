package weather;

import org.json.JSONObject;

public interface WeatherReport{
	
	public int getTemperature(JSONObject weatherInfo);
	public int getMinTemperature(JSONObject weatherInfo);
	public int getMaxTemperature(JSONObject weatherInfo);
	public String getCoordinates(JSONObject weatherInfo);
	public String getCityName(JSONObject weatherInfo);
	public void changeUnits(String newUnit);
	public String getUnits();
	public void setApiUrl(String apiUrl);
	public String getApiUrl();
	public void setApiKey(String apiKey);
	public String getApiKey();
	
}
