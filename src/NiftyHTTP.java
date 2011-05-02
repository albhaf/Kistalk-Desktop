import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.client.CookieStore;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.*;
import org.apache.http.*;
import org.*;

public class NiftyHTTP {
	
	final static String AUTH_URL = "http://kistalk.com/api/validate_token";
	
	private String username;
	private String authToken;
	
	public NiftyHTTP (String username, String authToken) throws RuntimeException {
		
		this.username = username;
		this.authToken = authToken;
		
		try {
			this.validateToken();
			System.out.println("woot");
		} catch (RuntimeException e) {
			System.err.print("Failed to authenticate token: ");
			System.err.println(e.getStackTrace());
		}
	}
	
	public static void main(String[] args) {
		
		NiftyHTTP n = new NiftyHTTP("panderse", "emgkxra2it");
		
	}
	
	public boolean validateToken() {
		HttpClient client = new DefaultHttpClient();
		HttpPost request = new HttpPost(AUTH_URL);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("username", this.username));
		params.add(new BasicNameValuePair("token", this.authToken));
		
		try {
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params);
			request.setEntity(formEntity);
		} catch (UnsupportedEncodingException e){
			System.err.print("Failed to encode authentication parameters: ");
			System.err.println(e.getStackTrace());
		}
		
		ResponseHandler<String> brs = new BasicResponseHandler();
		
		String response = "false";
		
		try {
			response = client.execute(request, brs);
		} catch (Exception e) {
			System.err.print("Unable to reach server for authentication of token: ");
			System.err.println(e.getStackTrace());
		} finally {
			client.getConnectionManager().shutdown();
		}
		
		return (response.trim().equals("true"));
	}
	
	public String getDesktopXML() {
		return "ye aulde xml feede";
	}
}
