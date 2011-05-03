import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.Timer;

@SuppressWarnings("serial")
public class TwoDSlideShow extends Panel implements ActionListener {

	Timer t;

	byte screenIndex;
	final int nrOfConfigLines = 10;

	ShowImage slideShowHandler;
	ImportPubSlides pubSlides;

	Rectangle monitor = new Rectangle();
	TwoDSlideShowView view;
	TwoDSlideShowInfo info;
	private boolean pubSlide;

 	public TwoDSlideShow() throws IOException {
 		pubSlide = true;
		view = new TwoDSlideShowView();
		info = new TwoDSlideShowInfo();
	//	pubSlides = new ImportPubSlides("C:\\Users\\Ludvig\\Documents\\asd.ppt");
//		pubSlides = new ImportPubSlides("/home/zandra/Documents/testSlide.ppt");
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
		info.readConfig(screenIndex);
		t = new Timer(10, this);
	}

	private void firstPicture() {
		info.setLinks();
		info.setPictures();
	}

	private void updatePicture() throws IOException {
		info.updatePicture();
		if(pubSlide && pubSlides.countFiles() > 0){
			slideShowHandler.setNewPicture(pubSlides.getImage(), info.getUser(), info.getImageText(), info.getImageComments());
			pubSlide = false;
		} else {
			slideShowHandler.setNewPicture(info.getImage(), info.getUser(), info.getImageText(), info.getImageComments());
			pubSlide = true;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == t) {

			slideShowHandler.MoveObjects();
			if (slideShowHandler.getSlideImageX() > monitor.width) {
				try {
					updatePicture();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	}

	public static void main(String args[]) throws IOException {
		new TwoDSlideShow();
	}
}
