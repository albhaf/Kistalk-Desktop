import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.TextComponent;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Checkbox;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;

public class AdminFrame {
	final int nrOfConfigLines = 11; // Includes regular textlines
	int nrOfPubSlides;
	int slideNr = 0;
	List<String> confValues = new ArrayList<String>();
	
	JFrame adminFrame;
	JPanel panel;
	JLabel headerLbl;
	
	JButton newImgBtn;
	JButton showAllImgsBtn;
	JButton editImgBtn;
	JButton delOneImgBtn;
	JButton delAllImgsBtn;
	JButton saveSetBtn;
	JButton resetBtn;
	JButton startBtn;
	JButton saveSlideBtn;
	JButton dscrdEditBtn;
	
	JLabel newImgLbl;
	JLabel newTitleLbl;
	JLabel uploadLbl;
	JLabel upldInfoLbl;
	JLabel newConLbl;
	JLabel nrOfImgsLbl;
	JLabel timeLbl;
	JLabel foodSttLbl;
	JLabel pubSttLbl;
	JLabel statusLbl;
	
	TextField titleTxt;
	TextField uploadTxt;
	TextArea contentTxt;
	TextField nrOfImgsTxt;
	TextField timeTxt;
	
	JRadioButton yFoodRbtn;
	JRadioButton nFoodRbtn;
	JRadioButton yPubRbtn;
	JRadioButton nPubRbtn;
	ButtonGroup foodRbgr;
	ButtonGroup pubRbgr;
	
	ButtonListener listener = new ButtonListener();
	FileHandler handler;

	//	Constructor
	public AdminFrame() {
		//countPubSlides();
		readConfig();
		setupPanel();

	}

