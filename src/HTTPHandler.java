import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public class HTTPHandler {
	
	public static void main(String args[]) throws ClientProtocolException, IOException {
		kistalkLogin("http://kistalk.com/login/create", "fuckyou", "");
	}
	
	public HTTPHandler() {
		
	}
	
	public static String httpGet(String url) {

		HttpClient client = new DefaultHttpClient();

		HttpGet get = new HttpGet(url);

		ResponseHandler<String> brs = new BasicResponseHandler();

		try {
			String response = client.execute(get, brs);
			return response;
		} catch (ClientProtocolException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			client.getConnectionManager().shutdown();
		}

		return null;
	}
	
	public static void kistalkLogin(String loginUrl, String username, String password) throws ClientProtocolException, IOException {

		String loginPage = httpGet(loginUrl);
		String myPat = 	"(<input\\stype=\"hidden\"\\sname=\"lt\"\\svalue=\")(.{76})(\"/>)";

		Pattern p = Pattern.compile(myPat);
		Matcher m = p.matcher(loginPage);
		String key = null;	
		
		if(m.find()){
			key = m.group(2);
		} else {
			throw new RuntimeException("Authentication error: Failed to retrieve CAS-Key.");
		}
		
		DefaultHttpClient client = new DefaultHttpClient();
		HttpContext context = new BasicHttpContext();
		
		CookieStore cookieJar = new BasicCookieStore();
		client.setCookieStore(cookieJar);
		HttpPost request = new HttpPost("https://login.kth.se/login?service=http%3A%2F%2Fkistalk.com%2Flogin%2Fcreate");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("_eventId", "submit"));
		params.add(new BasicNameValuePair("lt", key));
		params.add(new BasicNameValuePair("usedMethod", "password"));
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));
		
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params);
		request.setEntity(formEntity);
		
		
		HttpResponse response = client.execute(request);

		
		request = new HttpPost();
		
		System.out.println(response);
	}
}
