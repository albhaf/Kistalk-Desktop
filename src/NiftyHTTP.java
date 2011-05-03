import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class NiftyHTTP {
	
	final static String AUTH_URL = "http://kistalk.com/api/validate_token";
	final static String FEED_URL = "http://kistalk.com/api/feed/desktop.xml";
	
	private String username;
	private String authToken;
	
	public static void main (String[] args) {
	}
	
	public NiftyHTTP (String username, String authToken) throws RuntimeException {
		
		this.username = username;
		this.authToken = authToken;
		
	}
	
	public boolean validateToken() {
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("username", this.username));
		params.add(new BasicNameValuePair("token", this.authToken));
		
		return this.simplePost(AUTH_URL, params).trim().equals("true");
	}
	
	public String getXMLFeed() {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("username", this.username));
		params.add(new BasicNameValuePair("token", this.authToken));
		
		return this.simpleGet(FEED_URL, params);
	}
	
	private String simpleGet(String URL) {
		return this.simpleGet(URL, null);
	}
	
	private String simpleGet(String URL, List<NameValuePair> params) {
		
		HttpClient client = new DefaultHttpClient();

		
		if(params != null){
			URL = URL + "?";
			Iterator<NameValuePair> it = params.iterator();
			
			while(it.hasNext()) {
				NameValuePair nvp = it.next();
				try {
					URL = URL + URLEncoder.encode(nvp.getName(), "UTF-8") + "=" + URLEncoder.encode(nvp.getValue(), "UTF-8");
					if (it.hasNext()){
						URL = URL + "&";
					}
				} catch (UnsupportedEncodingException e) {
					System.err.print("Failed to encode url for GET request: ");
					System.err.println(e.getStackTrace());
				}
		
		
		
		System.out.println(URL);
		
		HttpGet request = new HttpGet(URL);
		
		ResponseHandler<String> brs = new BasicResponseHandler();
		
		String response = "false";
		
		try {
			response = client.execute(request, brs);
		} catch (Exception e) {
			System.err.print("Unable to retrieve XML-Feed: ");
			System.err.println(e.getStackTrace());
		} finally {
			client.getConnectionManager().shutdown();
		}

		return response;
		
	}
	
	
	private String simplePost(String URL, List<NameValuePair> params) {
		
		HttpClient client = new DefaultHttpClient();
		HttpPost request = new HttpPost(URL);
		
		try {
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params);
			request.setEntity(formEntity);
		} catch (UnsupportedEncodingException e){
			System.err.print("Failed to encode parameters while attempting to craft post request: ");
			System.err.println(e.getStackTrace());
		}
		
		ResponseHandler<String> brs = new BasicResponseHandler();
		
		String response = "false";
		
		try {
			response = client.execute(request, brs);
		} catch (Exception e) {
			System.err.print("Unable to retrieve XML-Feed: ");
			System.err.println(e.getStackTrace());
		} finally {
			client.getConnectionManager().shutdown();
		}

		return response;
	}
}
