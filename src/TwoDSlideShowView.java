import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class TwoDSlideShowView {
	JPanel panel;

	protected TwoDSlideShowView() {
	}

	protected void createFrame(ShowImage slideShowHandler,
			Rectangle monitor) {
		JFrame frame = new JFrame("ShowImage.java");
		
		panel = slideShowHandler;
		frame.getContentPane().add(panel);

		frame.setUndecorated(true);
		frame.setSize(monitor.width, monitor.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	
}
