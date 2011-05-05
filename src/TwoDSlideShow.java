import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;
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
	private boolean imageSlide;

 	public TwoDSlideShow() throws IOException {
 		imageSlide = false;
		view = new TwoDSlideShowView();
		info = new TwoDSlideShowInfo();
		pubSlides = new ImportPubSlides("asd.ppt");
		readConfig();
		getScreenResolution();
		firstPicture();
		createFrame();
		updatePicture();
		updatePicture();
		t.start();
	}
 	
 	public void close(){
 		t.stop();
 		view.terminate();

 	}

	// Build the frame (Slideshow)
	private void createFrame() throws IOException {
		slideShowHandler =  new ShowImage(monitor, info.getTimeStill(), info.getFadingSpeed());
		view.createFrame(slideShowHandler, monitor,this);
	}

	// Get the size of the monitor
	private void getScreenResolution() {
		monitor = info.getScreenSize(screenIndex);
	}

	private void readConfig() throws FileNotFoundException {
		screenIndex = info.readConfig();
		t = new Timer(10, this);
	}

	private void firstPicture() throws IOException {
		info.setLinks();
		info.setPictures();
	}

	private void updatePicture() throws IOException {
		if(!imageSlide && pubSlides.getNrOfSlides() > 0){
			try{
			slideShowHandler.setNewSlide(pubSlides.getImage());
			}catch(IOException e){
				JOptionPane.showMessageDialog(null, "Something went wrong. Couldn't open the powerpoint images.");
				close();
			}
			imageSlide = true;
		} else {
			info.updatePicture();
			imageSlide = false;
			slideShowHandler.setNewPicture(info.getImage(), info.getUser(), info.getImageText(), info.getImageComments());		
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
					e1.printStackTrace();
				}
			}
		}

	}

	public static void main(String args[]) throws IOException {
		new TwoDSlideShow();
	}
}
