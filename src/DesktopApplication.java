import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public class DesktopApplication {
	String event, food_description;
	boolean pub_open = false;
	NiftyHTTP nifty;
	ConfigSettings config = new ConfigSettings();
	SlidePath slidepath = new SlidePath();
	
	public DesktopApplication() {
		LogInFrame loginframe = new LogInFrame();
		loginframe.logFrame.setVisible(true);
		//loginframe.addObserver(this); add observable?
		
	}

	public void login(String user, String token, JFrame logFrame) {
		String[] values = new String[11];
		AdminFrame adminframe = new AdminFrame();
		nifty = new NiftyHTTP(user, token);
		
		if (nifty.validateToken()) {
			logFrame.dispose();
			values = config.getValues();
			adminframe.setupFrame(values, slidepath.ninja(values[9]), slidepath.ninja(values[10]));
			
		} else {
			// Fel token/user, notifiera!
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
	
	public void announceFood(String food, boolean pub_open, boolean food_ready){
		food_description = food;
		nifty.postAnnouncement(food_description, event, pub_open, food_ready);
	}

	public void announcePub(String ev, boolean pub_open, boolean food_ready){
		String event = ev;
		nifty.postAnnouncement(food_description, event, pub_open, food_ready);
	}
	
	public void savePath(String path){
		String name = popup("Name your path: ");
		config.setValues(slidepath.add(name, path, getConf()));
	}
	
	public void remPath(String name){
		config.setValues(slidepath.remove(name, getConf()));
	}
	
	public void startShow(){
		try {
			new TwoDSlideShow();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void exitShow(){
		//exit twoDSlideShow only...
	}
	
	public String popup(String message){ //Pub, Food & SaveSlideshow
		PopupFrame popupframe = new PopupFrame(message);
		return popupframe.popTxt.getText(); //Returnera? eller köra genom submit()?
		
	}
	
	public String submit(String value){
		return value;
	}
	

	// Main
	public static void main(String[] args) {
		new DesktopApplication();

	}
	
}
