import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AdminFrame {
	String slideItem = null;
	String[] values;
	Font stdFont;
	Font smlFont;
	Font stdItalFont;
	
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
	JButton announceBtn;
	
	JLabel nrOfImgsLbl;
	JLabel timeLbl;
	JLabel foodLbl;
	JLabel pubLbl;
	JLabel statusLbl;
	JLabel xmlPubPathLbl;
	JLabel legalFilesLbl;
	JLabel nrOfCommentsLbl;
	JLabel screenLbl;
	JLabel bgLbl;
	JLabel pubnfoodStatusLbl;
	JLabel fadeLbl;
	
	JTextField nrOfImgsTxt;
	JTextField timeTxt;
	JTextField xmlPubPathTxt;
	JTextField legalFilesTxt;
	JTextField nrOfCommentsTxt;
	JTextField eventTxt;
	JTextField foodTxt;
	JTextField fadeTxt;
	
	JCheckBox foodChb;
	JCheckBox pubChb;
	
	JComboBox pubSlidesDDLst;
	JComboBox screenDDLst;
	
	GroupLayout groupLayout;
	Graphics g;
	
	DesktopApplication controller;
	ButtonListener listener;
	
	List<String> slideNames;
	List<String> slidePaths;

	//	Constructor
	public AdminFrame(DesktopApplication tmpCont, Font tmpFont) {
		controller = tmpCont;
		stdFont = tmpFont;
	}

	//	Setting up the settings frame
	public void setupFrame(final String[] confValues, final List<String> tmpNames, final List<String> tmpPaths, final Image bgImage){
		values = confValues;
		slideNames = tmpNames;
		slidePaths = tmpPaths;
		
		//	Create all objects
		adminFrame = new JFrame();
		headerLbl = new JLabel();
		
		saveSetBtn = new JButton();
		resetBtn = new JButton();
		startBtn = new JButton();
		exitBtn = new JButton();
		savePathBtn = new JButton();
		remPathBtn = new JButton();
		announceBtn = new JButton();
		
		nrOfImgsLbl = new JLabel();
		timeLbl= new JLabel();
		foodLbl = new JLabel();
		pubLbl = new JLabel();
		statusLbl = new JLabel();
		xmlPubPathLbl = new JLabel();
		legalFilesLbl = new JLabel();
		nrOfCommentsLbl = new JLabel();
		screenLbl = new JLabel();
		bgLbl = new JLabel();
		fadeLbl = new JLabel();
		pubnfoodStatusLbl = new JLabel();
		
		nrOfImgsTxt = new JTextField();
		timeTxt = new JTextField();
		xmlPubPathTxt = new JTextField();
		legalFilesTxt = new JTextField();
		nrOfCommentsTxt = new JTextField();
		eventTxt = new JTextField();
		foodTxt = new JTextField();
		fadeTxt = new JTextField();
		
		foodChb = new JCheckBox();
		pubChb = new JCheckBox();
		
		pubSlidesDDLst = new JComboBox();
		screenDDLst = new JComboBox();
		
		listener = new ButtonListener();
		
		smlFont = new Font("Helvetica", Font.PLAIN, 10);
		stdItalFont = new Font("Helvetica", Font.ITALIC, 12);
		
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
		adminFrame.setSize(510,610);
		adminFrame.setResizable(false);
		adminFrame.setLocation(300, 50);
		adminFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		adminFrame.setTitle("KisTalk Slideshow Settings");
		adminFrame.setUndecorated(false);
		
		//	Panel settings
		thePanel.setLayout(groupLayout);
		thePanel.setBackground(Color.decode("#ae0808"));
		
		//	Label settings
		headerLbl.setIcon(new ImageIcon("kistalk_adm_logo.png"));
		
		nrOfImgsLbl.setText("Nr of pics (from KisTalk): ");
		nrOfImgsLbl.setForeground(Color.WHITE);
		
		timeLbl.setText("Time interval (ms): ");
		timeLbl.setForeground(Color.WHITE);
		
		xmlPubPathLbl.setText("Choose a .ppt-file (path): ");
		xmlPubPathLbl.setForeground(Color.WHITE);
		
		legalFilesLbl.setText("Approved file extensions: ");
		legalFilesLbl.setForeground(Color.WHITE);
		
		pubLbl.setText("Specify event:");
		pubLbl.setForeground(Color.GRAY);
		
		foodLbl.setText("Specify food:");
		foodLbl.setForeground(Color.GRAY);
		
		statusLbl.setText("Status: Ready for some action");
		statusLbl.setForeground(Color.WHITE);
		statusLbl.setFont(smlFont);
		
		nrOfCommentsLbl.setText("Number of comments: ");
		nrOfCommentsLbl.setForeground(Color.WHITE);
		
		screenLbl.setText("Choose screen: ");
		screenLbl.setForeground(Color.WHITE);
		
		fadeLbl.setText("Fading speed (ms): ");
		fadeLbl.setForeground(Color.WHITE);
		
		pubnfoodStatusLbl.setText("Pub_open: -  Event: -  Food_ready: -  Food: -");
		pubnfoodStatusLbl.setForeground(Color.WHITE);
		pubnfoodStatusLbl.setFont(smlFont);
		
		//	Text settings
		nrOfImgsTxt.setText(values[0]);
		nrOfImgsTxt.setFont(stdFont);
		
		timeTxt.setText(values[2]);
		timeTxt.setFont(stdFont);
		
		xmlPubPathTxt.setText("C:\\...");
		xmlPubPathTxt.setFont(stdFont);
		
		legalFilesTxt.setText(values[4]);
		legalFilesTxt.setFont(stdItalFont);
		legalFilesTxt.setEnabled(false);
		
		nrOfCommentsTxt.setText(values[7]);
		nrOfCommentsTxt.setFont(stdFont);
		
		eventTxt.setFont(stdFont);
		eventTxt.setEnabled(false);
		
		foodTxt.setFont(stdFont);
		foodTxt.setEnabled(false);
		
		fadeTxt.setFont(stdFont);
		fadeTxt.setText(values[3]);
		
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
		
		announceBtn.setText("Send announce");
		announceBtn.setForeground(Color.BLACK);
		announceBtn.addActionListener(listener);
		
		//	Checkboxes settings
		foodChb.setText("Dinner is served");
		foodChb.setForeground(Color.WHITE);
		foodChb.addActionListener(listener);
		foodChb.setOpaque(false);
		foodChb.setEnabled(false);
		
		pubChb.setText("Pub is open");
		pubChb.setForeground(Color.WHITE);
		pubChb.addActionListener(listener);
		pubChb.setOpaque(false);
		
		//	DropDownList settings, with ItemListeners
		pubSlidesDDLst.addItem("[Saved slideshows]");
		for (int i = 0; i < slideNames.size(); i++)
			pubSlidesDDLst.addItem(slideNames.get(i));
		
		pubSlidesDDLst.setFont(stdFont);
		pubSlidesDDLst.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent e){
						if (e.getStateChange() == ItemEvent.SELECTED){
							if (e.getItem().toString().equals("[Saved slideshows]") == false){ // Set pathen to the path that belongs to the selected Item
								xmlPubPathTxt.setText(slidePaths.get(slideNames.indexOf(e.getItem().toString())));
								xmlPubPathTxt.setEnabled(false);
								xmlPubPathTxt.setFont(stdItalFont);
								statusLbl.setText("Status: " + e.getItem().toString() + "s Slideshow is choosed");
								slideItem = e.getItem().toString();
								
								if (e.getItem().toString().equals("TMEIT")){
									eventTxt.setText("Tisdagspub");
								}else if (e.getItem().toString().equals("Qmisk")){
									eventTxt.setText("Torsdagspub");
								}else if(e.getItem().toString().equals("ITK")){
									eventTxt.setText("Lan");
								}
								
							}else{
								xmlPubPathTxt.setText("C:\\...");
								xmlPubPathTxt.setEnabled(true);
								xmlPubPathTxt.setFont(stdFont);
								statusLbl.setText("Status: Choose a Slideshow or specify a path");
								slideItem = null;
								eventTxt.setText("");
								
							}
						}
					}
				}
		);
		
		screenDDLst.addItem("This");
		screenDDLst.addItem("External");
		screenDDLst.setSelectedItem("THIS");
		screenDDLst.setFont(stdFont);
		screenDDLst.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent e){
						if (e.getStateChange() == ItemEvent.SELECTED)
							if (e.getItem().toString() == "This"){
								values[5] = "0";
							}else if (e.getItem().toString() == "External"){
								values[5] = "1";
							}
						statusLbl.setText("Status: " + e.getItem().toString() + " screen it is!");
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
						   	.addGap(23)
						   	.addComponent(nrOfImgsTxt, 50, 50, 50)
						)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(nrOfCommentsLbl)
							.addGap(37)
							.addComponent(nrOfCommentsTxt, 50, 50, 50)
						)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(timeLbl)
							.addGap(58)
							.addComponent(timeTxt, 50, 50, 50)
						)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(fadeLbl)
							.addGap(56)
							.addComponent(fadeTxt, 50, 50, 50)
						)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(legalFilesLbl)
							.addGap(20)
							.addComponent(legalFilesTxt, 110, 110, 110)
						)
						.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(screenLbl)
								.addComponent(screenDDLst, 155, 155, 155)
						)
			   			.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
			   					.addComponent(xmlPubPathLbl)
			   					.addComponent(xmlPubPathTxt, 277, 277, 277)
			   					.addComponent(pubSlidesDDLst, 155, 155, 155)
					   	)
				)
				
				.addGap(50)
				
				.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						
						.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addComponent(pubChb)
							.addComponent(pubLbl)
							.addComponent(eventTxt, 125, 125, 125)
						)
						.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						   	.addComponent(foodLbl)
						   	.addComponent(foodTxt, 125, 125, 125)
						   	.addComponent(foodChb)
						)
						.addComponent(announceBtn, 125, 125, 125)
			   	)
			)
			.addGroup(groupLayout.createSequentialGroup()
				   	.addComponent(saveSetBtn, 130, 130, 130)
				   	.addGap(10)
				   	.addComponent(savePathBtn, 130, 130, 130)
				   	.addGap(40)
					.addComponent(startBtn, 130, 130, 130)
				   			
			)
			.addGroup(groupLayout.createSequentialGroup()
					.addComponent(resetBtn, 130, 130, 130)
					.addGap(10)
					.addComponent(remPathBtn, 130, 130, 130)
					.addGap(40)
					.addComponent(exitBtn, 130, 130, 130)
			)
			.addComponent(statusLbl)
			.addGroup(groupLayout.createSequentialGroup()
				.addComponent(pubnfoodStatusLbl)
			)
		);
		
			//	Vertical
		groupLayout.setVerticalGroup(
				groupLayout.createSequentialGroup()
				   	.addComponent(headerLbl)
				   	.addGap(35)
				   	.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				   		
				   		.addGroup(groupLayout.createSequentialGroup()
				   				
					   			.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						   			.addComponent(nrOfImgsLbl)
						   			.addComponent(nrOfImgsTxt, 20, 20, 20)
						   		)
								.addGap(10)
								.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
									.addComponent(nrOfCommentsLbl)
									.addComponent(nrOfCommentsTxt, 20, 20, 20)
								)
								.addGap(10)
								.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						   			.addComponent(timeLbl)
						   			.addComponent(timeTxt, 20, 20, 20)
						   		)
						   		.addGap(10)
						   		.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						   			.addComponent(fadeLbl)
						   			.addComponent(fadeTxt, 20, 20, 20)
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
						   			.addComponent(pubChb)
						   			.addGap(5)
						   			.addComponent(pubLbl)
						   			.addComponent(eventTxt, 20, 20, 20)
						   		)
						   		.addGap(10)
						   		.addGroup(groupLayout.createSequentialGroup()
						   			.addComponent(foodLbl)
								   	.addComponent(foodTxt, 20, 20, 20)
								   	.addComponent(foodChb)
						   		)
						   		.addGap(20)
								.addComponent(announceBtn, 25, 25, 25)
								.addGap(5)
				   		)
				  )
				  .addGap(40)
				  .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					   		.addComponent(saveSetBtn, 25, 25, 25)
					   		.addComponent(savePathBtn, 25, 25, 25)
							.addComponent(startBtn, 25, 25, 25)
				  )
				  .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						  .addComponent(resetBtn, 25, 25, 25)
						  .addComponent(remPathBtn, 25, 25, 25)
						  .addComponent(exitBtn, 25, 25, 25)
				  )
				  .addGap(10)
				  .addComponent(statusLbl)
				  .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(pubnfoodStatusLbl)
				  )
			);
		
		//	Add panel
		adminFrame.add(thePanel);
		adminFrame.setVisible(true);
	}
	
	public void slideSaved(String name, String path){
		pubSlidesDDLst.addItem(name);
		statusLbl.setText("Status: Slideshow saved as " + name);
		slideNames.add(name);
		slidePaths.add(path);
		pubSlidesDDLst.setSelectedItem(name);
	}
	
	public void disable() { // Disable buttons
		saveSetBtn.setEnabled(false);
		resetBtn.setEnabled(false);
		startBtn.setEnabled(false);
		exitBtn.setEnabled(false);
		pubChb.setEnabled(false);
		foodChb.setEnabled(false);
		screenDDLst.setEnabled(false);
		pubSlidesDDLst.setEnabled(false);
		nrOfImgsTxt.setEnabled(false);
		timeTxt.setEnabled(false);
		nrOfCommentsTxt.setEnabled(false);
		
		savePathBtn.setEnabled(false);
		remPathBtn.setEnabled(false);
		announceBtn.setEnabled(false);
		fadeTxt.setEnabled(false);
		pubChb.setEnabled(false);
		
		eventTxt.setEnabled(false);
		foodTxt.setEnabled(false);
		foodChb.setEnabled(false);
		xmlPubPathTxt.setEnabled(false);
		
		nrOfImgsLbl.setForeground(Color.GRAY);
		timeLbl.setForeground(Color.GRAY);
		foodLbl.setForeground(Color.GRAY);
		pubLbl.setForeground(Color.GRAY);
		statusLbl.setForeground(Color.GRAY);
		xmlPubPathLbl.setForeground(Color.GRAY);
		legalFilesLbl.setForeground(Color.GRAY);
		nrOfCommentsLbl.setForeground(Color.GRAY);
		screenLbl.setForeground(Color.GRAY);
		bgLbl.setForeground(Color.GRAY);
		pubnfoodStatusLbl.setForeground(Color.GRAY);
		fadeLbl.setForeground(Color.GRAY);
		
	}

	public void enable(){ // Enable buttons
		saveSetBtn.setEnabled(true);
		resetBtn.setEnabled(true);
		startBtn.setEnabled(true);
		exitBtn.setEnabled(true);
		screenDDLst.setEnabled(true);
		pubSlidesDDLst.setEnabled(true);
		nrOfImgsTxt.setEnabled(true);
		timeTxt.setEnabled(true);
		nrOfCommentsTxt.setEnabled(true);
		
		savePathBtn.setEnabled(true);
		remPathBtn.setEnabled(true);
		announceBtn.setEnabled(true);
		fadeTxt.setEnabled(true);
		pubChb.setEnabled(true);
		
		
		if (pubChb.isSelected()){
			eventTxt.setEnabled(true);
			foodTxt.setEnabled(true);
			foodChb.setEnabled(true);
		}
		if (slideItem == null)
			xmlPubPathTxt.setEnabled(true);
		
		nrOfImgsLbl.setForeground(Color.WHITE);
		timeLbl.setForeground(Color.WHITE);
		
		
		statusLbl.setForeground(Color.WHITE);
		xmlPubPathLbl.setForeground(Color.WHITE);
		legalFilesLbl.setForeground(Color.WHITE);
		nrOfCommentsLbl.setForeground(Color.WHITE);
		screenLbl.setForeground(Color.WHITE);
		bgLbl.setForeground(Color.WHITE);
		pubnfoodStatusLbl.setForeground(Color.WHITE);
		fadeLbl.setForeground(Color.WHITE);
		
		if (pubChb.isSelected()){
			foodLbl.setForeground(Color.WHITE);
			pubLbl.setForeground(Color.WHITE);
			
		}
		
	}
	
	public void getTxt(){ // Get text from textfields
		values[0] = nrOfImgsTxt.getText();
		values[2] = timeTxt.getText();
		values[3] = fadeTxt.getText();
		values[7] = nrOfCommentsTxt.getText();
		values[8] = xmlPubPathTxt.getText();
		
	}
	
	public void setTxt(String[] values){ // Write text to textfields
		nrOfImgsTxt.setText(values[0]);
		timeTxt.setText(values[2]);
		fadeTxt.setText(values[3]);
		nrOfCommentsTxt.setText(values[7]);
		xmlPubPathTxt.setText(values[8]);
		statusLbl.setText("Status: Config is back to normal");
		
	}
	
	private class ButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == saveSetBtn){ // Save settings to config
				getTxt();
				controller.setConf(values);
				statusLbl.setText("Status: Settings saved to Config");
				
			}else if (e.getSource() == resetBtn){ // Reset settings in config
				controller.popup("Are you sure you want to reset the config file?", "");
				
			}else if (e.getSource() == pubChb){ // Announce pub
				if (pubChb.isSelected()){
					foodChb.setEnabled(true);
					foodLbl.setForeground(Color.WHITE);
					foodTxt.setEnabled(true);
					pubLbl.setForeground(Color.WHITE);
					eventTxt.setEnabled(true);
				}else{
					foodChb.setEnabled(false);
					foodLbl.setForeground(Color.GRAY);
					foodTxt.setEnabled(false);
					pubLbl.setForeground(Color.GRAY);
					eventTxt.setEnabled(false);
				}
				
			}else if (e.getSource() == foodChb){ // Announce food
				statusLbl.setText("Status: Omnomnomnomnomnomnom");
				
			}else if (e.getSource() == announceBtn){ // Announce
				if (foodTxt.getText().length() < 40 && eventTxt.getText().length() < 40){
					if (foodTxt.getText().length() == 0)
						foodTxt.setText("-");
					if (eventTxt.getText().length() == 0)
						eventTxt.setText("-");
					controller.announce(foodTxt.getText(), eventTxt.getText(), pubChb.isSelected(), foodChb.isSelected());
					statusLbl.setText("Status: Announcement was sent!");
					pubnfoodStatusLbl.setText("Pub_open: " + pubChb.isSelected() + "  Event: " + eventTxt.getText() + "  Food_ready: " + foodChb.isSelected() + "  Food: " + foodTxt.getText());
				
				} else {
					controller.fail("Error", "Both textfields require 1 to 40 chars!");
				}
				
			}else if (e.getSource() == savePathBtn){ // Save Slideshow
				controller.popup("Name the slideshow: ", xmlPubPathTxt.getText());
				statusLbl.setText("Status: Slideshow saved");
			
			}else if (e.getSource() == remPathBtn){ // Remove saved Slideshow
				if (slideItem != null){
					controller.remPath(slideItem);
					pubSlidesDDLst.removeItem(slideItem);
					statusLbl.setText("Status: Slideshow removed");
				}else{
					statusLbl.setText("Status: Choose a slideshow to remove");
				}
				
			}else if (e.getSource() == startBtn){ // Start Slideshow
				getTxt();
				controller.setConf(values);
				exitBtn.setText("Quit SlideShow");
				startBtn.setEnabled(false);
				statusLbl.setText("Status: Slideshow started");
				controller.startShow();
				
			}else if (e.getSource() == exitBtn){ // Exit Slideshow / Program
				if (exitBtn.getText().equals("Quit SlideShow")){
					statusLbl.setText("Status: The slideshow is dead...");
					exitBtn.setText("Quit KisTalk");
					startBtn.setEnabled(true);
					controller.exitShow();
				}else{
					adminFrame.dispose();
				}
				
			}
		}
	}

}
