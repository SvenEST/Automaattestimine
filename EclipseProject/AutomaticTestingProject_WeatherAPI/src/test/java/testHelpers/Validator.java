package testHelpers;

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
    
    public static void validateGeoCoordinates(String geoCoordinates) throws Exception {
        double latMax = 90;
        double latMin = -90;
        double lngMax = 180;
        double lngMin = -180;
        /*
        if(geoCoordinates == null || geoCoordinates.latitude == NO_VALUE || geoCoordinates.longitude == NO_VALUE)
            throw new Exception("Geo-coordinates must be specified");
        if(geoCoordinates.latitude>latMax || geoCoordinates.latitude<latMin)
            throw new Exception("Geo-coordinates latitude is not valid");
        if(geoCoordinates.longitude>lngMax ||  geoCoordinates.longitude<lngMin)
            throw new Exception("Geo-coordinates longitude is not valid");
		*/
}

}
