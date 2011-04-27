import java.awt.*;
import javax.swing.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Image;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

@SuppressWarnings("serial")
public class SlideShow extends JFrame implements ActionListener {

	Timer t;

	byte screenIndex;

	int nrOfPicsServer;
	int nrOfPicsPub;
	final int nrOfConfigLines = 10;
	String[] fileFormats = new String[4];
	JLabel labelHeader;
	JLabel labelContent;
	JLabel labelText;
	ImageIcon[] iconArrayServer;
	ImageIcon[] iconArrayPub;
	URL[] urlArray;
	int currentPicture = 0;
	int currentPubPicture = 0;
	int nrOfComments = 0;

	XMLreader xmlreader;
	String xmlPath;
	List<ImageXML> imgXMLList;

	Rectangle monitor = new Rectangle();

	public SlideShow() {
		readConfig();
		getScreenResolution();
		createFrame(iconArrayServer);
		firstPicture();
		t.start();
	}

	// Build the frame (Slideshow)
	public void createFrame(ImageIcon[] iconArray) {

		// Skapa objekt
		JFrame frame = new JFrame();
		JPanel panelBG = new JPanel();
		labelHeader = new JLabel();
		labelContent = new JLabel();
		labelText = new JLabel();

		// Framesettings
		frame.setSize(monitor.width, monitor.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("KisTalk Slideshow");
		frame.setLocation(monitor.x, monitor.y);
		frame.setUndecorated(true);

		// panelBG Settings
		panelBG.setBackground(Color.BLACK);
		panelBG.setLayout(new BoxLayout(panelBG, BoxLayout.Y_AXIS));

		// Rubrik Settings
		labelHeader.setText("Hej! och Välkommen till KisTalk!");
		labelHeader.setFont(new Font("Cambria", Font.BOLD, 16));
		labelHeader.setForeground(Color.white);
		labelHeader.setOpaque(false);
		labelHeader.setAlignmentX(CENTER_ALIGNMENT);
		panelBG.add(labelHeader);

		labelContent.setIcon(iconArray[0]);
		labelContent.setForeground(Color.white);
		labelContent.setOpaque(false);
		labelContent.setAlignmentX(CENTER_ALIGNMENT);
		panelBG.add(labelContent);

		// Text Settings
		labelText.setText("Bildspelet laddas...");
		labelText.setForeground(Color.white);
		labelText.setOpaque(false);
		labelText.setAlignmentX(CENTER_ALIGNMENT);
		panelBG.add(labelText);

		frame.add(panelBG);

		frame.setVisible(true);

		pack();

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
		String[] values = new String[nrOfConfigLines];
		ConfigHandler reader = new ConfigHandler();

		try {
			values = reader.processLineByLine();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Hantera inkommande data
		nrOfPicsServer = Integer.valueOf(values[0]);
		iconArrayServer = new ImageIcon[nrOfPicsServer];
		urlArray = new URL[nrOfPicsServer];
		t = new Timer(Integer.valueOf(values[1]), this);
		fileFormats = values[2].split(" ");
		screenIndex = Byte.valueOf(values[3]);
		xmlPath = values[4];
		nrOfComments = Integer.valueOf(values[5]);

	}

	private void firstPicture(){
		xmlreader = new XMLreader(xmlPath);
		imgXMLList = xmlreader.getImagesInfo();
		for(int i = 0; i<nrOfPicsServer ; i++){
			urlArray[i]=imgXMLList.get(i).getLink();
		}
		if(urlArray[0].equals(null)==false){
				try {
					ImageIcon tmpImg =  new ImageIcon(ImageIO.read(urlArray[0]).getScaledInstance(200, -1, Image.SCALE_SMOOTH));
					 iconArrayServer[0] =   tmpImg;
						tmpImg=null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		
//		pubPics();
		printStuff();
	}

	private void updatePicture(int pictureNr){

		//Image tmpImg = null;
		if( pictureNr == nrOfPicsServer - 1){
			imgXMLList = xmlreader.getImagesInfo();

		}
		if(urlArray.equals(imgXMLList.get(pictureNr).getLink())==false && imgXMLList.get(pictureNr).getLink()!=null){
			urlArray[pictureNr]=imgXMLList.get(pictureNr).getLink();
			try {
				ImageIcon tmp = new ImageIcon( ImageIO.read(urlArray[pictureNr]).getScaledInstance(200, -1, Image.SCALE_SMOOTH));
				iconArrayServer [pictureNr] = tmp;
				tmp=null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		printStuff();
	}

//	private void pubPics() {
//		nrOfPicsPub = 2;
//		String[] imgAdresses = new String[nrOfPicsPub];
//		iconArrayPub = new ImageIcon[nrOfPicsPub];
//		Image imgIndex = null;
//		
//		currentPubPicture = 0;
//
//		// Hämta länkar till de valda bilderna - Lägg in dessa rader direkt i for-loopen
//		imgAdresses[0] = "C:\\Users\\Public\\Pictures\\Sample Pictures\\Öken.jpg";
//		imgAdresses[1] = "C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg";
//
//		for (int i = 0; i < nrOfPicsPub; i++) {
//			File fileName = new File(imgAdresses[i]);
//			try {
//				imgIndex = ImageIO.read(fileName);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			fileName = null;
//			iconArrayPub[i] = new ImageIcon(imgIndex.getScaledInstance(200, -1, Image.SCALE_SMOOTH));
//		}
//		imgAdresses = null;
//	}

	private void printStuff() {
		StringBuffer sbHeader = new StringBuffer();
		StringBuffer sbText = new StringBuffer();

		if (true == true) { //currentPicture % 2 == 0
			if (imgXMLList.get(currentPicture).getLink() != null) {
				labelContent.setIcon(iconArrayServer[currentPicture]);
			} else {
				labelContent.setIcon(null);
				labelContent.setText(imgXMLList.get(currentPicture)
						.getImageText());
			}
			labelContent.setText(imgXMLList.get(currentPicture).getImageText());

			sbHeader.append(imgXMLList.get(currentPicture).getUser() + ": ");

			List<CommentXML> comments = imgXMLList.get(currentPicture)
					.getComments();
			if (nrOfComments < comments.size()) {
				for (int i = 0; i < nrOfComments; i++) {
					sbText.append("        |          ");
					sbText.append(comments.get(comments.size() - nrOfComments
							+ i));
					sbText.append("        |          ");
				}
			} else {
				for (int i = 0; i < comments.size(); i++) {
					sbText.append("        |          ");
					sbText.append(comments.get(i));
					sbText.append("        |          ");
				}
			}

			labelHeader.setText(sbHeader.toString());
			labelText.setText(sbText.toString());
		} else {
//			if (currentPubPicture >= nrOfPicsPub || currentPicture == 1)
//				pubPics();
//
//			labelHeader.setText("Puben Presenterar: ");
//			labelContent.setIcon(iconArrayPub[0]);
//			labelText.setText("Hejdå!");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == t) {
			updatePicture(currentPicture);
			currentPicture = currentPicture + 1;
			if (currentPicture >= nrOfPicsServer) {
				currentPicture = 0;
			}
		}

	}

	public static void main(String args[]) {
		new SlideShow();

	}
}
