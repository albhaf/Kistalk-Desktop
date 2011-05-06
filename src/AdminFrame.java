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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AdminFrame {
	CreateNewElements create;	
	
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
	JButton pathBtn;
	
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
	JFileChooser fc;
	
	List<String> slideNames;
	List<String> slidePaths;

	//	Constructor
	public AdminFrame(DesktopApplication tmpCont, Font tmpFont) {
		controller = tmpCont;
		stdFont = tmpFont;
	}

	//	Setting up the settings frame
	public void setupFrame(final String[] confValues, final List<String> tmpNames, final List<String> tmpPaths, final Image bgImage){
		create = new CreateNewElements();
		listener = new ButtonListener();
		values = confValues;
		slideNames = tmpNames;
		slidePaths = tmpPaths;
		
		//	Create all objects
		adminFrame = new JFrame();
		headerLbl = new JLabel();
		
		saveSetBtn = create.setNewButton("Save settings", listener);
		resetBtn = create.setNewButton("Reset settings", listener);
		startBtn = create.setNewButton("Start slideshow", listener);
		exitBtn = create.setNewButton("Exit", listener);
		savePathBtn = create.setNewButton("Save path", listener);
		remPathBtn = create.setNewButton("Remove Path", listener);
		announceBtn = create.setNewButton("Send announce", listener);
		
		nrOfImgsLbl = create.setNewLabel("Nr of pics (from KisTalk): ", stdFont);
		timeLbl= create.setNewLabel("Time interval (ms): ", stdFont);
		foodLbl = create.setNewLabel("Specify food:", stdFont);
		pubLbl = create.setNewLabel("Specify event:", stdFont);
		statusLbl = create.setNewLabel("Status: Ready for some action", smlFont);
		xmlPubPathLbl = create.setNewLabel("Choose a .ppt-file (path): ", stdFont);
		legalFilesLbl = create.setNewLabel("Approved file extensions: ", stdFont);
		nrOfCommentsLbl = create.setNewLabel("Number of comments: ", stdFont);
		screenLbl = create.setNewLabel("Choose screen: ", stdFont);
		bgLbl = create.setNewLabel("", stdFont);
		fadeLbl = create.setNewLabel("Fading speed (ms): ", stdFont);
		pubnfoodStatusLbl = create.setNewLabel("Pub_open: -  Event: -  Food_ready: -  Food: -", smlFont);
		
		nrOfImgsTxt = create.setNewTextField(values[0], stdFont, true);
		timeTxt = create.setNewTextField(values[2], stdFont, true);
		xmlPubPathTxt = create.setNewTextField("C:\\...", stdFont, true);
		legalFilesTxt = create.setNewTextField(values[4], stdItalFont, false);
		nrOfCommentsTxt = create.setNewTextField(values[7], stdFont, true);
		eventTxt = create.setNewTextField("", stdFont, false);
		foodTxt = create.setNewTextField("", stdFont, false);
		fadeTxt = create.setNewTextField(values[3], stdFont, true);
						
		
		foodChb = create.setNewCheckBox("Dinner is served", listener, false);
		pubChb = create.setNewCheckBox("Pub is open", listener, true);
		
		pubSlidesDDLst = new JComboBox();
		screenDDLst = new JComboBox();
		
		fc = new JFileChooser();
		
		smlFont = new Font("Helvetica", Font.PLAIN, 10);
		stdItalFont = new Font("Helvetica", Font.ITALIC, 12);
		
		// Create and Paint thePanel background
		thePanel = new JPanel(){ //Observera att detta �r en create!
			private static final long serialVersionUID = -7374793566417261848L;

			public void paint(Graphics g){
				g.drawImage(bgImage, 0,0, this);
				setOpaque(false);
	    		super.paint(g);
	    		setOpaque(true);
	    		
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
								}else{
									eventTxt.setText("");
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
	
	public boolean chkTxts(){  //G�r n�got �t!
		if (nrOfImgsTxt.getText().equals("") == false && timeTxt.getText().equals("") == false && xmlPubPathTxt.getText().equals("") == false && nrOfCommentsTxt.getText().equals("") == false && fadeTxt.getText().equals("") == false) {
			return true;
		}else{
			return false;
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
	
	public void setPaths(List<String> names, List<String> paths) {
		pubSlidesDDLst.removeAllItems();
		slideNames.clear();
		slidePaths.clear();
		
		pubSlidesDDLst.addItem("[Saved slideshows]");
		for (int i = 0; i < names.size(); i++) {
			pubSlidesDDLst.addItem(names.get(i));
			slideNames.add(names.get(i));
			slidePaths.add(paths.get(i));
		}
	}
	
	private class ButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == saveSetBtn){ // Save settings to config
				if (chkTxts()){
					getTxt();
					controller.setConf(values);
					statusLbl.setText("Status: Settings saved to Config");
				}else{
					controller.fail("Error", "One or more textfields contains too few / too many chars");
				}
				
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
//			}else if (e.getSource() == pathBtn){
//				int returnVal = fc.showOpenDialog(FileChooserDemo.this);
				
				
			}else if (e.getSource() == savePathBtn){ // Save Slideshow
				if (xmlPubPathTxt.getText().equals("") == false){
				controller.popup("Name the slideshow: ", xmlPubPathTxt.getText());
				statusLbl.setText("Status: Slideshow saved");
				}else{
					controller.fail("Wrong path", "You have to specify a path to your .ppt-file!");
				}
			
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
