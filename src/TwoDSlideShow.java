import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class TwoDSlideShow extends Panel implements ActionListener {

	Timer t;

	byte screenIndex;
	final int nrOfConfigLines = 10;

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
		slideShowHandler =  new ShowImage(monitor, info.getTimeStill());
		view.createFrame(slideShowHandler, monitor);
	}

	// Get the size of the monitor
	private void getScreenResolution() {
		monitor = info.getScreenSize(screenIndex);
	}

	private void readConfig() throws FileNotFoundException {
		screenIndex = info.readConfig(screenIndex);
		t = new Timer(10, this);
	}

	private void firstPicture() {
		info.setLinks();
		info.setPictures();
	}

	private void updatePicture() {
		info.updatePicture();
		slideShowHandler.resetPicture();
		slideShowHandler.resetPicture();
		// Kommentarer
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
