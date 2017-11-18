package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;

public class ConnectionUtility{
	
	public String insertedUrl;
	
	public ConnectionUtility(String url) {
		this.insertedUrl = url;
	}

	public boolean internetConnectionExists() {
	    try {
	        URL url = new URL(insertedUrl);
	        URLConnection connection = url.openConnection();
	        connection.connect();
	        return true;
	    } catch (MalformedURLException e) {
	        throw new RuntimeException(e);
	    } catch (IOException e) {
	        return false;
	    }
	}
	
	public JSONObject readJsonFromUrl() throws IOException {
		String resultString = null;
		try {
			URL url = new URL(insertedUrl);
			URLConnection urlCon = url.openConnection();
			InputStreamReader inputStreamReader = new InputStreamReader(urlCon.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	        resultString = bufferedReader.readLine();
	        bufferedReader.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		JSONObject resultJson = null;
		try {
			resultJson = new JSONObject(resultString);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return resultJson;
	}

	public String getInsertedUrl() {
		return insertedUrl;
	}

	public void setInsertedUrl(String insertedUrl) {
		this.insertedUrl = insertedUrl;
	}
}
