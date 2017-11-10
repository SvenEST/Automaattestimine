package testhelpers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.json.JSONObject;

public class Validator {

    public static void validateTemperature(int result, String units) throws Exception{
        int maxTemp = 100;
        int minTemp = -100;
        if(units.equalsIgnoreCase("metric")){
            maxTemp = 100;
            minTemp = -100;
        }else if(units.equalsIgnoreCase("imperial")){
            maxTemp = 140;
            minTemp = -148;
	    }else if(units.equalsIgnoreCase("kelvin")){
	        maxTemp = 373;
	        minTemp = 173;
	    }
        if (result != (int)result)
            throw new Exception("Response temperature is not int");
        if (result < minTemp)
            throw new Exception("Temperature cannot be lower than: " + minTemp + ", report shows: " + result);
        if (result > maxTemp)
            throw new Exception("Temperature cannot be higher than: " + maxTemp + ", report shows: " + result);
    }
    
    public static void validateJsonFormat(JSONObject jsonObject) {
		assertTrue("Response result must be a JSONObject", jsonObject == (JSONObject)jsonObject);
		assertFalse("Response result can't be empty", jsonObject.toString().isEmpty());
		assertTrue("Response result must start with '{'", jsonObject.toString().startsWith("{"));
		assertTrue("Response result must end with '}'", jsonObject.toString().endsWith("}"));
    }
    
    public static void validateGeoCoordinates(String geoCoordinates) throws Exception {
        double latMax = 90;
        double latMin = -90;
        double lngMax = 180;
        double lngMin = -180;
        
        String[] tempArray;
        tempArray = geoCoordinates.split(":");
        double lat = Double.parseDouble(tempArray[0]);
        double lon = Double.parseDouble(tempArray[1]);
        
        if(lat>latMax || lat<latMin)
            throw new Exception("Geo-coordinates latitude is not valid");
        if(lon>lngMax ||  lon<lngMin)
            throw new Exception("Geo-coordinates longitude is not valid");
	}

}
