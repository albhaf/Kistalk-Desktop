import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public class DesktopApplication implements Observer {
	NiftyHTTP nifty;
	ConfigSettings config = new ConfigSettings();
	AdminFrame adminframe = new AdminFrame();
	
	public DesktopApplication() {
		LogInFrame loginframe = new LogInFrame();
		loginframe.addObserver(this);
		
	}

	public void update(Observable o, Object p) {

	}

	public void login(String user, String token, JFrame logFrame) {
		
		nifty = new NiftyHTTP(user, token);
		if (nifty.validateToken()) {
			logFrame.dispose();
			adminframe.setupFrame(config.getValues());
			
		} else {
			//tryAgain
			
		}
	}
	
	public String[] getConf(){
		return config.getValues();
	}
	
	public void setConf(String[] vals){
		config.setValues(vals);
	}
	
	public String[] resetConf(){
		return config.resetValues();
	}
	
	public void yFood(){
		//Popup
		//Skicka info till servern
	}
	
	public void nFood(){
		//Skicka info till servern
	}

	public void yPub(){
		//Skicka info till servern
	}

	public void nPub(){
		//Skicka info till servern
	}
	
	public void savePath(String path){
		//Name = Popup.value();
		//Skriv till fil
	}
	
	public void remPath(String name){
		// String[] string = config.getValues();
		// newString1[] = DivideString.ninja(string[10])
		// newString2[] = DivideString.ninja(string[11])
		// while(newString1[i] != name)
		// newString1[i].delete();
		// newString2[i].delete();
		// string[10] = DivideString.putTogheter(newString1[])
		// string[11] = DivideString.putTogheter(newString2[])
		// config.setValues(string);
	}
	
	public void startShow(){
		try {
			new TwoDSlideShow();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void exitShow(){
		//exit twpDSlideShow only...
	}
	

	// Main
	public static void main(String[] args) {
		new DesktopApplication();

	}
}
