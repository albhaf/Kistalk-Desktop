import java.awt.*;

import javax.swing.*;

import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;

@SuppressWarnings("serial")
public class TwoDSlideShow extends Panel implements ActionListener {

	Timer t;

	byte screenIndex;

	int nrOfPicsServer;
	int nrOfPicsPub;
	final int nrOfConfigLines = 10;
	String[] fileFormats = new String[4];
	ImageIcon[] iconArrayServer;
	ImageIcon[] iconArrayPub;
	Image[] serverImgs;
	URL[] urlArray;
	int currentPicture = 0;
	int currentPubPicture = 0;
	int nrOfComments = 0;
	int timeStill =200;

	Graphics2D g2d;
	JPanel panel;
	XMLreader xmlreader;
	String xmlPath;
	List<ImageXML> imgXMLList;
	ShowImage slideShowHandler;

	int timestill = 100;

	Rectangle monitor = new Rectangle();

	public TwoDSlideShow() {
		readConfig();
		getScreenResolution();
		firstPicture();
		createFrame();
		t.start();
	}

	// Build the frame (Slideshow)
	public void createFrame() {


		JFrame frame = new JFrame("ShowImage.java");
		slideShowHandler = new ShowImage((BufferedImage) serverImgs[0],imgXMLList.get(0).getUser(),imgXMLList.get(0).getImageText(), monitor, timeStill, imgXMLList.get(0).getComments());
		panel = slideShowHandler;
		frame.getContentPane().add(panel);

		frame.setUndecorated(true);
		frame.setSize(monitor.width, monitor.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	// Get the size of the monitor
	private void getScreenResolution() {
		GraphicsEnvironment gfxEnviro = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice[] gfxScreenDev = gfxEnviro.getScreenDevices();

		// Get the right screen
		GraphicsConfiguration[] gc = gfxScreenDev[screenIndex]
				.getConfigurations();

		// Get size and position of the screen
		monitor = gc[0].getBounds();

	}

	private void readConfig() {
		String[] values = new String[6];
		ConfigHandler reader = new ConfigHandler();

		try {
			values = reader.processLineByLine();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Hantera inkommande data
		nrOfPicsServer = Integer.valueOf(values[0]);
		serverImgs = new Image[nrOfPicsServer];
		iconArrayServer = new ImageIcon[nrOfPicsServer];
		urlArray = new URL[nrOfPicsServer];
		t = new Timer(Integer.valueOf(values[1]), this);
		fileFormats = values[2].split(" ");
		screenIndex = Byte.valueOf(values[3]);
		xmlPath = values[4];
		nrOfComments = Integer.valueOf(values[5]);

	}

	private void firstPicture() {
		xmlreader = new XMLreader(xmlPath);
		imgXMLList = xmlreader.getImagesInfo();
		for (int i = 0; i < nrOfPicsServer; i++) {
			urlArray[i] = imgXMLList.get(i).getLink();
		}
		try {
			serverImgs[currentPicture] = ImageIO.read(urlArray[currentPicture]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		currentPicture = currentPicture + 1;

	}

	private void updatePicture() {

		if(currentPicture>=nrOfPicsServer){
			currentPicture=0;
		}
		if (currentPicture == nrOfPicsServer - 1) {
			imgXMLList = xmlreader.getImagesInfo();

		}
		if (urlArray[currentPicture].equals(imgXMLList.get(currentPicture).getLink()) == false || serverImgs[currentPicture]==null) {
			urlArray[currentPicture] = imgXMLList.get(currentPicture).getLink();
			try {
				serverImgs[currentPicture] = ImageIO
						.read(urlArray[currentPicture]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		slideShowHandler.UpdatePicture((BufferedImage) serverImgs[currentPicture],imgXMLList.get(currentPicture).getUser(),imgXMLList.get(currentPicture).getImageText(), imgXMLList.get(currentPicture).getComments());
currentPicture = currentPicture + 1;

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

	/*public static void copyPptPics() throws IOException{
	        
	                ZipFile zf = new ZipFile("C:\\Users\\Ludvig\\Documents\\asd.odp");
	                Enumeration<? extends ZipEntry> files = zf.entries();

	                while (files.hasMoreElements()) {
	                  ZipEntry ze = files.nextElement();

	                  System.out.println("Decompressing " + ze.getName());
	                  System.out.println("  Compressed Size: " + ze.getCompressedSize()
	                      + "  Expanded Size: " + ze.getSize() + "\n");
	                     if(ze.isDirectory()==false){
	                  BufferedInputStream fin = new BufferedInputStream(zf.getInputStream(ze));
	                  BufferedOutputStream fout = new BufferedOutputStream(

	                		  new FileOutputStream(ze.getName()));

	                  int i;
	                  do {
	                    i = fin.read();
	                    if (i != -1)
	                      fout.write(i);
	                  } while (i != -1);

	                  fout.close();
	                  fin.close();
	                     }
	                }
	                zf.close();
	              }*/
		 
	
	public static void main(String args[]) {


//		PubXMLReader blah = new PubXMLReader();
//		blah.parseDocument();
		new TwoDSlideShow();

	}
}
