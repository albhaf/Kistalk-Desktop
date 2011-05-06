import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LogInFrame{
	private CreateNewElements create;
	private JPanel logPanel;
	public JLabel logInstrLbl; 
	private JLabel logInLbl, logUserLbl, logMailLbl, logPassLbl;
	private JButton sbmBtn, clsBtn;
	private JTextField logUserTxt, logPassTxt;
	public JFrame logFrame;
	private Image bgImage;
	private LoginListener listener;
	private DesktopApplication controller;
	private GroupLayout logLayout;
	
	
	public LogInFrame(Image imgTmp, DesktopApplication tmpContr){
		create = new CreateNewElements();
		controller = tmpContr;
		bgImage = imgTmp;
		setup();
		
		
	}
	
	public void setup(){
		listener = new LoginListener();
		logFrame = new JFrame();
		logPanel = create.setNewPanel(bgImage);

		Font stdFont = new Font("Imperial", Font.PLAIN, 12);
		
		logInLbl = create.setNewLabel("LogIn", new Font("Imperial", Font.BOLD, 22));		
		logUserLbl = create.setNewLabel("Username: ", stdFont);
		logMailLbl = create.setNewLabel("@kth.se", stdFont);
		logPassLbl = create.setNewLabel("Token: ", stdFont);
		logInstrLbl = create.setNewLabel("To get your token, log in to kistalk.com", new Font("Imperial", Font.ITALIC, 10));
		
		
		logUserTxt = create.setNewTextField("androse", stdFont, true);
		logPassTxt = create.setNewTextField("6dd3vr1xil", stdFont, true);
		sbmBtn = create.setNewButton("Submit", listener);
		clsBtn = create.setNewButton("Close", listener);
		logLayout = new GroupLayout(logPanel);
		
		logFrame.setLocation(450, 250);
		logFrame.setSize(280, 180);
		logFrame.setTitle("KisTalk Login");
		logFrame.setResizable(false);
		logFrame.setUndecorated(true);
		logFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		logPanel.setLayout(logLayout);
		
		this.setHorizontal();
		this.setVertical();
		
		
		logFrame.add(logPanel);
	}
	
	private void setVertical(){
		logLayout.setVerticalGroup(
				logLayout.createSequentialGroup()
				.addGap(10)
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
						.addComponent(clsBtn, 25, 25, 25)
						.addComponent(sbmBtn, 25, 25, 25)
				)
				.addGap(20)
				.addComponent(logInstrLbl)
				
		);
		
	}
	
	private void setHorizontal(){
		logLayout.setHorizontalGroup(
				logLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(logLayout.createSequentialGroup()
					.addGap(100)
					.addComponent(logInLbl)
					)
					.addGap(15)
					.addGroup(logLayout.createSequentialGroup()
							.addGap(20)
							.addComponent(logUserLbl)
							.addGap(10)
							.addComponent(logUserTxt, 100, 100, 100)
							.addGap(3)
							.addComponent(logMailLbl)
					)
					.addGroup(logLayout.createSequentialGroup()
							.addGap(20)
							.addComponent(logPassLbl)
							.addGap(36)
							.addComponent(logPassTxt, 100, 100, 100)
					)
					.addGroup(logLayout.createSequentialGroup()
							.addGap(55)
							.addComponent(clsBtn, 80, 80, 80)
							.addGap(15)
							.addComponent(sbmBtn, 80, 80, 80)
					).addGroup(logLayout.createSequentialGroup()
							.addGap(20)
							.addComponent(logInstrLbl)
					)
		);

	}
	
	public void clooose(){
		this.logFrame.dispose();
	}
	
	private class LoginListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == sbmBtn){
				controller.login(logUserTxt.getText(), logPassTxt.getText(), logFrame);
			}else if (e.getSource() == clsBtn){
				logFrame.dispose();
			}
		}
	}
}