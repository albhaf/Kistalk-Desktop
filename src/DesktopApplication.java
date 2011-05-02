import java.util.Observable;
import java.util.Observer;


public class DesktopApplication implements Observer {
	
	public DesktopApplication() {
		LogInFrame loginframe = new LogInFrame();
		loginframe.addObserver(this);
	}
	
	public void update(Observable o, Object p) {
		
		
	}
	
	public void login(){
		if (new NiftyHTTP(loginframe.logUserTxt.getText(), loginframe.logPassTxt.getText()).validateToken()){
			loginframe.logFrame.dispose();
			config.getValues();
			adminframe.setupFrame(listener);
		} else {
			loginframe.logUserTxt.setText("Fel användarnamn");
			loginframe.logPassTxt.setText("eller token...");
			System.out.println("WROOONG!");
		}
	}
	
	
	
	//	Main
	public static void main(String[] args) {
		new DesktopApplication();
		
	}
}
