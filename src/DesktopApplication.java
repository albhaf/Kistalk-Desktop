import java.awt.Image;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class DesktopApplication {
	String path;
	Image bgImage;
	NiftyHTTP nifty;
	LogInFrame loginframe;
	AdminFrame adminframe;
	TwoDSlideShow slideshow;
	ConfigSettings config = new ConfigSettings();
	SlidePath slidepath = new SlidePath();
	
	public DesktopApplication() {
		ImageIcon icon = new ImageIcon("bgIcon.png");
		bgImage = icon.getImage();
		loginframe = new LogInFrame(bgImage, this);
		loginframe.logFrame.setVisible(true);
		
	}

	public void login(String user, String token, JFrame logFrame) {
		String[] values = new String[11];
		adminframe = new AdminFrame(this);
		nifty = new NiftyHTTP(user, token);
		
		if (nifty.validateToken()) {
			loginframe.clooose();
			values = config.getValues();
			adminframe.setupFrame(values, slidepath.ninja(values[9]), slidepath.ninja(values[10]), bgImage);
			
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
		PopupFrame popupframe = new PopupFrame(message, bgImage, this);
		popupframe.popFrame.setVisible(true);
		
	}
	
	public void startShow(){
		try {
			slideshow = new TwoDSlideShow();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void exitShow(){
		try{
			slideshow.close();
		}catch(NullPointerException e){
			
		}
	}

	// Main
	public static void main(String[] args) {
		new DesktopApplication();
		
	}
	
}
