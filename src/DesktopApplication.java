import java.io.IOException;
import javax.swing.JFrame;

public class DesktopApplication {
	String path;
	NiftyHTTP nifty;
	LogInFrame loginframe;
	AdminFrame adminframe;
	ConfigSettings config = new ConfigSettings();
	SlidePath slidepath = new SlidePath();
	
	public DesktopApplication() {
		loginframe = new LogInFrame(this);
		loginframe.logFrame.setVisible(true);
		
	}

	public void login(String user, String token, JFrame logFrame) {
		String[] values = new String[11];
		adminframe = new AdminFrame(this);
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
		nifty.postAnnouncement(food_description, event, pub_open, food_ready);
	}
	
	public void savePath(String name){
		config.setValues(slidepath.add(name, path, getConf()));
		adminframe.slideSaved(name);
	}
	
	public void remPath(String name){
		config.setValues(slidepath.remove(name, getConf()));
	}
	
	public void popup(String message, String pathTmp){ //Pub, Food & SaveSlideshow
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
