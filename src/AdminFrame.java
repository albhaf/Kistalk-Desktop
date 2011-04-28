import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class AdminFrame {
	final int nrOfConfigValues = 9; // Includes regular textlines
	int nrOfPubSlides;
	int slideNr = 0;
	String value;
	Image bgImage;
	String[] confValues = new String[nrOfConfigValues];
	
	JFrame adminFrame;
	JFrame popFrame;
	JPanel bgPanel;
	JPanel fgPanel;
	JLabel headerLbl;
	
	JButton saveSetBtn;
	JButton resetBtn;
	JButton startBtn;
	
	JButton lolButton;
	JButton popButton;
	
	JLabel nrOfImgsLbl;
	JLabel timeLbl;
	JLabel foodSttLbl;
	JLabel pubSttLbl;
	JLabel statusLbl;
	JLabel xmlPubPathLbl;
	JLabel legalFilesLbl;
	JLabel nrOfCommentsLbl;
	JLabel screenLbl;
	JLabel bgLbl;
	
	TextField nrOfImgsTxt;
	TextField timeTxt;
	TextField popText;
	TextField xmlPubPathTxt;
	TextField legalFilesTxt;
	TextField nrOfCommentsTxt;
	
	JRadioButton yFoodRbtn;
	JRadioButton nFoodRbtn;
	JRadioButton yPubRbtn;
	JRadioButton nPubRbtn;
	ButtonGroup foodRbgr;
	ButtonGroup pubRbgr;
	
	JComboBox pubSlidesDDLst;
	JComboBox screenDDLst;
	
	ButtonListener listener = new ButtonListener();
	ConfigHandler handler;

	//	Constructor
	public AdminFrame() {
		readConfig();
		setupPanel();

	}

	//	Setting up the settings frame
	private void setupPanel(){
		
		//	Create new objects
		adminFrame = new JFrame();
		fgPanel = new JPanel();
		bgPanel = new JPanel();
		headerLbl = new JLabel();
		
		saveSetBtn = new JButton();
		resetBtn = new JButton();
		startBtn = new JButton();
		lolButton = new JButton();
		
		nrOfImgsLbl = new JLabel();
		timeLbl= new JLabel();
		foodSttLbl = new JLabel();
		pubSttLbl = new JLabel();
		statusLbl = new JLabel();
		xmlPubPathLbl = new JLabel();
		legalFilesLbl = new JLabel();
		nrOfCommentsLbl = new JLabel();
		screenLbl = new JLabel();
		bgLbl = new JLabel();
		
		nrOfImgsTxt = new TextField();
		timeTxt = new TextField();
		xmlPubPathTxt = new TextField();
		legalFilesTxt = new TextField();
		nrOfCommentsTxt = new TextField();
		
		yFoodRbtn = new JRadioButton();
		nFoodRbtn = new JRadioButton();
		yPubRbtn = new JRadioButton();
		nPubRbtn = new JRadioButton();
		foodRbgr = new ButtonGroup();
		pubRbgr = new ButtonGroup();
		
		pubSlidesDDLst = new JComboBox();
		screenDDLst = new JComboBox();
		
		
		GroupLayout groupLayout = new GroupLayout(fgPanel);
		
		
		//	Frame settings
		adminFrame.setSize(900,600);
		adminFrame.setResizable(false);
		adminFrame.setLocation(200, 50);
		adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		adminFrame.setTitle("KisTalk Slideshow Settings");
		
		//	Panel settings
		ImageIcon icon;
		icon = new ImageIcon("C:\\Users\\Andeers\\Pictures\\bgIcon.png");
		bgLbl.setIcon(icon);
		bgPanel.add(bgLbl);
		
//		paintComponent(g);

		
		fgPanel.setBackground(Color.BLACK);
		fgPanel.setOpaque(false);
		fgPanel.setLayout(groupLayout);
		
		//	Label settings
		headerLbl.setText("Welcome to KisTalk Administration Center!");
		headerLbl.setFont(new Font("Cambria", Font.BOLD, 32));
		headerLbl.setForeground(Color.WHITE);
		
		nrOfImgsLbl.setText("Nr of pics (from KisTalk): ");
		nrOfImgsLbl.setForeground(Color.WHITE);
		
		timeLbl.setText("Time interval (ms): ");
		timeLbl.setForeground(Color.WHITE);
		
		xmlPubPathLbl.setText("OpenOffice Powerpoint: ");
		xmlPubPathLbl.setForeground(Color.WHITE);
		
		legalFilesLbl.setText("Approved file extensions: ");
		legalFilesLbl.setForeground(Color.WHITE);
		
		pubSttLbl.setText("The pub is open");
		pubSttLbl.setForeground(Color.WHITE);
		
		foodSttLbl.setText("Dinner is served");
		foodSttLbl.setForeground(Color.WHITE);
		
		statusLbl.setText("Status: Ready for some action");
		statusLbl.setForeground(Color.WHITE);
		statusLbl.setFont(new Font("Helvetica", Font.ITALIC, 9));
		
		nrOfCommentsLbl.setText("Number of shown comments: ");
		nrOfCommentsLbl.setForeground(Color.WHITE);
		
		screenLbl.setText("Choose screen: ");
		screenLbl.setForeground(Color.WHITE);
		
		//	Text settings
		nrOfImgsTxt.setText(confValues[0]);
		nrOfImgsTxt.setFont(new Font("Algerian", Font.ITALIC, 12));
		
		timeTxt.setText(confValues[2]);
		timeTxt.setFont(new Font("Algerian", Font.ITALIC, 12));
		
		xmlPubPathTxt.setText(confValues[8]);
		xmlPubPathTxt.setFont(new Font("Algerian", Font.ITALIC, 12));
		
		legalFilesTxt.setText(confValues[4]);
		legalFilesTxt.setFont(new Font("Algerian", Font.ITALIC, 12));
		legalFilesTxt.setEnabled(false);
		
		nrOfCommentsTxt.setText(confValues[7]);
		nrOfCommentsTxt.setFont(new Font("Algerian", Font.ITALIC, 12));
		
		//	Button settings
		saveSetBtn.setText("Save settings");
		saveSetBtn.setForeground(Color.WHITE);
		saveSetBtn.setBackground(Color.BLACK);
		saveSetBtn.addActionListener(listener);
		saveSetBtn.setOpaque(false);
		
		resetBtn.setText("Reset settings");
		resetBtn.setForeground(Color.WHITE);
		resetBtn.setBackground(Color.BLACK);
		resetBtn.addActionListener(listener);
		resetBtn.setOpaque(false);
		
		startBtn.setText("Start slideshow");
		startBtn.setForeground(Color.WHITE);
		startBtn.setBackground(Color.BLACK);
		startBtn.addActionListener(listener);
		startBtn.setOpaque(false);
		
		lolButton.setText("popup!");
		lolButton.addActionListener(listener);
		
		//	Radiobuttons settings
		yFoodRbtn.setText("True");
		yFoodRbtn.setBackground(Color.BLACK);
		yFoodRbtn.setForeground(Color.WHITE);
		yFoodRbtn.setOpaque(false);
		yFoodRbtn.addActionListener(listener);
		
		nFoodRbtn.setSelected(true);
		nFoodRbtn.setText("False");
		nFoodRbtn.setBackground(Color.BLACK);
		nFoodRbtn.setForeground(Color.WHITE);
		nFoodRbtn.setOpaque(false);
		nFoodRbtn.addActionListener(listener);
		
		yPubRbtn.setText("True");
		yPubRbtn.setBackground(Color.BLACK);
		yPubRbtn.setForeground(Color.WHITE);
		yPubRbtn.setOpaque(false);
		yPubRbtn.addActionListener(listener);
		
		nPubRbtn.setSelected(true);
		nPubRbtn.setText("False");
		nPubRbtn.setBackground(Color.BLACK);
		nPubRbtn.setForeground(Color.WHITE);
		nPubRbtn.setOpaque(false);
		nPubRbtn.addActionListener(listener);
		
		foodRbgr.add(yFoodRbtn);
		foodRbgr.add(nFoodRbtn);
		
		pubRbgr.add(yPubRbtn);
		pubRbgr.add(nPubRbtn);
		
		//	ComboBox settings
		pubSlidesDDLst.addItem("[Other Slideshow]");
		pubSlidesDDLst.addItem("TMEIT");
		pubSlidesDDLst.addItem("Qmisk");
		pubSlidesDDLst.addItem("ITK");
		pubSlidesDDLst.setOpaque(false);
		pubSlidesDDLst.setFont(new Font("Algerian", Font.ITALIC, 12));
		pubSlidesDDLst.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent e){
						if (e.getStateChange() == ItemEvent.SELECTED)
							statusLbl.setText(e.getItem().toString() + "s Slideshow is choosed");
					}
				}
		);
		
		screenDDLst.addItem("External");
		screenDDLst.addItem("This");
		screenDDLst.setOpaque(false);
		screenDDLst.setFont(new Font("Algerian", Font.ITALIC, 12));
		screenDDLst.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent e){
						if (e.getStateChange() == ItemEvent.SELECTED)
							statusLbl.setText(e.getItem().toString() + " screen it is!");
					}
				}
		);
		
		//	Layout settings & add components
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		
			//	Horisontal
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
			.addComponent(headerLbl)
			.addGap(100)
			.addGroup(groupLayout.createSequentialGroup()
				
			   	.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
			   			
			   			.addGroup(groupLayout.createSequentialGroup()
						   	.addComponent(nrOfImgsLbl)
						   	.addComponent(nrOfImgsTxt, 50, 50, 50)
						)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(timeLbl)
							.addComponent(timeTxt, 100, 100, 100)
						)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(nrOfCommentsLbl)
							.addComponent(nrOfCommentsTxt, 100, 100, 100)
						)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(legalFilesLbl)
							.addComponent(legalFilesTxt, 110, 110, 110)
						)
						.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(screenLbl)
								.addComponent(screenDDLst, 150, 150, 150)
						)
			   			.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
			   					.addComponent(xmlPubPathLbl)
			   					.addComponent(pubSlidesDDLst, 200, 200, 200)
								.addComponent(xmlPubPathTxt, 300, 300, 300)
					   	)
				   		.addGroup(groupLayout.createSequentialGroup()
				   			.addComponent(saveSetBtn, 140, 140, 140)
				   			.addComponent(resetBtn, 140, 140, 140)
				   			.addComponent(startBtn, 140, 140, 140)
				   		)
				)
				
				.addGap(100)
				
				.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						
						.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						   	.addComponent(foodSttLbl)
						   	.addComponent(yFoodRbtn)
						   	.addComponent(nFoodRbtn)
						)
						.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
							 .addComponent(pubSttLbl)
							 .addComponent(yPubRbtn)
							 .addComponent(nPubRbtn)
						)
			   	)
			)
			.addComponent(statusLbl)
		);
		
			//	Vertical
		groupLayout.setVerticalGroup(
				groupLayout.createSequentialGroup()
				   	.addComponent(headerLbl)
				   	.addGap(45)
				   	.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				   		
				   		.addGroup(groupLayout.createSequentialGroup()
				   				
					   			.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						   			.addComponent(nrOfImgsLbl)
						   			.addComponent(nrOfImgsTxt, 20, 20, 20)
						   		)
								.addGap(10)
						   		.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						   			.addComponent(timeLbl)
						   			.addComponent(timeTxt, 20, 20, 20)
						   		)
								.addGap(10)
								.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
									.addComponent(nrOfCommentsLbl)
									.addComponent(nrOfCommentsTxt, 20, 20, 20)
								)
								.addGap(10)
								.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
									.addComponent(legalFilesLbl)
									.addComponent(legalFilesTxt, 20, 20, 20)
								)
								.addGap(10)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(screenLbl)
									.addComponent(screenDDLst, 20, 20, 20)
								)
								.addGap(20)
					   			.addGroup(groupLayout.createSequentialGroup()
									.addComponent(xmlPubPathLbl)
									.addComponent(pubSlidesDDLst, 20, 20, 20)
									.addComponent(xmlPubPathTxt, 20, 20, 20)
					   			)
								.addGap(60)
					   			.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					   				.addComponent(saveSetBtn, 30, 30, 30)
					   				.addComponent(resetBtn, 30, 30, 30)
					   				.addComponent(startBtn, 30, 30, 30)
					   			)
				   		)
				   		
				   		.addGroup(groupLayout.createSequentialGroup()
				   				
				   				.addGroup(groupLayout.createSequentialGroup()
						   			.addComponent(foodSttLbl)
						   			.addComponent(yFoodRbtn)
						   			.addComponent(nFoodRbtn)
						   		)
								.addGap(20)
						   		.addGroup(groupLayout.createSequentialGroup()
						   			.addComponent(pubSttLbl)
						   			.addComponent(yPubRbtn)
						   			.addComponent(nPubRbtn)
						   		)
				   		)
				   		
				  )
				  .addGap(50)
				  .addComponent(statusLbl)
			);
		
		//	Add panel
		adminFrame.add(bgPanel);
		adminFrame.add(fgPanel);
		
		adminFrame.setVisible(true);
	}
	
