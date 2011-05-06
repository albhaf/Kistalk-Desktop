import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class FrameListener implements WindowListener{
	
	private TwoDSlideShow slideshow;
	private DesktopApplication deskApp;
	
	public FrameListener(DesktopApplication tmpdesk){
		deskApp = tmpdesk;
	}
	
	public FrameListener(TwoDSlideShow tmp, DesktopApplication tmpdesk){
		deskApp = tmpdesk;
		slideshow = tmp;
	}
	
	public FrameListener(){
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		JFrame frame = (JFrame)( arg0.getSource());
		String title = frame.getTitle();
		if(title.equalsIgnoreCase("ShowImage.java")){
			ShowImageClosing();
		}
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		JFrame frame = (JFrame)( arg0.getSource());
		String title = frame.getTitle();
		if(title.equalsIgnoreCase("KisTalk Slideshow Settings")){
			deskApp.deskClsd();
		}
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {

	}

	@Override
	public void windowIconified(WindowEvent arg0) {

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
	}
	
	private void ShowImageClosing(){
		deskApp.showClsd("");
		slideshow.t.stop();
		DirectoryExterminator de = new DirectoryExterminator("Images");
		de.exterminate();
	}
}
