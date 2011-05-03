import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.*;
import java.util.ArrayList;
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
import org.apache.http.client.CookieStore;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.*;
import org.apache.http.*;
import org.*;
import java.util.Map.Entry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class NiftyHTTP {

	final static String AUTH_URL = "http://kistalk.com/api/validate_token";
	final static String FEED_URL = "http://kistalk.com/api/feed/desktop.xml";
	final static String ANNOUNCEMENTS_URL = "http://kistalk.com/api/announcement/new";
	final static String ENCODING = "UTF-8";

	private String username;
	private String authToken;

	public NiftyHTTP(String username, String authToken) throws RuntimeException {
		this.username = username;
		this.authToken = authToken;

	}
	

	public String postAnnouncement(HashMap<String, String> announcements) {

		/*
		 * Typically, these announcements can be posted: pub_open ( = 0 or 1),
		 * food_ready ( = 0 or 1), food_description, event
		 */

		List<NameValuePair> params = new ArrayList<NameValuePair>();

		params.add(new BasicNameValuePair("utf-8", "✓"));
		params.add(new BasicNameValuePair("username", this.username));
		params.add(new BasicNameValuePair("token", this.authToken));

		for (Entry<String, String> e : announcements.entrySet()) {
			params.add(new BasicNameValuePair(e.getKey(), e.getValue()));
		}

		return this.simplePost(ANNOUNCEMENTS_URL, params);
	}

	public boolean validateToken() {

		List<NameValuePair> params = new ArrayList<NameValuePair>();

		try {
			params.add(new BasicNameValuePair("username", URLEncoder.encode(
					this.username, ENCODING)));
			params.add(new BasicNameValuePair("token", URLEncoder.encode(
					this.authToken, ENCODING)));
		} catch (UnsupportedEncodingException e) {
			System.err.print("Failed to encode url for POST request: ");
			e.printStackTrace();
		}

		return this.simplePost(AUTH_URL, params).trim().equals("true");
	}

	public String getXMLFeed() {
		List<NameValuePair> params = new ArrayList<NameValuePair>();

		try {
			params.add(new BasicNameValuePair("username", URLEncoder.encode(
					this.username, ENCODING)));
			params.add(new BasicNameValuePair("token", URLEncoder.encode(
					this.authToken, ENCODING)));
		} catch (UnsupportedEncodingException e) {
			System.err.print("Failed to encode url for POST request: ");
			e.printStackTrace();
		}

		return this.simpleGet(FEED_URL, params);
	}

	public String getImage(String URL) {
		return "foo";
	}

	private String simpleGet(String URL) {
		return this.simpleGet(URL, null);
	}

	private String simpleGet(String URL, List<NameValuePair> params) {
		
		HttpClient client = new DefaultHttpClient();

		if(params != null){
			URL = URL + "?";
			Iterator<NameValuePair> it = params.iterator();
			
			try {
				while(it.hasNext()) {
				
					NameValuePair nvp = it.next();
				
					URL = URL + URLEncoder.encode(nvp.getName(), ENCODING) + "=" + URLEncoder.encode(nvp.getValue(), ENCODING);
					
					if (it.hasNext()){
						URL = URL + "&";
					}
				}
			} catch (UnsupportedEncodingException e) {
				System.err.print("Failed to encode url for GET request: ");
				e.printStackTrace();
			}
		}
		
		
		HttpGet request = new HttpGet(URL);
		
		ResponseHandler<String> brs = new BasicResponseHandler();
		
		String response = "false";
		
		try {
			response = client.execute(request, brs);
		} catch (Exception e) {
			System.err.print("Unable to retrieve XML-Feed: ");
			e.printStackTrace();
		} finally {
			client.getConnectionManager().shutdown();
		}

		return response;
	}
		
	private String simplePost(String URL, List<NameValuePair> params) {

		HttpClient client = new DefaultHttpClient();
		HttpPost request = new HttpPost(URL);

		request.setHeader("ContentType",
				"application/x-www-form-urlencoded;charset=utf-8;");

		try {
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params);
			request.setEntity(formEntity);
		} catch (UnsupportedEncodingException e) {
			System.err
					.print("Failed to encode parameters while attempting to craft post request: ");
			e.printStackTrace();
		}

		ResponseHandler<String> brs = new BasicResponseHandler();
		String response = null;

		try {
			response = client.execute(request, brs);
		} catch (Exception e) {
			System.err.print("Unable execute POST request: ");
			e.printStackTrace();
		} finally {
			client.getConnectionManager().shutdown();
		}

		return response;
	}
}
