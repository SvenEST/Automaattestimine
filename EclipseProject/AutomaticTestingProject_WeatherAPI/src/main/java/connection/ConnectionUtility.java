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
	
	public URL insertedUrl;
	
	public ConnectionUtility(URL url) {
		this.insertedUrl = url;
	}

	public boolean internetConnectionExists() {
	    try {
	        URLConnection connection = insertedUrl.openConnection();
	        connection.connect();
	        return true;
	    } catch (MalformedURLException e) {
	        throw new RuntimeException(e);
	    } catch (IOException e) {
	        return false;
	    }
	}
	
	public JSONObject readJsonFromUrl(){
		String resultString = null;
		try {
			URLConnection urlCon = insertedUrl.openConnection();
			InputStreamReader inputStreamReader = new InputStreamReader(urlCon.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	        resultString = bufferedReader.readLine();
	        bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Changing result to JSONObject format
		JSONObject resultJson = null;
		try {
			resultJson = new JSONObject(resultString);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return resultJson;
	}

	public URL getInsertedUrl() {
		return insertedUrl;
	}

	public void setInsertedUrl(URL insertedUrl) {
		this.insertedUrl = insertedUrl;
	}
}
