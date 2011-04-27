import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class imageurl {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Rectangle monitor = new Rectangle();

		// Get second screen details and save as Rectangle monitor
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
		// gs[1] gets the *second* screen. gs[0] would get the primary screen
		GraphicsDevice gd = gs[0];
		GraphicsConfiguration[] gc = gd.getConfigurations();
		monitor = gc[0].getBounds();

		Image image[] = new Image[10];

		// Read from a URL
		URL url[] = new URL[10];
		try {
			url[0] = new URL(
					"http://www.ltu.se/polopoly_fs/1.10778!johansson%20hans.jpg");
			url[1] = new URL(
					"http://www.psy.gu.se/digitalAssets/1287/1287070_HansS.jpg");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			image[0] = ImageIO.read(url[0]);
			image[1] = ImageIO.read(url[1]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// int type = image.getType();
		// BufferedImage sizedimage = new BufferedImage(500, 500,type );
		/*
		 * try { image = Toolkit.getDefaultToolkit().getImage( new
		 * java.net.URL("http://www.ltu.se/polopoly_fs/1.10778!johansson%20hans.jpg"
		 * ) ); } catch (MalformedURLException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */
		image[0] = image[0].getScaledInstance(200, -1, Image.SCALE_SMOOTH);
		image[1] = image[1].getScaledInstance(200, -1, Image.SCALE_SMOOTH);
		/*
		 * Graphics2D g = sizedimage.createGraphics(); g.drawImage(image, 0, 0,
		 * 500, 500, BufferedImage.TYPE_INT_RGB); g.dispose();
		 */

		JFrame frame = new JFrame();
		JLabel label = new JLabel(new ImageIcon(image[0]));
		ImageIcon img = new ImageIcon(image[1]);

		label.setSize(10, 10);
		frame.getContentPane().add(label, BorderLayout.CENTER);

		frame.setLocation(monitor.x, monitor.y);
		frame.setUndecorated(true);
		frame.setSize(monitor.width, monitor.height);
		// frame.pack();
		frame.setVisible(true);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		label.setIcon(img);
		label.imageUpdate(image[1], Image.SCALE_SMOOTH, 0, 0, 100, 100);

	}

}
