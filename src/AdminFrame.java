import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextField;
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


public class AdminFrame {
	Image bgImage;
	String slideItem = null;
	
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
	JLabel sttsFoodRdyLbl;
	JLabel sttsPubLbl;
	JLabel sttsFoodLbl;
	JLabel sttsEventLbl;
	JLabel foodInstrLbl;
	
	TextField nrOfImgsTxt;
	TextField timeTxt;
	TextField popTxt;
	TextField xmlPubPathTxt;
	TextField legalFilesTxt;
	TextField nrOfCommentsTxt;
	TextField eventTxt;
	TextField foodTxt;
	
	JCheckBox foodChb;
	JCheckBox pubChb;
	
	JComboBox pubSlidesDDLst;
	JComboBox screenDDLst;
	
	GroupLayout groupLayout;
	Graphics g;
	
	DesktopApplication controller;

	//	Constructor
	public AdminFrame(DesktopApplication contTmp) {
		controller = contTmp;
	}

	//	Setting up the settings frame
	public void setupFrame(final String[] confValues, final List<String> slideNames, final List<String> slidePaths){
		
		//	Background Pic
		ImageIcon icon = new ImageIcon("bgIcon.png");
		final Image bgImage = icon.getImage();
		
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
		
		nrOfImgsTxt = new TextField();
		timeTxt = new TextField();
		xmlPubPathTxt = new TextField();
		legalFilesTxt = new TextField();
		nrOfCommentsTxt = new TextField();
		eventTxt = new TextField();
		foodTxt = new TextField();
		
		foodChb = new JCheckBox();
		pubChb = new JCheckBox();
		
		pubSlidesDDLst = new JComboBox();
		screenDDLst = new JComboBox();
		
		ButtonListener listener = new ButtonListener();
		
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
		adminFrame.setSize(510,600);
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
		
		xmlPubPathLbl.setText("OpenOffice Powerpoint: ");
		xmlPubPathLbl.setForeground(Color.WHITE);
		
		legalFilesLbl.setText("Approved file extensions: ");
		legalFilesLbl.setForeground(Color.WHITE);
		
		pubLbl.setText("Specify event:");
		pubLbl.setForeground(Color.GRAY);
		
		foodLbl.setText("Specify food:");
		foodLbl.setForeground(Color.GRAY);
		
		statusLbl.setText("Status: Ready for some action");
		statusLbl.setForeground(Color.WHITE);
		statusLbl.setFont(new Font("Helvetica", Font.ITALIC, 10));
		
		nrOfCommentsLbl.setText("Number of comments: ");
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
		
		eventTxt.setFont(new Font("Algerian", Font.PLAIN, 12));
		eventTxt.setEnabled(false);
		
		foodTxt.setFont(new Font("Algerian", Font.PLAIN, 12));
		foodTxt.setEnabled(false);
		
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
		
		pubSlidesDDLst.setFont(new Font("Gulim", Font.PLAIN, 12));
		pubSlidesDDLst.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent e){
						if (e.getStateChange() == ItemEvent.SELECTED){
							if (e.getItem().toString() != "[Saved slideshows]"){ // Set pathen to the path that belongs to the selected Item
								xmlPubPathTxt.setText(slidePaths.get(slideNames.indexOf(e.getItem().toString())));
								xmlPubPathTxt.setEnabled(false);
								xmlPubPathTxt.setFont(new Font("Algerian", Font.ITALIC, 12));
								statusLbl.setText("Status: " + e.getItem().toString() + "s Slideshow is choosed");
								slideItem = e.getItem().toString();
								
							}else{
								xmlPubPathTxt.setText("C:\\...");
								xmlPubPathTxt.setEnabled(true);
								xmlPubPathTxt.setFont(new Font("Algerian", Font.PLAIN, 12));
								statusLbl.setText("Status: Choose a Slideshow or specify a path");
								slideItem = null;
								
							}
						}
					}
				}
		);
		
		screenDDLst.addItem("External");
		screenDDLst.addItem("This");
		screenDDLst.setSelectedItem("THIS");
		screenDDLst.setFont(new Font("Gulim", Font.PLAIN, 12));
		screenDDLst.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent e){
						if (e.getStateChange() == ItemEvent.SELECTED)
							statusLbl.setText("Status: " + e.getItem().toString() + " screen it is!");
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
						   	.addGap(23)
						   	.addComponent(nrOfImgsTxt, 50, 50, 50)
						)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(timeLbl)
							.addGap(58)
							.addComponent(timeTxt, 50, 50, 50)
						)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(nrOfCommentsLbl)
							.addGap(37)
							.addComponent(nrOfCommentsTxt, 50, 50, 50)
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
				   		)
				  )
				  .addGap(50)
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
				  .addGap(20)
				  .addComponent(statusLbl)
			);
		
		//	Add panel
		adminFrame.add(thePanel);
		adminFrame.setVisible(true);
	}
	
	public void slideSaved(String name){
		statusLbl.setText("Status: Slideshow saved as " + name);
	}
	
	private class ButtonListener implements ActionListener {
		String[] values = new String[11];
		
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == saveSetBtn){ // Save settings to config
				getTxt();
				controller.setConf(values);
				statusLbl.setText("Status: Settings saved to Config");
				
			}else if (e.getSource() == resetBtn){ // Reset settings in config
				values = controller.resetConf();
				setTxt();
				statusLbl.setText("Status: Config is back to normal");
				
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
				controller.announce(foodTxt.getText(), eventTxt.getText(), pubChb.isSelected(), foodChb.isSelected());
				statusLbl.setText("Status: Announcement was sent!");
				
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
				statusLbl.setText("Status: Starting Slideshow...");
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
		
		public void getTxt(){ // Get text from textfields
			values[0] = nrOfImgsTxt.getText();
			values[2] = timeTxt.getText();
			values[7] = nrOfCommentsTxt.getText();
			values[8] = xmlPubPathTxt.getText();
			
		}
		
		public void setTxt(){ // Write text to textfields
			nrOfImgsTxt.setText(values[0]);
			timeTxt.setText(values[2]);
			nrOfCommentsTxt.setText(values[7]);
			xmlPubPathTxt.setText(values[8]);
			
		}

		
//		public void disable() { // Disable buttons
//			saveSetBtn.setEnabled(false);
//			resetBtn.setEnabled(false);
//			startBtn.setEnabled(false);
//			exitBtn.setEnabled(false);
//			pubChb.setEnabled(false);
//			foodChb.setEnabled(false);
//			screenDDLst.setEnabled(false);
//			pubSlidesDDLst.setEnabled(false);
//			nrOfImgsTxt.setEnabled(false);
//			timeTxt.setEnabled(false);
//			nrOfCommentsTxt.setEnabled(false);
//			xmlPubPathTxt.setEnabled(false);
//			
//		}
//	
//		public void enable(){ // Enable buttons
//			saveSetBtn.setEnabled(true);
//			resetBtn.setEnabled(true);
//			startBtn.setEnabled(true);
//			exitBtn.setEnabled(true);
//			pubChb.setEnabled(true);
//			foodChb.setEnabled(true);
//			screenDDLst.setEnabled(true);
//			pubSlidesDDLst.setEnabled(true);
//			nrOfImgsTxt.setEnabled(true);
//			timeTxt.setEnabled(true);
//			nrOfCommentsTxt.setEnabled(true);
//			if (slideItem == null)
//				xmlPubPathTxt.setEnabled(true);
//			
//		}
		
	}

}
