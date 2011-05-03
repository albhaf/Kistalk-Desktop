import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public class DesktopApplication implements Observer {
	String event, food_description;
	boolean pub_open = false;
	NiftyHTTP nifty;
	ConfigSettings config = new ConfigSettings();
	AdminFrame adminframe = new AdminFrame();
	SlidePath slidepath = new SlidePath();
	
	public DesktopApplication() {
		LogInFrame loginframe = new LogInFrame();
		//loginframe.addObserver(this); add observable?
		
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
	
	public String popup(String message){
		String value = null; //tillfälligt
		PopupFrame popupframe = new PopupFrame(message);
		
		return value;
	}
	
	public void submit(){
		
	}
	

	// Main
	public static void main(String[] args) {
		new DesktopApplication();

	}
}
