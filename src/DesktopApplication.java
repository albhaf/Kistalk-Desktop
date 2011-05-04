import java.io.IOException;
import javax.swing.JFrame;

public class DesktopApplication {
	String event, food_description, path;
	NiftyHTTP nifty;
	ConfigSettings config = new ConfigSettings();
	SlidePath slidepath = new SlidePath();
	LogInFrame loginframe;
	
	public DesktopApplication() {
		loginframe = new LogInFrame(this);
		loginframe.logFrame.setVisible(true);
		
	}

	public void login(String user, String token, JFrame logFrame) {
		String[] values = new String[11];
		AdminFrame adminframe = new AdminFrame(this);
		nifty = new NiftyHTTP(user, token);
		
		if (nifty.validateToken()) {
			loginframe.clooose();
			values = config.getValues();
			adminframe.setupFrame(values, slidepath.ninja(values[9]), slidepath.ninja(values[10]));
			
		} else {
			FailFrame fail = new FailFrame();
			fail.errFrame.setVisible(true);
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
	
	public void announce(String food_description, String event, boolean pub_open, boolean food_ready){ // Send announcement to server
//		if (food.equals(null) == false){
//			food_description = food;
//		}else if(ev.equals(null) == false){
//			event = ev;
//		}
		
		nifty.postAnnouncement(food_description, event, pub_open, food_ready);
	}
	
	public void savePath(String name){
		config.setValues(slidepath.add(name, path, getConf()));
	}
	
	public void remPath(String name){
		config.setValues(slidepath.remove(name, getConf()));
	}
	
	public void popup(String message, String pathTmp, boolean foodChbTmp, boolean pubChbTmp){ //Pub, Food & SaveSlideshow
		path = pathTmp;
		PopupFrame popupframe = new PopupFrame(message, this);
		popupframe.popFrame.setVisible(true);
		
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

	// Main
	public static void main(String[] args) {
		new DesktopApplication();
		
	}
	
}
