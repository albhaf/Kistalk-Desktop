import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class TwoDSlideShowInfo {
	private XMLreader xmlreader;
	private String xmlPath;
	private ImageXMLQueue imgXmlQueue;
	private String[] fileFormats;
	private URL[] urlArray;
	private ImageQueue imgQueue;

	private int nrOfConfValues;
	private int currentPicture = 0;
	private int nrOfComments = 0;
	private int nrOfPics;
	private int timeStill;
	private int fadingSpeed;

	public TwoDSlideShowInfo(int tmpConfValues) {
		nrOfConfValues = tmpConfValues;
		fileFormats = new String[4];
	}

	protected Rectangle getScreenSize(int screenIndex, DesktopApplication deskApp) {
		
		try{
		GraphicsEnvironment gfxEnviro = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gfxScreenDev = gfxEnviro.getScreenDevices();

		// Get the right(screenIndex) screen
		GraphicsConfiguration[] gc = gfxScreenDev[screenIndex]
				.getConfigurations();

		// Get size and position of the screen
		return gc[0].getBounds();
		}catch(ArrayIndexOutOfBoundsException e){
			deskApp.showClsd("Invalid screen choice.");
		}
		return null;

	}

	protected String[] readConfig() throws FileNotFoundException {

		ConfigHandler reader = new ConfigHandler(nrOfConfValues);
		String[] values = new String[9];
		String[] returner = new String[2];
		
		try {
			values = reader.processLineByLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Hantera inkommande data
		nrOfPics = Integer.valueOf(values[0]);
		urlArray = new URL[nrOfPics];
		imgQueue = new ImageQueue(nrOfPics);
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
		imgXmlQueue = xmlreader.getImagesInfo();
	}
	
	protected void setLinks() throws IOException {
		int page = 1;
		readNext(page);
		for (int i = 0, j = 0; i < nrOfPics; i++, j++) {
			if(j > page*9)
				readNext(page++);
			try {
				urlArray[i] = imgXmlQueue.getFirst().getLink();
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
				urlArray[i] = imgXmlQueue.getFirst().getLink();
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
				imgQueue.addLast(ImageIO.read(urlArray[j])); 
				i=i+1;
				j=j+1;
			} catch (IOException e) {
					j=j+1;
			}
			if(nrOfPics <= j && i < nrOfPics){
				try {
					setLinks(page=page+1);
				} catch (IOException e) {
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
			if(urlArray[0] != imgXmlQueue.peek().getLink());
				setPictures();
			currentPicture = -1;
		}

	}
	
	protected BufferedImage getImage(){
		currentPicture++;
		return (BufferedImage) imgQueue.getFirst();
	}
	
	protected ImageXML getImgInfo(){
		return imgXmlQueue.getFirst();
	}
	
	protected int getTimeStill(){
		return timeStill;
	}
	
	public int getFadingSpeed(){
		return fadingSpeed;
	}
	
}