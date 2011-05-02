import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.Timer;

@SuppressWarnings("serial")
public class TwoDSlideShow extends Panel implements ActionListener {

	Timer t;

	byte screenIndex;
	final int nrOfConfigLines = 10;

	int timeStill = 200;

	ShowImage slideShowHandler;

	Rectangle monitor = new Rectangle();
	TwoDSlideShowView view;
	TwoDSlideShowInfo info;

	public TwoDSlideShow() throws FileNotFoundException {
		view = new TwoDSlideShowView();
		info = new TwoDSlideShowInfo();
		readConfig();
		getScreenResolution();
		firstPicture();
		createFrame();
		updatePicture();
		t.start();
	}

	// Build the frame (Slideshow)
	public void createFrame() {
		slideShowHandler = info.createShowImage(monitor);
		view.createFrame(slideShowHandler, monitor);
	}

	// Get the size of the monitor
	private void getScreenResolution() {
		monitor = info.getScreenSize(screenIndex);
	}

	private void readConfig() throws FileNotFoundException {
		info.readConfig(screenIndex);
		t = new Timer(10, this);
	}

	private void firstPicture() {
		info.setLinks();
		info.setPictures();
	}

	private void updatePicture() {
		info.updatePicture(slideShowHandler);
		// resets the image comments
		
		slideShowHandler.setComments(info.getImageComments());

		// Bildtexten
		slideShowHandler.setImageText(info.getImageText());
				
		// Image user
		slideShowHandler.setUserText(info.getUser());
		
		// Bilden
		slideShowHandler.setImage(info.getImage());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == t) {

			slideShowHandler.MoveObjects();
			if (slideShowHandler.getSlideImageX() > monitor.width) {
				updatePicture();
			}
		}

	}

	public static void main(String args[]) throws FileNotFoundException {
		new TwoDSlideShow();
	}
}
