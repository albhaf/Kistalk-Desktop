import java.util.Observable;
import java.util.Observer;

public class DesktopApplication implements Observer { // nej

	public DesktopApplication() {
		LogInFrame loginframe = new LogInFrame();
		loginframe.addObserver(this); // <-- Hej, detta är vår controller men
										// trots detta FUNKAR INTE
										// SKITHELVTETET! JÄVLAKUKJAVA SOM INTE
										// BARA KAN ACCEPTERA EN JÄVLA
										// OBSERVERHELVETE!!!
	}

	public void update(Observable o, Object p) {

	}

	public void login() { // lol
		if (new NiftyHTTP(loginframe.logUserTxt.getText(),
				loginframe.logPassTxt.getText()).validateToken()) {
			loginframe.logFrame.dispose();//fel
			config.getValues();
			adminframe.setupFrame(listener);
		} else {//skit i detta
			loginframe.logUserTxt.setText("Fel användarnamn");
			loginframe.logPassTxt.setText("eller token...");
			System.out.println("WROOONG!");
		}
	}

	// Main
	public static void main(String[] args) {
		new DesktopApplication();

	}
}
