import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class TwoDSlideShowView  {
	JPanel panel;
	JFrame frame;
	
	protected TwoDSlideShowView() {
	}

	protected void createFrame(ShowImage slideShowHandler, Rectangle monitor,TwoDSlideShow tmp, DesktopApplication tmpDesk) {

		frame = new JFrame("ShowImage.java");
		FrameListener wl = new FrameListener(tmp, tmpDesk);
		
		panel = slideShowHandler;
		frame.getContentPane().add(panel);
		frame.addWindowListener(wl);
		frame.setUndecorated(true);
		frame.setLocation(monitor.x, monitor.y);
		frame.setSize(monitor.width, monitor.height);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void terminate(){
		frame.dispose();
	}
	
	
}
