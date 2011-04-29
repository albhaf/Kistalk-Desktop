import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;


import java.util.regex.*;


public class HTTPHandler {
	
	public static void main(String args[]) throws ClientProtocolException, IOException{
		kistalkLogin("http://kistalk.com/login/create", "foo", "bar");
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
	
	
	public static void kistalkLogin(String loginUrl, String username, String password) {

		String loginPage = httpGet(loginUrl);
		String myPat = 	"(<input\\stype=\"hidden\"\\sname=\"lt\"\\svalue=\")(.{76})(\"/>)";

		Pattern p = Pattern.compile(myPat);
		Matcher m = p.matcher(loginPage);

		if(m.matches()){
			System.out.println(m.group(1));
		}

		while(m.find()){
			System.out.print(loginPage.substring(m.start(), m.end()));
		}
	}
}