//	@Override
//	protected void paintComponent(Graphics g){
//		bgImage = ImageIO.read(new URL("C:\\Users\\Andeers\\Pictures\\bgIcon.png"));
//	    super.paintComponent(g); 
//	    if (bgImage != null)
//	      g.drawImage(bgImage, 0,0, bgPanel);
	    
	    
//		Image image = null;
//		Graphics g = null;
//		imgRect Rect = new imgRect(0, 0, 200, 200);
//	    try {
//			image = ImageIO.read(new File("C:\\Users\\Andeers\\Desktop\\tapetoverlay1.png"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		TexturePaint tp = new TexturePaint(slideImage,Rect);
//        g2d.setPaint(tp);
//        g2d.fill(Rect);
//		
//		g.setPaint(image);
//		g.drawImage(image, 0, 0, null);
//		panel.paint(g);
//	    
//	  }
	
	//	Reads Config and saves values in 'confValues'
	private void readConfig() {
		handler = new ConfigHandler();
		
		try {
			confValues = handler.processLineByLine();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	//	Saves current settings to Config
	public void saveSettings() {
		String[] lines = new String[45];
		Date today = new Date();
		
		handler = new ConfigHandler();
		
		//	Standard text
		lines[0] = "";
		lines[1] = "_|    _|  _|           _|_|_|_|_|          _|  _|";
		lines[2] = "_|  _|          _|_|_|     _|      _|_|_|  _|  _|  _|";
		lines[3] = "_|_|      _|  _|_|         _|    _|    _|  _|  _|_|";
		lines[4] = "_|  _|    _|      _|_|     _|    _|    _|  _|  _|  _|";
		lines[5] = "_|    _|  _|  _|_|_|       _|      _|_|_|  _|  _|    _|";
		lines[6] = "";
		lines[7] = "";
		lines[8] = "    _      _      _      _      _";
		lines[9] = " __(')> __(')> __(')> __(')> __(^)<";
		lines[10] = " \\___)  \\___)  \\___)  \\___)  \\___)";
		lines[11] = "*'*'*'*'*'*'*Config.hans*'*'*'*'*'*'*";
		lines[12] = "*'*'* The official config file  *'*'*";
		lines[13] = "*'*'* of KisTalk. Please support*'*'*";
		lines[14] = "*'*'* our provider, Hans, by    *'*'*";
		lines[15] = "*'*'* sending a dollar to:      *'*'*";
		lines[16] = "*'*'*     Hans                  *'*'*";
		lines[17] = "*'*'*     17 Corey Dr Franklin  *'*'*";
		lines[18] = "*'*'*     TN 37067, USA         *'*'*";
		lines[19] = "*'*'* Mark your letter with     *'*'*";
		lines[20] = "*'*'* 'KisTalk-Hans'            *'*'*";
		lines[21] = "*'*'*                    //Hans *'*'*";
		lines[22] = "*'*'*'*'*'*'*'*'*'*'*'*'*'*'*'*'*'*'*";
		lines[23] = "*'*'*'*'*'*'*'*'*'*'*'*'*'*'*'*'*'*'*";
		lines[24] = "";
		lines[25] = "//	Filen senast ändrad " + today.getHours() + ":" + today.getMinutes() + " den " + today.getDate() + "/" + today.getMonth();
		lines[26] = "";
		lines[27] = "";
		
		//	Defined values
		lines[28] = "Max_number_of_Images %" + nrOfImgsTxt.getText();
		lines[30] = "Number_of_Hans %-";
		lines[32] = "Timer_interval %" + timeTxt.getText();
		lines[34] = "Number_of_Jimmys %-";
		lines[36] = "supported_image_formats %.jpg .png .gif .bnp";
		lines[38] = "Screen_index %1";
		lines[40] = "XMLURL %C:\\\\Users\\\\Andeers\\\\Documents\\\\Mina mottagna filer\\\\bild.xml";
		lines[42] = "Number_of_comments %2";
		lines[44] = "Path_to_Pubslides %" + xmlPubPathTxt.getText();
		
		// Write to file (config)
		handler.setConfig(lines);
		readConfig();
		
	}

	//	Resets Config to its standard state
	public void resetConfig() {
		handler = new ConfigHandler();
		
		//	Reset Config-file
		try {
			handler.resetConfig();
		} catch (IOException e) {
			System.out.println("Default config file has been removed or modified, Please contact Hans at <i>hans@kistalk.com</i>.");
		}
		
		// Reset 'confValues'
		readConfig();
		nrOfImgsTxt.setText(confValues[0]);
		timeTxt.setText(confValues[1]);
		
	}

	//	Set the path
	public void setPubSlidesPath(){
		
		xmlPubPathTxt.setText("C://TMEIT");
	}
	
	public void disabelButtons(){
		//	Disable buttons
		
		saveSetBtn.setEnabled(false);
		saveSetBtn.setVisible(false);
		resetBtn.setEnabled(false);
		resetBtn.setVisible(false);
	}
	
	public void enableButtons(){
		//	Enable buttons
		
		saveSetBtn.setEnabled(true);
		saveSetBtn.setVisible(true);
		resetBtn.setEnabled(true);
		resetBtn.setVisible(true);
	}
	
	public void popup(String message){
		popFrame = new JFrame();
		JPanel popPanel = new JPanel();
		JLabel popLabel = new JLabel();
		popText = new TextField();
		popButton = new JButton();
		
		popFrame.setLocation(400, 100);
		popFrame.setTitle("KisTalk Popup");
		popPanel.setBackground(Color.BLACK);
		popLabel.setText(message);
		popLabel.setForeground(Color.WHITE);
		popText.setSize(10, 5);
		popButton.setText("Submit");
		popButton.addActionListener(listener);
		popButton.setForeground(Color.WHITE);
		popButton.setBackground(Color.BLACK);
		
		popPanel.add(popLabel);
		popPanel.add(popText);
		popPanel.add(popButton);
		popFrame.add(popPanel);
		
		popFrame.setVisible(true);
		
	}
	
	public void yFood() {
		URL url;
		try {
			url = new URL("http://hostname:80/cgi");
			URLConnection conn = url.openConnection();
		    conn.setDoOutput(true);
		    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		    wr.write("yFood");
		    wr.flush();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	public void nFood(){
		URL url;
		try {
			url = new URL("http://hostname:80/cgi");
			URLConnection conn = url.openConnection();
		    conn.setDoOutput(true);
		    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		    wr.write("nFood");
		    wr.flush();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void yPub(){
		URL url;
		try {
			url = new URL("http://hostname:80/cgi");
			URLConnection conn = url.openConnection();
		    conn.setDoOutput(true);
		    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		    wr.write("yPub");
		    wr.flush();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void nPub(){
		URL url;
		try {
			url = new URL("http://hostname:80/cgi");
			URLConnection conn = url.openConnection();
		    conn.setDoOutput(true);
		    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		    wr.write("nPub");
		    wr.flush();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//	Starts the Slideshow
	public void startSlideshow() {
		saveSettings();
		readConfig();
		
		// Starta bildspelet
		//new TwoDSlideShow();
		
	}
	
	//	Main
	public static void main(String[] args) {
		new AdminFrame();
	}

	//	Listeners
	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == saveSetBtn){
				saveSettings();
				statusLbl.setText("Status: Settings saved to Config");
			}else if (e.getSource() == resetBtn){
				resetConfig();
				statusLbl.setText("Status: Config is back to normal");
			}else if (e.getSource() == startBtn){
				System.out.println("Startar bildspel..."); //startSlideshow();
				statusLbl.setText("Status: Starting Slideshow...");
			}else if(e.getSource() == yFoodRbtn){
				yFood();
				statusLbl.setText("Dinner is served!");
			}else if(e.getSource() == nFoodRbtn){
				nFood();
				statusLbl.setText("Food is no more");
			}else if(e.getSource() == yPubRbtn){
				yPub();
				statusLbl.setText("The pub is open!");
			}else if(e.getSource() == nPubRbtn){
				nPub();
				statusLbl.setText("The pub is closed");
			}else if(e.getSource() == pubSlidesDDLst){
				
			}else if(e.getSource() == screenDDLst){
				statusLbl.setText("lol");
			}else if(e.getSource().toString() == "hej"){
				
			}else if(e.getSource() == popButton){
				value = popText.getText();
				popFrame.dispose();
			}else if(e.getSource() == lolButton){
				popup("hejhejhallå!");
				statusLbl.setText("popup! Kom igen!");
			}else if(e.getSource() == null){
				
			}

		}
	}
	
}
