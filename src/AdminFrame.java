import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

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
	final int nrOfConfigValues = 11; // Doesn't include regular textlines. If change, then change configHandler too
	int nrOfPubSlides;
	int slideNr = 0;
	String food;
	Image bgImage;
	String[] confValues = new String[nrOfConfigValues];
	
	JFrame adminFrame;
	JFrame popFrame;
	JFrame logFrame;
	JPanel thePanel;
	JLabel headerLbl;
	
	JButton saveSetBtn;
	JButton resetBtn;
	JButton startBtn;
	JButton exitBtn;
	JButton savePathBtn;
	JButton remPathBtn;
	JButton popSbmBtn;
	JButton popClsBtn;
	JButton logSbmBtn;
	JButton logClsBtn;
	
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
	JLabel popLbl;
	
	TextField nrOfImgsTxt;
	TextField timeTxt;
	TextField popTxt;
	TextField xmlPubPathTxt;
	TextField legalFilesTxt;
	TextField nrOfCommentsTxt;
	TextField logUserTxt;
	TextField logPassTxt;
	
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
	GroupLayout groupLayout;
	Graphics g;

	//	Constructor
	public AdminFrame() {
		logInFrame();
		
	}

	//	Setting up the settings frame
	private void setupFrame(){
		
		//	Create all objects
		adminFrame = new JFrame();
		headerLbl = new JLabel();
		
		saveSetBtn = new JButton();
		resetBtn = new JButton();
		startBtn = new JButton();
		exitBtn = new JButton();
		savePathBtn = new JButton();
		remPathBtn = new JButton();
		
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
		
		// Create and Paint thePanel background
		thePanel = new JPanel(){ //Observera att detta �r en create!
			public void paint(Graphics g){
				g.drawImage(bgImage, 0,0, this);
				setOpaque(false);
	    		super.paint(g);
	    		setOpaque(true);
	    		repaint();
	    		
			}
		};
		
		groupLayout = new GroupLayout(thePanel);
		
		//	Frame settings
		adminFrame.setSize(500,600);
		adminFrame.setResizable(false);
		adminFrame.setLocation(300, 50);
		adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		adminFrame.setTitle("KisTalk Slideshow Settings");
		
		//	Panel settings
		thePanel.setLayout(groupLayout);
		thePanel.setBackground(Color.decode("#ae0808"));
		
		//	Label settings
		headerLbl.setIcon(new ImageIcon("kistalk_adm_logo.png"));
		
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
		nrOfImgsTxt.setFont(new Font("Algerian", Font.PLAIN, 12));
		
		timeTxt.setText(confValues[2]);
		timeTxt.setFont(new Font("Algerian", Font.PLAIN, 12));
		
		xmlPubPathTxt.setText(confValues[8]);
		xmlPubPathTxt.setFont(new Font("Algerian", Font.PLAIN, 12));
		
		legalFilesTxt.setText(confValues[4]);
		legalFilesTxt.setFont(new Font("Algerian", Font.ITALIC, 12));
		legalFilesTxt.setEnabled(false);
		
		nrOfCommentsTxt.setText(confValues[7]);
		nrOfCommentsTxt.setFont(new Font("Algerian", Font.PLAIN, 12));
		
		//	Button settings
		saveSetBtn.setText("Save settings");
		saveSetBtn.setForeground(Color.BLACK);
		saveSetBtn.addActionListener(listener);
		
		resetBtn.setText("Reset settings");
		resetBtn.setForeground(Color.BLACK);
		resetBtn.addActionListener(listener);
		
		startBtn.setText("Start slideshow");
		startBtn.setForeground(Color.BLACK);
		startBtn.addActionListener(listener);
		
		exitBtn.setText("Exit");
		exitBtn.setForeground(Color.BLACK);
		exitBtn.addActionListener(listener);
		
		savePathBtn.setText("Save path");
		savePathBtn.setForeground(Color.BLACK);
		savePathBtn.addActionListener(listener);
		
		remPathBtn.setText("Remove Path");
		remPathBtn.setForeground(Color.BLACK);
		remPathBtn.addActionListener(listener);
		
		//	Radiobuttons settings
		yFoodRbtn.setText("True");
		yFoodRbtn.setForeground(Color.WHITE);
		yFoodRbtn.addActionListener(listener);
		yFoodRbtn.setOpaque(false);
		
		nFoodRbtn.setSelected(true);
		nFoodRbtn.setText("False");
		nFoodRbtn.setForeground(Color.WHITE);
		nFoodRbtn.addActionListener(listener);
		nFoodRbtn.setOpaque(false);
		
		yPubRbtn.setText("True");
		yPubRbtn.setForeground(Color.WHITE);
		yPubRbtn.addActionListener(listener);
		yPubRbtn.setOpaque(false);
		
		nPubRbtn.setSelected(true);
		nPubRbtn.setText("False");
		nPubRbtn.setForeground(Color.WHITE);
		nPubRbtn.addActionListener(listener);
		nPubRbtn.setOpaque(false);
		
		foodRbgr.add(yFoodRbtn);
		foodRbgr.add(nFoodRbtn);
		
		pubRbgr.add(yPubRbtn);
		pubRbgr.add(nPubRbtn);
		
		//	DropDownList settings, with ItemListeners
		pubSlidesDDLst.addItem("[Other Slideshow]");
		pubSlidesDDLst.addItem("TMEIT");
		pubSlidesDDLst.addItem("Qmisk");
		pubSlidesDDLst.addItem("ITK");
		pubSlidesDDLst.setFont(new Font("Gulim", Font.PLAIN, 12));
		pubSlidesDDLst.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent e){
						if (e.getStateChange() == ItemEvent.SELECTED){
							setPubSlidesPath(e.getItem().toString());
						}
					}
				}
		);
		
		screenDDLst.addItem("External");
		screenDDLst.addItem("This");
		screenDDLst.setFont(new Font("Gulim", Font.PLAIN, 12));
		screenDDLst.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent e){
						if (e.getStateChange() == ItemEvent.SELECTED)
							statusLbl.setText(e.getItem().toString() + " screen it is!");
							if (e.getItem().toString() == "This"){
								confValues[5] = "0";
							}else if (e.getItem().toString() == "External"){
								confValues[5] = "1";
							}
					}
				}
		);
		
		//	Layout settings & add components
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		
		//	Layout
			//	Horisontal
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
			.addComponent(headerLbl)
			.addGap(70)
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
			   					.addComponent(xmlPubPathTxt, 300, 300, 300)
			   					.addComponent(pubSlidesDDLst, 200, 200, 200)
					   	)
				)
				
				.addGap(50)
				
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
			.addGroup(groupLayout.createSequentialGroup()
				   	.addComponent(saveSetBtn, 140, 140, 140)
				   	.addComponent(savePathBtn, 140, 140,140)
					.addComponent(remPathBtn, 140, 140, 140)
				   			
			)
			.addGroup(groupLayout.createSequentialGroup()
					.addComponent(resetBtn, 140, 140, 140)
					.addComponent(exitBtn, 140, 140, 140)
					.addComponent(startBtn, 140, 140, 140)
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
								.addGap(15)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(screenLbl)
									.addComponent(screenDDLst, 20, 20, 20)
								)
								.addGap(15)
					   			.addGroup(groupLayout.createSequentialGroup()
									.addComponent(xmlPubPathLbl)
									.addComponent(xmlPubPathTxt, 20, 20, 20)
									.addComponent(pubSlidesDDLst, 20, 20, 20)
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
				  .addGap(30)
				  .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					   		.addComponent(saveSetBtn, 30, 30, 30)
					   		.addComponent(savePathBtn, 30, 30, 30)
							  .addComponent(remPathBtn, 30, 30, 30)
				  )
				  .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						  .addComponent(resetBtn, 30, 30, 30)
						  .addComponent(exitBtn, 30, 30, 30)
					   		.addComponent(startBtn, 30, 30, 30)
				  )
				  .addGap(30)
				  .addComponent(statusLbl)
			);
		
		//	Add panel
		adminFrame.add(thePanel);
		adminFrame.setVisible(true);
	}
	
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

	//	Saves current settings to Config (except Default Turtle, he only lives in config when config is defaultahrized)
	public void saveSettings() {
		String[] lines = new String[49];
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
		lines[25] = "//	Date of last revision: " + today.getHours() + ":" + today.getMinutes() + " den " + today.getDate() + "/" + today.getMonth();
		lines[26] = "";
		lines[27] = "";
		lines[29] = "";
		lines[31] = "";
		lines[33] = "";
		lines[35] = "";
		lines[37] = "";
		lines[39] = "";
		lines[41] = "";
		lines[43] = "";
		lines[45] = "";
		lines[47] = "";
		
		//	Defined values
		lines[28] = "Max_number_of_Images %" + nrOfImgsTxt.getText();
		lines[30] = "Number_of_Hans %-";
		lines[32] = "Timer_interval %" + timeTxt.getText();
		lines[34] = "Number_of_Jimmys %-";
		lines[36] = "supported_image_formats %.jpg .png .gif .bnp";
		lines[38] = "Screen_index %" + confValues[5];
		lines[40] = "XMLURL %http://www.kistalk.com/desktop_images.xml";
		lines[42] = "Number_of_comments %2";
		lines[44] = "Path_to_Pubslides %" + xmlPubPathTxt.getText(); //Ett \ tas bort var g�ng filen laddas?
		lines[46] = "Saved_Pubslides %" + confValues[9];
		lines[48] = "Saved_Paths %" + confValues[10];
		
		// Write to file (config)
		handler.setConfig(lines);
		readConfig();
		
	}

	//	Resets Config to its standard state
	public void resetConfig() { //Error
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
	public void setPubSlidesPath(String name){
		if (name == "TMEIT"){
			xmlPubPathTxt.setText("C:\\TMEIT");
			xmlPubPathTxt.setFont(new Font("Algerian", Font.ITALIC, 12));
			xmlPubPathTxt.disable();
			statusLbl.setText(name + "s Slideshow is choosed");
		}else if (name == "Qmisk"){
			xmlPubPathTxt.setText("C:\\Qmisk");
			xmlPubPathTxt.setFont(new Font("Algerian", Font.ITALIC, 12));
			xmlPubPathTxt.disable();
			statusLbl.setText(name + "s Slideshow is choosed");
		}else if (name == "ITK"){
			xmlPubPathTxt.setText("C:\\ITK");
			xmlPubPathTxt.setFont(new Font("Algerian", Font.ITALIC, 12));
			xmlPubPathTxt.disable();
			statusLbl.setText(name + "s Slideshow is choosed");
		}else if (name == "[Other Slideshow]"){
			xmlPubPathTxt.setText("C:\\...");
			xmlPubPathTxt.setFont(new Font("Algerian", Font.PLAIN, 12));
			xmlPubPathTxt.enable();
			statusLbl.setText("Choose a Slideshow");
		}
		
	}
	
	//	Exit slideshow or, if slideshow's off, KisTalk
	public void exit() {
//		if (twoDSlideShow() == true){	//Om bildspelet �r ig�ng *VIKTIGT!*
//			statusLbl.setText("The slideshow is dead...");
//			twDSlideshow.dispose();
//			startBtn.setEnabled(true);
//			exitBtn.setText("Quit KisTalk");
//		}else{
//			statusLbl.setText("Goodbye!");
//			adminFrame.dispose();
//		}
		
	}
	
	//	Disable buttons
	public void disableButtons(){
		saveSetBtn.setEnabled(false); //BUGG! xmlPubPathTxt enablas, �ven om den inte ska vara enbled
		resetBtn.setEnabled(false);
		startBtn.setEnabled(false);
		exitBtn.setEnabled(false);
		yFoodRbtn.setEnabled(false);
		nFoodRbtn.setEnabled(false);
		yPubRbtn.setEnabled(false);
		nPubRbtn.setEnabled(false);
		screenDDLst.setEnabled(false);
		pubSlidesDDLst.setEnabled(false);
		nrOfImgsTxt.setEnabled(false);
		timeTxt.setEnabled(false);
		nrOfCommentsTxt.setEnabled(false);
		xmlPubPathTxt.setEnabled(false);
		
	}
	
	//	Enable buttons
	public void enableButtons(){
		
		saveSetBtn.setEnabled(true);
		resetBtn.setEnabled(true);
		startBtn.setEnabled(true);
		exitBtn.setEnabled(true);
		yFoodRbtn.setEnabled(true);
		nFoodRbtn.setEnabled(true);
		yPubRbtn.setEnabled(true);
		nPubRbtn.setEnabled(true);
		screenDDLst.setEnabled(true);
		pubSlidesDDLst.setEnabled(true);
		nrOfImgsTxt.setEnabled(true);
		timeTxt.setEnabled(true);
		nrOfCommentsTxt.setEnabled(true);
		xmlPubPathTxt.setEnabled(true);
		
	}
	
	//	Creates a pop-up
	public void popUp(String message){ //Anv�ndas f�r LogIn kanske?
		disableButtons();
		
		popFrame = new JFrame();
		JPanel popPanel = new JPanel(){ // Insert annan bild! Knapp ist f�r Kryss
			public void paint(Graphics g){
				g.drawImage(bgImage, 0,0, this);
				setOpaque(false);
	    		super.paint(g);
	    		setOpaque(true);
	    		repaint();
	    		
			}
		};
		popLbl = new JLabel();
		popTxt = new TextField();
		popSbmBtn = new JButton();
		popClsBtn = new JButton();
		GroupLayout popLayout = new GroupLayout(popPanel);
		
		popFrame.setLocation(400, 270);
		popFrame.setSize(400, 100);
		popFrame.setTitle("KisTalk Popup");
		popFrame.setResizable(false);
		popPanel.setLayout(popLayout);
		popLbl.setText(message);
		popLbl.setForeground(Color.WHITE);
		popSbmBtn.setText("Submit");
		popSbmBtn.addActionListener(listener);
		popClsBtn.setText("Close");
		popClsBtn.addActionListener(listener);
		
		popLayout.setHorizontalGroup(
				popLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						
						.addGroup(popLayout.createSequentialGroup()
								.addGap(10)
								.addComponent(popLbl)
								.addGap(5)
								.addComponent(popTxt, 200, 200, 200)
						)
						.addGroup(popLayout.createSequentialGroup()
								.addGap(120)
								.addComponent(popSbmBtn, 90, 90, 90)
								.addGap(5)
								.addComponent(popClsBtn, 90, 90, 90)
						)
		);
		
		popLayout.setVerticalGroup(
				popLayout.createSequentialGroup()
				.addGap(10)
				.addGroup(popLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(popLbl)
						.addComponent(popTxt, 20, 20, 20)
				)
				.addGap(10)
				.addGroup(popLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(popSbmBtn, 25, 25, 25)
						.addComponent(popClsBtn, 25, 25, 25)
				)
				
		);
		
		popFrame.add(popPanel);
		
		popFrame.setVisible(true);
		
	}
	
	public void logInFrame(){
//		disableButtons();

		// Background pic
		ImageIcon icon = new ImageIcon("bgIcon.png");
		bgImage = icon.getImage();
		
		logFrame = new JFrame();
		JPanel logPanel = new JPanel(){
			public void paint(Graphics g){
				g.drawImage(bgImage, 0,0, this);
				setOpaque(false);
	    		super.paint(g);
	    		setOpaque(true);
	    		repaint();
	    		
			}
		};
		JLabel logInLbl = new JLabel();
		JLabel logUserLbl = new JLabel();
		JLabel logMailLbl = new JLabel();
		JLabel logPassLbl = new JLabel();
		JLabel logInstrLbl = new JLabel();
		logUserTxt = new TextField();
		logPassTxt = new TextField();
		logSbmBtn = new JButton();
		logClsBtn = new JButton();
		GroupLayout logLayout = new GroupLayout(logPanel);
		
		logFrame.setLocation(400, 230);
		logFrame.setSize(300, 190);
		logFrame.setTitle("KisTalk Login");
		logFrame.setResizable(false);
		logFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		logPanel.setLayout(logLayout);
		
		logInLbl.setText("LogIn");
		logInLbl.setForeground(Color.WHITE);
		logInLbl.setFont(new Font("Imperial", Font.BOLD, 22));
		logUserLbl.setText("username: ");
		logUserLbl.setForeground(Color.WHITE);
		logMailLbl.setText("@kth.se");
		logMailLbl.setForeground(Color.WHITE);
		logPassLbl.setText("token: ");
		logPassLbl.setForeground(Color.WHITE);
		logInstrLbl.setText("To get your token, log in to KisTalk.com");
		logInstrLbl.setForeground(Color.WHITE);
		logInstrLbl.setFont(new Font("Imperial", Font.ITALIC, 8));
		
		logSbmBtn.setText("Submit");
		logSbmBtn.addActionListener(listener);
		logClsBtn.setText("Close");
		logClsBtn.addActionListener(listener);
		
		logLayout.setHorizontalGroup(
				logLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(logLayout.createSequentialGroup()
					.addGap(110)
					.addComponent(logInLbl)
					)
					.addGap(10)
					.addGroup(logLayout.createSequentialGroup()
							.addGap(15)
							.addComponent(logUserLbl)
							.addGap(5)
							.addComponent(logUserTxt, 100, 100, 100)
							.addGap(3)
							.addComponent(logMailLbl)
					)
					.addGroup(logLayout.createSequentialGroup()
							.addGap(15)
							.addComponent(logPassLbl)
							.addGap(31)
							.addComponent(logPassTxt, 100, 100, 100)
					)
					.addGroup(logLayout.createSequentialGroup()
							.addGap(50)
							.addComponent(logClsBtn, 80, 80, 80)
							.addGap(10)
							.addComponent(logSbmBtn, 80, 80, 80)
					).addGroup(logLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(logInstrLbl)
					)
		);
		
		logLayout.setVerticalGroup(
				logLayout.createSequentialGroup()
				.addComponent(logInLbl)
				.addGap(10)
				.addGroup(logLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(logUserLbl)
						.addComponent(logUserTxt, 20, 20, 20)
						.addComponent(logMailLbl)
				)
				.addGap(10)
				.addGroup(logLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(logPassLbl)
						.addComponent(logPassTxt, 20, 20, 20)
				)
				.addGap(10)
				.addGroup(logLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(logClsBtn, 25, 25, 25)
						.addComponent(logSbmBtn, 25, 25, 25)
				)
				.addGap(20)
				.addComponent(logInstrLbl)
				
		);
		
		logFrame.add(logPanel);
		
		logFrame.setVisible(true);
	}
	
	//	Dinner is served, send HTTP-post to server
	public void yFood(String food) {//D�lig kod, Per fixar
		//	Send info (like "food" [String])
//		URL url;
//		try {
//			url = new URL("http://hostname:80/cgi");
//			URLConnection conn = url.openConnection();
//		    conn.setDoOutput(true);
//		    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
//		    wr.write("yFood");
//		    wr.flush();
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	    
	}
	
	//	Dinner isn't served, send HTTP-post to server
	public void nFood(){//D�lig kod, Per fixar
//		URL url;
//		try {
//			url = new URL("http://hostname:80/cgi");
//			URLConnection conn = url.openConnection();
//		    conn.setDoOutput(true);
//		    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
//		    wr.write("nFood");
//		    wr.flush();
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	//	Pub is open, send HTTP-post to server
	public void yPub(){ //D�lig kod, Per fixar
//		URL url;
//		try {
//			url = new URL("http://hostname:80/cgi");
//			URLConnection conn = url.openConnection();
//		    conn.setDoOutput(true);
//		    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
//		    wr.write("yPub");
//		    wr.flush();
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	//	Pub is closed, send HTTP-post to server
	public void nPub(){//D�lig kod, Per fixar
//		URL url;
//		try {
//			url = new URL("http://hostname:80/cgi");
//			URLConnection conn = url.openConnection();
//		    conn.setDoOutput(true);
//		    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
//		    wr.write("nPub");
//		    wr.flush();
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	//	Starts the Slideshow
	public void startSlideshow() {
		saveSettings();
		readConfig();
		
		// Starta bildspelet
		try {
			new TwoDSlideShow();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		exitBtn.setText("Quit SlideShow");
		startBtn.setEnabled(false);
		
	}
	
	//	Main
	public static void main(String[] args) {
		new AdminFrame();
	}

	//	Listener
	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == saveSetBtn){ //Save settings
				saveSettings();
				statusLbl.setText("Status: Settings saved to Config");
			}else if (e.getSource() == resetBtn){ //Reset config
				resetConfig();
				statusLbl.setText("Status: Config is back to normal");
			}else if (e.getSource() == startBtn){ //Start slideshow
				startSlideshow();
				statusLbl.setText("Status: Starting Slideshow...");
			}else if(e.getSource() == yFoodRbtn){ //Dinner served
				popUp("What is teh food?");
				statusLbl.setText("Dinner is served!");
			}else if(e.getSource() == nFoodRbtn){ //No dinner
				nFood();
				statusLbl.setText("Food is no more");
			}else if(e.getSource() == yPubRbtn){ //Pub open
				yPub();
				statusLbl.setText("The pub is open!");
			}else if(e.getSource() == nPubRbtn){ //Pub closed
				nPub();
				statusLbl.setText("The pub is closed");
			}else if(e.getSource() == exitBtn){ //Exit
				exit();
			}else if(e.getSource() == savePathBtn){
				popUp("Name your Slideshow: ");
				confValues[9] = confValues[9] + "�" + xmlPubPathTxt.getText();
				pubSlidesDDLst.addItem(xmlPubPathTxt.getText());
				saveSettings();
			}else if(e.getSource() == popSbmBtn){ //Popup
				if (popLbl.getText() == "What is teh food?"){
					String food = popTxt.getText();
					yFood(food);
				}else{
//					pubPathSave();
				}
				popFrame.dispose();
				enableButtons();
			}else if(e.getSource() == popClsBtn){
				popFrame.dispose();
				enableButtons();
				nFoodRbtn.setSelected(true);
			}else if(e.getSource() == logSbmBtn){
				logFrame.dispose();
				readConfig();
				setupFrame();
			}else if(e.getSource() == logClsBtn){
				logFrame.dispose();
			}else if(e.getSource() == null){
				
			}

		}
	}
	
}
