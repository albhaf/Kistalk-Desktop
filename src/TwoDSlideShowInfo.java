import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class TwoDSlideShowInfo {
	
	public TwoDSlideShowInfo(){
		
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
	
	protected int readConfig(TwoDSlideShow show) throws FileNotFoundException {

		ConfigHandler reader = new ConfigHandler();
		String[] values = reader.getAllLines();

		try {
			values = reader.processLineByLine();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Hantera inkommande data
		show.nrOfPicsServer = Integer.valueOf(values[0]);
		show.serverImgs = new Image[show.nrOfPicsServer];
		show.iconArrayServer = new ImageIcon[show.nrOfPicsServer];
		show.urlArray = new URL[show.nrOfPicsServer];
		show.fileFormats = values[4].split(" ");
		show.screenIndex = Byte.valueOf(values[5]);
		show.xmlPath = values[6];
		show.nrOfComments = Integer.valueOf(values[7]);
		return Integer.valueOf(values[2]);
	}

	protected void setLinks(TwoDSlideShow show) {
		show.imgXMLList = show.xmlreader.getImagesInfo();
		for (int i = 0; i < show.nrOfPicsServer; i++) {
			show.urlArray[i] = show.imgXMLList.get(i).getLink();
		}
	}
	
	protected void setPicture(TwoDSlideShow show) {
		try {
			show.serverImgs[show.currentPicture] = ImageIO.read(show.urlArray[show.currentPicture]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		show.currentPicture++;
	}
}