	//	Setting up the settings frame
	private void setupPanel(){
		
		//	Create new objects
		adminFrame = new JFrame();
		panel = new JPanel();
		headerLbl = new JLabel();
		
		newImgBtn = new JButton();
		showAllImgsBtn = new JButton();
		editImgBtn = new JButton();
		delOneImgBtn = new JButton();
		delAllImgsBtn = new JButton();
		saveSetBtn = new JButton();
		resetBtn = new JButton();
		startBtn = new JButton();
		saveSlideBtn = new JButton();
		dscrdEditBtn = new JButton();
		
		newImgLbl = new JLabel();
		newTitleLbl = new JLabel();
		uploadLbl = new JLabel();
		upldInfoLbl = new JLabel();
		newConLbl = new JLabel();
		nrOfImgsLbl = new JLabel();
		timeLbl= new JLabel();
		foodSttLbl = new JLabel();
		pubSttLbl = new JLabel();
		statusLbl = new JLabel();
		
		titleTxt = new TextField();
		uploadTxt = new TextField();
		contentTxt = new TextArea();
		nrOfImgsTxt = new TextField();
		timeTxt = new TextField();
		
		yFoodRbtn = new JRadioButton();
		nFoodRbtn = new JRadioButton();
		yPubRbtn = new JRadioButton();
		nPubRbtn = new JRadioButton();
		foodRbgr = new ButtonGroup();
		pubRbgr = new ButtonGroup();
		
		GroupLayout groupLayout = new GroupLayout(panel);
		
		
		
		//	Frame settings
		adminFrame.setSize(900,600);
		adminFrame.setResizable(false);
		adminFrame.setLocation(200, 50);
		adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		adminFrame.setTitle("KisTalk Slideshow Settings");
		
		//	Panel settings
		panel.setBackground(Color.BLACK);
		panel.setOpaque(true);
		panel.setLayout(groupLayout);
		
		//	Label settings
		headerLbl.setText("Welcome to KisTalk Administration Center!");
		headerLbl.setFont(new Font("Cambria", Font.BOLD, 32));
		headerLbl.setForeground(Color.WHITE);
		
		newImgLbl.setText("Add new slide:");
		newImgLbl.setForeground(Color.WHITE);
		newImgLbl.setFont(new Font("Helvetica", Font.BOLD, 13));
		
		newTitleLbl.setText("Title: ");
		newTitleLbl.setForeground(Color.WHITE);
		
		uploadLbl.setText("Upload picture: ");
		uploadLbl.setForeground(Color.WHITE);
		
		upldInfoLbl.setText("from URL or computer, leave empty for text only");
		upldInfoLbl.setForeground(Color.WHITE);
		upldInfoLbl.setFont(new Font("Helvetica", Font.ITALIC, 9));
		
		newConLbl.setText("Content: ");
		newConLbl.setForeground(Color.WHITE);
		
		nrOfImgsLbl.setText("Nr of pics (from KisTalk): ");
		nrOfImgsLbl.setForeground(Color.WHITE);
		
		timeLbl.setText("Time interval (ms): ");
		timeLbl.setForeground(Color.WHITE);
		
		pubSttLbl.setText("The pub is open");
		pubSttLbl.setForeground(Color.WHITE);
		
		foodSttLbl.setText("Dinner is served");
		foodSttLbl.setForeground(Color.WHITE);
		
		statusLbl.setText("Status: Ready for some action");
		statusLbl.setForeground(Color.WHITE);
		statusLbl.setFont(new Font("Helvetica", Font.ITALIC, 9));
		
		//	Text settings
		titleTxt.setText("");
		titleTxt.setFont(new Font("Cambria", Font.BOLD, 14));
		
		uploadTxt.setText("");
		
		contentTxt.setText("");
		contentTxt.setFont(new Font("Courer", Font.PLAIN, 12));
		
		nrOfImgsTxt.setText(confValues.get(0));
		nrOfImgsTxt.setFont(new Font("Algerian", Font.ITALIC, 12));
		
		timeTxt.setText(confValues.get(1));
		timeTxt.setFont(new Font("Algerian", Font.ITALIC, 12));
		
		//	Button settings
		newImgBtn.setText("L�gg till ny slide");
		newImgBtn.setForeground(Color.WHITE);
		newImgBtn.setBackground(Color.BLACK);
		newImgBtn.addActionListener(listener);
		newImgBtn.setOpaque(false);
		
		showAllImgsBtn.setText("Visa existerande slides");
		showAllImgsBtn.setForeground(Color.WHITE);
		showAllImgsBtn.setBackground(Color.BLACK);
		showAllImgsBtn.addActionListener(listener);
		showAllImgsBtn.setOpaque(false);
		
		editImgBtn.setText("Edit a slide");
		editImgBtn.setForeground(Color.WHITE);
		editImgBtn.setBackground(Color.BLACK);
		editImgBtn.addActionListener(listener);
		editImgBtn.setOpaque(false);
		
		delOneImgBtn.setText("Delete slide");
		delOneImgBtn.setForeground(Color.WHITE);
		delOneImgBtn.setBackground(Color.BLACK);
		delOneImgBtn.addActionListener(listener);
		delOneImgBtn.setOpaque(false);
		
		delAllImgsBtn.setText("Rensa slides");
		delAllImgsBtn.setForeground(Color.WHITE);
		delAllImgsBtn.setBackground(Color.BLACK);
		delAllImgsBtn.addActionListener(listener);
		delAllImgsBtn.setOpaque(false);
		
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
		
		saveSlideBtn.setText("Save slide");
		saveSlideBtn.setForeground(Color.WHITE);
		saveSlideBtn.setBackground(Color.BLACK);
		saveSlideBtn.addActionListener(listener);
		saveSlideBtn.setOpaque(false);
		saveSlideBtn.setEnabled(false);
		saveSlideBtn.setVisible(false);
		
		dscrdEditBtn.setText("Avbryt edit");
		dscrdEditBtn.setForeground(Color.WHITE);
		dscrdEditBtn.setBackground(Color.BLACK);
		dscrdEditBtn.addActionListener(listener);
		dscrdEditBtn.setOpaque(false);
		dscrdEditBtn.setEnabled(false);
		dscrdEditBtn.setVisible(false);
		
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
		
		
		//	Layout settings & add components
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		
			//	Horisontal
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
			.addComponent(headerLbl)
			.addGroup(groupLayout.createSequentialGroup()
				.addGap(100)
			   	.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
			   			.addComponent(newImgLbl)
				   		.addGroup(groupLayout.createSequentialGroup()
				   			.addComponent(newTitleLbl)
				   			.addComponent(titleTxt, 300, 300, 300)
				   		)
				   		.addGroup(groupLayout.createSequentialGroup()
				   			.addComponent(uploadLbl)
				   			.addComponent(uploadTxt, 200, 200, 200)
				   		)
				   		.addGroup(groupLayout.createSequentialGroup()
				   			.addGap(100)
				   			.addComponent(upldInfoLbl)
				   		)
				   		.addGroup(groupLayout.createSequentialGroup()
				   			.addComponent(newConLbl)
				   			.addComponent(contentTxt, 310, 310, 310)
				   		)
				   		.addGroup(groupLayout.createSequentialGroup()
				   			.addGap(0)
				   			.addComponent(newImgBtn, 140, 140, 140)
				   			.addComponent(showAllImgsBtn, 140, 140, 140)
				   			.addComponent(delAllImgsBtn, 140, 140, 140)
				   		)
				   		.addGroup(groupLayout.createSequentialGroup()
				   			.addComponent(saveSetBtn, 140, 140, 140)
				   			.addComponent(resetBtn, 140, 140, 140)
				   			.addComponent(startBtn, 140, 140, 140)
				   		)
				   		.addGroup(groupLayout.createSequentialGroup()
				   			.addComponent(delOneImgBtn)
							.addComponent(editImgBtn)
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
					   	.addGroup(groupLayout.createSequentialGroup()
					   		.addComponent(nrOfImgsLbl)
					   		.addComponent(nrOfImgsTxt, 50, 50, 50)
						)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(timeLbl)
							.addComponent(timeTxt, 100, 100, 100)
						)
						.addComponent(saveSlideBtn)
						.addComponent(dscrdEditBtn)
						.addComponent(statusLbl)
			   	)
			)
		);
		
			//	Vertical
		groupLayout.setVerticalGroup(
				groupLayout.createSequentialGroup()
				   	.addComponent(headerLbl)
				   	.addGap(45)
				   	.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				   		.addGroup(groupLayout.createSequentialGroup()
				   				.addComponent(newImgLbl)
					   			.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					   				.addComponent(newTitleLbl)
					   				.addComponent(titleTxt, 20, 20, 20)
					   			)
					   			.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					   				.addComponent(uploadLbl)
					   				.addComponent(uploadTxt, 20, 20, 20)
					   			)
					   			.addComponent(upldInfoLbl)
					   			.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					   				.addComponent(newConLbl)
					   				.addComponent(contentTxt, 150, 150, 150)
					   			)
					   			.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					   				.addComponent(newImgBtn, 30, 30, 30)
					   				.addComponent(showAllImgsBtn, 30, 30, 30)
					   				.addComponent(delAllImgsBtn, 30, 30, 30)
					   			)
					   			.addGap(20)
					   			.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					   				.addComponent(saveSetBtn, 30, 30, 30)
					   				.addComponent(resetBtn, 30, 30, 30)
					   				.addComponent(startBtn, 30, 30, 30)
					   			)
					   			.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					   				.addComponent(delOneImgBtn)
									.addComponent(editImgBtn)
					   			)
				   		)
				   		.addGroup(groupLayout.createSequentialGroup()
				   				.addGap(30)
				   				.addGroup(groupLayout.createSequentialGroup()
						   				.addComponent(foodSttLbl)
						   				.addComponent(yFoodRbtn)
						   				.addComponent(nFoodRbtn))
						   			.addGroup(groupLayout.createSequentialGroup()
						   				.addComponent(pubSttLbl)
						   				.addComponent(yPubRbtn)
						   				.addComponent(nPubRbtn))
						   			.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						   				.addComponent(nrOfImgsLbl)
						   				.addComponent(nrOfImgsTxt, 20, 20, 20)
						   			)
						   			.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						   				.addComponent(timeLbl)
						   				.addComponent(timeTxt, 20, 20, 20)
						   			)
						   			.addComponent(saveSlideBtn)
						   			.addComponent(dscrdEditBtn)
						   			.addGap(80)
						   			.addComponent(statusLbl)
				   		)
				   )
			);
		
		//	Add panel
		adminFrame.add(panel);
		
		adminFrame.setVisible(true);
	}
	
	//	Counts the number of pub slides
	public void countPubSlides(){
		handler = new FileHandler("PubSlides.hans");
		nrOfPubSlides = handler.countLines();
		
	}
	
	//	Reads Config and saves values in 'confValues'
	private void readConfig() {
		handler = new FileHandler("Config.hans");
		
		try {
			confValues = handler.processLineByLine(1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	//	Saves current settings to Config
	public void saveSettings() {
		
		handler = new FileHandler("Config.hans");
		String[] valueslcl = new String[11];
		
		//	Standard text
		valueslcl[0] = "**Hans_config-fil**";
		valueslcl[1] = "//	Specifiera exakt hur m�nga bilder som har h�mtats";
		valueslcl[3] = "Number_of_Hans %-";
		valueslcl[5] = "Number_of_Jimmys %-";
		valueslcl[10] = "Hejd�!";
		
		//	Defined values
		valueslcl[2] = "Max_number_of_Images %" + nrOfImgsTxt.getText();
		valueslcl[4] = "Timer_interval %" + timeTxt.getText();
		valueslcl[6] = "supported_image_formats %.jpg .png .gif .bnp";
		valueslcl[7] = "Screen_index %1";
		valueslcl[8] = "XMLURL %C:\\\\Users\\\\Andeers\\\\Documents\\\\Mina mottagna filer\\\\bild.xml";
		valueslcl[9] = "Number_of_comments %2";
		
		// Write to file (config)
		handler.setConfig(valueslcl);
		readConfig();
		
	}

	//	Resets Config to its standard state
	public void resetConfig() {
		handler = new FileHandler("Config.hans"); //null
		
		//	Reset Config-file
		try {
			handler.resetConfig();
		} catch (IOException e) {
			System.out.println("Default config file has been removed or modified, Please contact Hans.");
		}
		
		// Reset 'confValues'
		readConfig();
		nrOfImgsTxt.setText(confValues.get(0));
		timeTxt.setText(confValues.get(1));
		
	}
	

	//	Creates a new slide and saves it
	public void newPubSlide() {
		List<String> lines = new ArrayList<String>();
		String[] tmp = null;
		StringBuffer sb = new StringBuffer();
		handler = new FileHandler("PubSlides.hans");
		
		String imgLink = chkSlideCon();
		
		//	Create String[]
		try {
			lines = handler.processLineByLine(5);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sb.append(titleTxt.getText() + "%" + contentTxt.getText() + "%" + imgLink);
		lines.add(sb.toString());
		
		for (int i = 0; i < nrOfPubSlides; i++){
			tmp[i] = lines.get(i);
		}
		
		//	Write to file
		handler.setConfig(tmp);
		
		nrOfPubSlides++;
		
	}
	
	//	Shows all slides
	public void readPubSlides(){
		List<String> title = new ArrayList<String>();
		List<String> content = new ArrayList<String>();
		List<String> link = new ArrayList<String>();
		
		FileHandler handler = new FileHandler("PubSlides.hans");
		StringBuffer sb = new StringBuffer();
		
		//	Get titles and content
		try {
			title = handler.processLineByLine(1);
			content = handler.processLineByLine(2);
			link = handler.processLineByLine(3);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//	Show all titles and content in a textfield
		for (int i = 0; i < nrOfPubSlides; i++){
			if (link.get(i) != null){
				sb.append("index " + i + ":" + "\n" + title.get(i) + "\n" + link.get(i) + "\n" + content.get(i) + "\n" + "\n");
			}else {
				sb.append("index " + i + ":" + "\n" + title.get(i) + "\n" + content.get(i) + "\n" + "\n");
			}
		}
		
		contentTxt.setText(sb.toString());
	}

	//	Edits an existing slide
	public void editSlide(){
		String title, content, link;
		List<String> lines = new ArrayList<String>();
		String[] tmp = new String[nrOfPubSlides];
		handler = new FileHandler("PubSlides.hans");
		
		disabelButtons();
		
		// Välj vilken slide som ska editeras, slideNr
		//Popup("Vilken bild ska editeras?");
		
		try {
			lines = handler.processLineByLine(5);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		title = handler.processLine(lines.get(slideNr))[0];
		content = handler.processLine(lines.get(slideNr))[1];
		link = handler.processLine(lines.get(slideNr))[2];
		
		titleTxt.setText(title);
		contentTxt.setText(content);
		uploadTxt.setText(link);
		
	}
	
	//	Save a slide after editing
	public void saveSlide(){
		List<String> lines = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		handler = new FileHandler("PubSlides.hans");
		String[] tmp = null;
		
		//	Check if content and title are valid, and returns ev link
		String imgLink = chkSlideCon();
		
		try {
			lines = handler.processLineByLine(5);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		Create String[]
		sb.append(titleTxt.getText() + "%" + contentTxt.getText() + "%" + imgLink);
		lines.remove(slideNr);
		lines.add(slideNr, sb.toString());
		
		
		//	Write to file
		for (int i = 0; i < nrOfPubSlides; i++){
			tmp[i] = lines.get(i);
		}
		
		//	Write to file
		handler.setConfig(tmp);
		
		enableButtons();
		nrOfPubSlides++;
		slideNr = 0;
		
	}
	
	public String chkSlideCon(){
		int nrOfConLtrs = 200;
		int nrOfTitLtrs = 35;
		String imgLink = "-";
		
		//	Check for an ev image
		if (uploadTxt.getText().equals("") == false){
			System.out.println("Du har valt att ladda upp en bild! Grattis!");
			
			// Skicka med l�nken till textfilen!
			imgLink = uploadTxt.getText();
			
			nrOfConLtrs = 50;
		}
		
		//	Check if number of letters is correct
		if (titleTxt.getText().length() > nrOfTitLtrs){
			System.out.println("Too many letters. The number of letters in the title is limited to " + nrOfTitLtrs);
			// Avbryt saveSlide(), tillbaka till edit-mode
		}
		if (contentTxt.getText().length() > nrOfConLtrs){
			System.out.println("Too many letters. The number of letters in the content is limited to " + nrOfConLtrs);
			// Avbryt saveSlide(), tillbaka till edit-mode
		}
		if (titleTxt.getText().length() < 2){
			System.out.println("Too few letters in the title field");
			// Avbryt saveSlide(), tillbaka till edit-mode
		}
		if (contentTxt.getText().length() < 2){
			System.out.println("Too few letters in the content field");
			// Avbryt saveSlide(), tillbaka till edit-mode
		}
		
		return imgLink;
	}
	
	public void disabelButtons(){
		//	Disable buttons
		newImgBtn.setEnabled(false);
		newImgBtn.setVisible(false);
		showAllImgsBtn.setEnabled(false);
		showAllImgsBtn.setVisible(false);
		editImgBtn.setEnabled(false);
		editImgBtn.setVisible(false);
		delOneImgBtn.setEnabled(false);
		delOneImgBtn.setVisible(false);
		delAllImgsBtn.setEnabled(false);
		delAllImgsBtn.setVisible(false);
		saveSetBtn.setEnabled(false);
		saveSetBtn.setVisible(false);
		resetBtn.setEnabled(false);
		resetBtn.setVisible(false);
		
		saveSlideBtn.setEnabled(true);
		saveSlideBtn.setVisible(true);
		dscrdEditBtn.setEnabled(true);
		dscrdEditBtn.setVisible(true);
	}
	
	public void enableButtons(){
		//	Enable buttons
		newImgBtn.setEnabled(true);
		newImgBtn.setVisible(true);
		showAllImgsBtn.setEnabled(true);
		showAllImgsBtn.setVisible(true);
		editImgBtn.setEnabled(true);
		editImgBtn.setVisible(true);
		delOneImgBtn.setEnabled(true);
		delOneImgBtn.setVisible(true);
		delAllImgsBtn.setEnabled(true);
		delAllImgsBtn.setVisible(true);
		saveSetBtn.setEnabled(true);
		saveSetBtn.setVisible(true);
		resetBtn.setEnabled(true);
		resetBtn.setVisible(true);
		
		saveSlideBtn.setEnabled(false);
		saveSlideBtn.setVisible(false);
		dscrdEditBtn.setEnabled(false);
		dscrdEditBtn.setVisible(false);
	}
	
	public void popup(){
		JFrame popFrame = new JFrame();
		JPanel popPanel = new JPanel();
		JLabel popLabel = new JLabel();
		TextField popText = new TextField();
		JButton popButton = new JButton();
		
		popPanel.add(popLabel);
		//popText
		//popButton
		//popFrame.add(popPanel);
		
	}

	public void delOnePubSlide(){
		List<String> lines = new ArrayList<String>();
		handler = new FileHandler("PubSlides.hans");
		
		int slideNr = 0;
		
		// V�lj vilken slide som ska tas bort, slideNr
		
		if (slideNr >= nrOfPubSlides){
			System.out.println("Du m�ste v�lja en slide som faktiskt finns! :(");
		}
		
		try {
			lines = handler.processLineByLine(5);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
lines.remove(slideNr);
		
		nrOfPubSlides--;
		
	}

	//	Deletes all slides
	public void delAllPubSlides(){
		handler = new FileHandler("PubSlides.hans");
		String[] tmp = new String[nrOfPubSlides];
		
		for (int i = 0; i < nrOfPubSlides; i++) // Nej!?
			tmp[i] = null;
		
		//	Set all lines in the file to null
		handler.setConfig(tmp);
		
		nrOfPubSlides = 0;
		
		readPubSlides();
		
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
	
//	Get an image and returns it
	public ImageIcon getPubImg(){
		String fileTxt = uploadTxt.getText();
		ImageIcon picture;
		
		if (fileTxt.startsWith("http://") || fileTxt.startsWith("ftp://")){
			URL url = null;
			try {
				url = new URL(uploadTxt.getText());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			picture = new ImageIcon(url);
		}else{
			picture = new ImageIcon(uploadTxt.getText());
		}
		
		return picture;
		
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

	//	Listener
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
			}else if (e.getSource() == newImgBtn){
				newPubSlide();
				statusLbl.setText("Status: Slide saved");
			}else if (e.getSource() == showAllImgsBtn){
				readPubSlides();
				statusLbl.setText("Status: Slides loaded");
			}else if(e.getSource() == editImgBtn){
				statusLbl.setText("Status: You're now editing a slide");
			}else if (e.getSource() == delOneImgBtn){
				delOnePubSlide();
				statusLbl.setText("Status: Slide deleted");
			}else if (e.getSource() == delAllImgsBtn){
				delAllPubSlides();
				statusLbl.setText("Status: All slides deleted");
			}else if (e.getSource() == saveSlideBtn){
				saveSlide();
			}else if(e.getSource() == dscrdEditBtn){
				System.out.println("Under construction, dont discard!");
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
			}else if(e.getSource() == null){
				
			}

			
		}
	}
}
