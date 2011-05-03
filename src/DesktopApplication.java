import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public class DesktopApplication implements Observer { // nej
	String[] confValues;
	NiftyHTTP nifty;
	ConfigSettings config = new ConfigSettings();
	
	public DesktopApplication() {
		LogInFrame loginframe = new LogInFrame();
		loginframe.addObserver(this);
		
	}

	public void update(Observable o, Object p) {

	}

	public void login(String user, String token, JFrame logFrame) { // lol
		nifty = new NiftyHTTP(user, token);
		if (nifty.validateToken()) {
			logFrame.dispose();
			confValues = config.getValues();
			adminframe.setupFrame(listener);
		} else {//skit i detta
			
		}
	}

	// Main
	public static void main(String[] args) {
		new DesktopApplication();

	}
}
