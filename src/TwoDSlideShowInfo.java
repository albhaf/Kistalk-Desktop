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

	int nrOfConfValues;
	int currentPicture = 0;
	int currentPubPicture = 0;
	int nrOfComments = 0;
	int nrOfPics;
	int timeStill;
	int fadingSpeed;

	public TwoDSlideShowInfo(int tmpConfValues) {
		nrOfConfValues = tmpConfValues;
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

	protected String[] readConfig() throws FileNotFoundException {

		ConfigHandler reader = new ConfigHandler(nrOfConfValues);
		String[] values = new String[9];
		String[] returner = new String[2];
		
		try {
			values = reader.processLineByLine();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Hantera inkommande data
		nrOfPics = Integer.valueOf(values[0]);
		iconArrayServer = new ImageIcon[nrOfPics];
		urlArray = new URL[nrOfPics];
		serverImgs = new Image[nrOfPics];
		timeStill = Integer.valueOf(values[2]);
		fadingSpeed = Integer.valueOf(values[3]);
		fileFormats = values[4].split(" ");
		returner[0] =values[5];
		xmlPath = values[6];
		nrOfComments = Integer.valueOf(values[7]);
		returner[1]=(values[8]);


		return returner;
	}

	private void readNext(int page){
		xmlreader = new XMLreader(xmlPath + "?username=znorman&token=vqlcotvzuu&page=" + page + "&per_page="+ (nrOfPics));
		imgXMLList = xmlreader.getImagesInfo();
	}
	
	protected void setLinks() throws IOException {
		int page = 1;
		readNext(page);
		for (int i = 0, j = 0; i < nrOfPics; i++, j++) {
			if(j > page*9)
				readNext(page++);
			try {
				urlArray[i] = imgXMLList.get(j%10).getLink();
			} catch (Exception e) {
				i--;
			}
		}
	}
	protected void setLinks(int page) throws IOException {
		readNext(page);
		for (int i = 0, j = 0; i < nrOfPics; i++, j++) {
			if(j > page*9)
				readNext(page++);
			try {
				urlArray[i] = imgXMLList.get(j%10).getLink();
			} catch (Exception e) {
				i--;
			}
		}
	}

	protected void setPictures() {
		int i =0;
		int page=1;
		int j =0;
		do {
			try {
				serverImgs[i] = ImageIO.read(urlArray[j]);
				
				i=i+1;
				j=j+1;
			} catch (IOException e) {
					j=j+1;
			}
			if(nrOfPics <= j && i < nrOfPics){
				try {
					setLinks(page=page+1);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				j=0;
			}
		}while(i<nrOfPics);
	}

	protected ShowImage createShowImage(Rectangle monitor) throws IOException {
		return new ShowImage(monitor, timeStill, fadingSpeed);
	}

	protected void updatePicture() throws IOException {		
		if (currentPicture >= nrOfPics-1) {
			setLinks();
			if(urlArray[0] != imgXMLList.get(0).getLink())
				setPictures();
			currentPicture = -1;
		}

	}
	
	protected BufferedImage getImage(){
		currentPicture++;
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
	
	protected int getTimeStill(){
		return timeStill;
	}
	
	public int getFadingSpeed(){
		return fadingSpeed;
	}
	
}