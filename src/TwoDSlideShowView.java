import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class TwoDSlideShowView  {
	JPanel panel;
	JFrame frame;

	protected TwoDSlideShowView() {
	}

	protected void createFrame(ShowImage slideShowHandler, Rectangle monitor) {
<<<<<<< HEAD
		JFrame frame = new JFrame("ShowImage.java");
		
		panel = slideShowHandler;
		frame.getContentPane().add(panel);

		FrameListener wl = new FrameListener();
=======
		frame = new JFrame("ShowImage.java");
		FrameListener wl = new FrameListener();
		
		panel = slideShowHandler;
		frame.getContentPane().add(panel);
>>>>>>> 67186b93708d82c1d697b6649703775ce5476098
		frame.addWindowListener(wl);
		frame.setUndecorated(true);
		frame.setSize(monitor.width, monitor.height);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void terminate(){
		frame.dispose();
	}
	
	
}
