import java.awt.Font;
import java.awt.Image;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class DesktopApplication {
	byte nrOfConfValues;
	String path;
	Image bgImage;
	Font stdFont;
	Font bldFont;
	NiftyHTTP nifty;
	LogInFrame loginframe;
	AdminFrame adminframe;
	TwoDSlideShow slideshow;
	ConfigSettings config;
	FrameListener framelistener;
	String link;
	SlidePath slidepath = new SlidePath();
	
	public DesktopApplication() {
		nrOfConfValues = 11;
		ImageIcon icon = new ImageIcon("bgIcon.png");
		bgImage = icon.getImage();
		stdFont = new Font("Imperial", Font.PLAIN, 12);
		bldFont = new Font("Arial", Font.BOLD, 12);
		config = new ConfigSettings(nrOfConfValues);
		framelistener = new FrameListener(this);
		
		loginframe = new LogInFrame(bgImage, this);
		loginframe.logFrame.setVisible(true);
		
	}

	public void login(String user, String token, JFrame logFrame) {
		ConfigQueue values = new ConfigQueue();
		adminframe = new AdminFrame(this, stdFont, framelistener);
		nifty = new NiftyHTTP(user, token);
		
		if (nifty.validateToken()) {
			loginframe.clooose();
			values = config.getValues();
			adminframe.setupFrame(values, slidepath.ninja(values.getValue("Saved_Pubslides")), slidepath.ninja(values.getValue("Saved_Paths")), bgImage);
			
		} else {
			fail("Error", "Wrong username and/or token. Try again.");
		}
	}
	
	public ConfigQueue getConf(){
		return config.getValues();
	}
	
	public void setConf(ConfigQueue vals){
		config.setValues(vals);
	}
	
	public void resetConf(){
		ConfigQueue lclVals = config.resetValues();
		adminframe.setTxt(lclVals);
		adminframe.setPaths(slidepath.ninja(lclVals.getValue("Saved_Pubslides")), slidepath.ninja(lclVals.getValue("Saved_Paths")));
	}
	
	public void announce(String food_description, String price, String event, boolean pub_open, boolean food_ready){ // Send announcement to server
		nifty.postAnnouncement(food_description, price, event, pub_open, food_ready);
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
		PopupFrame popupframe = new PopupFrame(message, bgImage, this, bldFont);
		popupframe.popFrame.setVisible(true);
		
	}

	
	public void closePop(){
		adminframe.enable();
	}
	
	public void fail(String t, String m){
		FailFrame fail = new FailFrame(t, m, bldFont);
		fail.errFrame.setVisible(true);
	}
	
	public void startShow(){
		try {
			slideshow = new TwoDSlideShow(nrOfConfValues, this); //this
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showClsd(String m){
		adminframe.setExitShow();
		try{
		if (m.equals(null) == false)
			fail("Error in slideshow", m);
		} catch(NullPointerException e){
			
		}
	}
	
	public void deskClsd(){
		adminframe.exitBtn.doClick();
	}
	
	public void exitShow(){
		try{
			slideshow.close();
		}catch(NullPointerException e){
			fail("Error", "The slideshow couldn't be closed");
		}
	}

	// Main
	public static void main(String[] args) {

		new DesktopApplication();
		
	}
	
}
