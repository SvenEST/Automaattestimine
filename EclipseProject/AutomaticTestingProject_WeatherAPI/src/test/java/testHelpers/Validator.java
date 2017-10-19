package testHelpers;

public class Validator {

    public static void validateTemperature(int result, String units) throws Exception{
        int maxTemp = 100;
        int minTemp = -100;
        if(units.equals("metric")){
            maxTemp = 100;
            minTemp = -100;
        }else if(units.equals("imperial")){
            maxTemp = 140;
            minTemp = -148;
	    }else if(units.equals("kelvin")){
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

}
