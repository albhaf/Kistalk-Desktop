import java.util.Observable;
import java.util.Observer;

public class DesktopApplication implements Observer { // nej

	public DesktopApplication() {
		LogInFrame loginframe = new LogInFrame();
		loginframe.addObserver(this); // <-- Hej, detta �r v�r controller men
										// trots detta FUNKAR INTE
										// SKITHELVTETET! J�VLAKUKJAVA SOM INTE
										// BARA KAN ACCEPTERA EN J�VLA
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
			loginframe.logUserTxt.setText("Fel anv�ndarnamn");
			loginframe.logPassTxt.setText("eller token...");
			System.out.println("WROOONG!");
		}
	}

	// Main
	public static void main(String[] args) {
		new DesktopApplication();

	}
}
