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
			fail("Error", "Wrong username and/or token. Try again.");
		}
	}
	
	public String[] getConf(){
		return config.getValues();
	}
	
	public void setConf(String[] vals){
		config.setValues(vals);
	}
	
	public void resetConf(){
		adminframe.setTxt(config.resetValues());
	}
	
	public void announce(String food_description, String event, boolean pub_open, boolean food_ready){ // Send announcement to server
		nifty.postAnnouncement(food_description, event, pub_open, food_ready);
		fail("Announcement", "Announcement successfully sent!");
	}
	
	public void savePath(String name){
		config.setValues(slidepath.add(name, path, getConf()));
		adminframe.slideSaved(name, path);
		adminframe.enable();
		
	}
	
	public void remPath(String name){
		config.setValues(slidepath.remove(name, getConf()));
	}
	
	public void popup(String message, String pathTmp){ //For SaveSlideshow
		path = pathTmp;
		adminframe.disable();
		PopupFrame popupframe = new PopupFrame(message, bgImage, this);
		popupframe.popFrame.setVisible(true);
		
	}
	
	public void closePop(){
		adminframe.enable();
	}
	
	public void fail(String t, String m){
		FailFrame fail = new FailFrame(t, m);
		fail.errFrame.setVisible(true);
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
