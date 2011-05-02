import java.io.FileNotFoundException;


public class StartnStop extends AdminFrame {
	
	//	Exit slideshow or, if slideshow's off, KisTalk
	public void exit() {
//		if (twoDSlideShow() == true){	//Om bildspelet är igång *VIKTIGT!*
//			statusLbl.setText("The slideshow is dead...");
//			twDSlideshow.dispose();
//			startBtn.setEnabled(true);
//			exitBtn.setText("Quit KisTalk");
//		}else{
//			statusLbl.setText("Goodbye!");
//			adminFrame.dispose();
//		}
		
	}
	
	//	Starts the Slideshow
	public void start() {
		config.setValues(confValues);
		
		// Start Show
		try {
			new TwoDSlideShow();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		exitBtn.setText("Quit SlideShow");
		startBtn.setEnabled(false);
		
	}
}
