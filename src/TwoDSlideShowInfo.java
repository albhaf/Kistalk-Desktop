import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class TwoDSlideShowInfo {
	XMLreader xmlreader;
	String xmlPath;
	List<ImageXML> imgXMLList;
	String[] fileFormats;
	ImageIcon[] iconArrayServer;
	ImageIcon[] iconArrayPub;
	Image[] serverImgs;
	URL[] urlArray;

	int currentPicture = -1;
	int currentPubPicture = 0;
	int nrOfComments = 0;
	int nrOfPicsServer;
	int timeStill;

	public TwoDSlideShowInfo() {
		fileFormats = new String[4];
	}

	protected Rectangle getScreenSize(int screenIndex) {
		GraphicsEnvironment gfxEnviro = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice[] gfxScreenDev = gfxEnviro.getScreenDevices();

		// Get the right screen
		GraphicsConfiguration[] gc = gfxScreenDev[screenIndex]
				.getConfigurations();

		// Get size and position of the screen
		return gc[0].getBounds();
	}

	protected void readConfig(int screenIndex) throws FileNotFoundException {

		ConfigHandler reader = new ConfigHandler();
		String[] values = new String[9];

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
		fileFormats = values[4].split(" ");
		screenIndex = Byte.valueOf(values[5]);
		xmlPath = values[6];
		nrOfComments = Integer.valueOf(values[7]);
		timeStill = Integer.valueOf(values[2]);
	}

	protected void setLinks() {
		xmlreader = new XMLreader(xmlPath);
		imgXMLList = xmlreader.getImagesInfo();
		for (int i = 0; i < nrOfPicsServer; i++) {
			urlArray[i] = imgXMLList.get(i).getLink();
		}
	}

	protected void setPictures() {
		for (int i = 0; i < nrOfPicsServer; i++) {
			try {
				serverImgs[i] = ImageIO
						.read(urlArray[i]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected ShowImage createShowImage(Rectangle monitor) {
		return new ShowImage(monitor, timeStill);
	}

	protected void updatePicture(ShowImage slideShowHandler) {
		currentPicture++;
		if (currentPicture >= nrOfPicsServer) {
			setLinks();
			if(urlArray[0] != imgXMLList.get(0).getLink())
				setPictures();
			currentPicture = 0;
		}
		slideShowHandler.resetPicture();
	}
	
	protected BufferedImage getImage(){
		return (BufferedImage) serverImgs[currentPicture];
	}
	
	protected List<CommentXML> getImageComments(){
		return imgXMLList.get(currentPicture).getComments();
	}
	
	protected String getImageText(){
		return imgXMLList.get(currentPicture).getImageText();
	}
	
	protected String getUser(){
		return imgXMLList.get(currentPicture).getUser();
	}
	
}