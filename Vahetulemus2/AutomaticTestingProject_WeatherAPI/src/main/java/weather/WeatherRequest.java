package weather;

import java.io.IOException;

import org.json.JSONObject;

public class WeatherRequest {
	
	public String cityName;
	public String units;

	public WeatherRequest(String cityName, String units) {
		this.cityName = cityName;
		this.units = units;
		
		CurrentWeather.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		CurrentWeather.setApiUrl("http://api.openweathermap.org/data/2.5/weather?q=");
		CurrentWeather.changeUnit(units);
		
		WeatherForecast.setApiKey("1a8a3563bee4967e64490dbfadf83b7e");
		WeatherForecast.setApiUrl("http://api.openweathermap.org/data/2.5/forecast?q=");
		WeatherForecast.changeUnit(units);
	}
	
	public int getCurrentTemperature() {
		JSONObject weatherInfoJson;
		try {
			weatherInfoJson = CurrentWeather.getWeatherInfoJson(cityName);
			int temperature = CurrentWeather.getTemperature(weatherInfoJson);
			return temperature;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getForecastTemperatureForDay(int day) {
		JSONObject forecastInfo;
		int temperature = 0;
		try {
			forecastInfo = WeatherForecast.getWeatherForecastInfo(cityName);
			JSONObject forecastForDay = WeatherForecast.getForecastForDay(forecastInfo , day);
			temperature = WeatherForecast.getTemperature(forecastForDay);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
