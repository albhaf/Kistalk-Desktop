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

public class TwoDSlideShowInfo{
	XMLreader xmlreader;
	String xmlPath;
	List<ImageXML> imgXMLList;
	String[] fileFormats = new String[4];
	ImageIcon[] iconArrayServer;
	ImageIcon[] iconArrayPub;
	Image[] serverImgs;
	URL[] urlArray;
	
	int currentPicture = 0;
	int currentPubPicture = 0;
	int nrOfComments = 0;
	int nrOfPicsServer;
	int timeStill;
	
	
	public TwoDSlideShowInfo() {
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
	
	protected void setPicture() {
		if (currentPicture >= nrOfPicsServer || currentPicture == 0) {			
			setLinks();			
			currentPicture = 0;			
		}
		try {
			serverImgs[currentPicture] = ImageIO.read(urlArray[currentPicture]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		currentPicture++;
	}
	
	protected ShowImage createShowImage(Rectangle monitor){
		return new ShowImage((BufferedImage) serverImgs[0],
				imgXMLList.get(0).getUser(),imgXMLList.get(0).getImageText(),
				monitor, timeStill, imgXMLList.get(0).getComments());	
	}
	
	protected void updatePicture(ShowImage slideShowHandler){
		slideShowHandler.UpdatePicture(
				(BufferedImage) serverImgs[currentPicture - 1],imgXMLList
						.get(currentPicture - 1).getUser(),imgXMLList.get(
						currentPicture - 1).getImageText(),imgXMLList.get(
						currentPicture - 1).getComments());

	}
}