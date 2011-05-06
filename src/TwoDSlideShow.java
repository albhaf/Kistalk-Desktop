import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.IIOException;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class TwoDSlideShow extends Panel implements ActionListener {

	/**
	 * 
	 */

	private static final long serialVersionUID = 5789717155006186682L;


	Timer t;
	
	int screenIndex;
	public int nrOfConfValues;

	private ShowImage slideShowHandler;
	private ImportPubSlides pubSlides;

	private Rectangle monitor = new Rectangle();
	private TwoDSlideShowView view;
	private TwoDSlideShowInfo info;
	private DesktopApplication desktopApp; //For popupframe and maybe something else??
	private boolean imageSlide;

 	public TwoDSlideShow(int tmpConfValues, DesktopApplication tmpDesk) throws IOException {
 		desktopApp = tmpDesk;
 		nrOfConfValues = tmpConfValues;
 		imageSlide = false;
		view = new TwoDSlideShowView();
		info = new TwoDSlideShowInfo(nrOfConfValues);
		pubSlides = new ImportPubSlides(readConfig(), this);
		getScreenResolution();
		firstPicture();
		createFrame();
		updatePicture();
		updatePicture();
		t.start();
	}
 	
 	public TwoDSlideShow() throws IOException {
 		nrOfConfValues = 11;
 		imageSlide = false;
		view = new TwoDSlideShowView();
		info = new TwoDSlideShowInfo(nrOfConfValues);
		pubSlides = new ImportPubSlides(readConfig(), this);
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

	private String readConfig() throws FileNotFoundException {
		String[] tmpConf =info.readConfig();
		screenIndex = Integer.valueOf(tmpConf[0]);
		t = new Timer(10, this);
		return tmpConf[1];
	}

	private void firstPicture() throws IOException {
		info.setLinks();
		info.setPictures();
	}

	private void updatePicture() throws IOException {
		if(!imageSlide && pubSlides.getNrOfSlides() > 0){
			try{
				slideShowHandler.setNewSlide(pubSlides.getImage());
			}catch(IIOException e){
				close();
				JOptionPane.showMessageDialog(null, "Something went wrong. Couldn't open the powerpoint images. They were deleted during runtime!");
			}
			imageSlide = true;
		} else {
			info.updatePicture();
			imageSlide = false;
			slideShowHandler.setNewPicture(info.getImage(),info.getImgInfo()/*info.getImage(), info.getUser(), info.getImageText(), info.getImageComments()*/);		
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

//	public static void main(String args[]) throws IOException {
//		new TwoDSlideShow();
//	}
}
