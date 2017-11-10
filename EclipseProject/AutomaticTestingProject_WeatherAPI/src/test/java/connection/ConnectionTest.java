package connection;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.json.JSONObject;
import org.junit.Test;

import connection.ConnectionUtility;
import testhelpers.Validator;

public class ConnectionTest {

	@Test
	public void testInternetConnectionExists() {
		ConnectionUtility con = new ConnectionUtility("https://www.google.com/");
		boolean result = con.internetConnectionExists();
		assertTrue(result);
	}
	
	@Test
	public void testJsonReadingFromUrl() {
		ConnectionUtility con = new ConnectionUtility("http://api.openweathermap.org/data/2.5/weather?q=Tallinn,ee&appid=1a8a3563bee4967e64490dbfadf83b7e");
		try {
			JSONObject result = con.readJsonFromUrl();
			Validator.validateJsonFormat(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
