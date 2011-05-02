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
	String[] confValues = new String[11];
	int nrOfPubSlids;
	String food;
	Image bgImage;
	
	ConfigSettings config = new ConfigSettings();
	PubSlides pubslides = new PubSlides();
	
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
	TextField popTxt;
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
	
	GroupLayout groupLayout;
	Graphics g;

	//	Constructor
	public AdminFrame() {
		
	}

	//	Setting up the settings frame
	protected void setupFrame(ButtonListener listener){
		
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
		
		//	Get config values
		confValues = config.getValues();
		
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
		yFoodRbtn.setText("True"); //Ska bytas ut mot Checkboxes
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
							pubslides.setPath(e.getItem().toString(), new AdminFrame());
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

}
