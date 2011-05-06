import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
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
	public JLabel logInstrLbl;
	public JButton sbmBtn;
	public JButton clsBtn;
	public JTextField logUserTxt;
	public JTextField logPassTxt;
	public JFrame logFrame;
	Image bgImage;
	LoginListener listener;
	DesktopApplication controller;
	
	public LogInFrame(Image imgTmp, DesktopApplication tmpContr){
		controller = tmpContr;
		bgImage = imgTmp;
		setup();
		
		
	}
	
	public void setup(){
		
		logFrame = new JFrame();
		JPanel logPanel = new JPanel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = -4955458211222040417L;

			public void paint(Graphics g){
				g.drawImage(bgImage, 0,0, this);
				setOpaque(false);
	    		super.paint(g);
	    		setOpaque(true);
	    		
			}
		};
		JLabel logInLbl = new JLabel();
		JLabel logUserLbl = new JLabel();
		JLabel logMailLbl = new JLabel();
		JLabel logPassLbl = new JLabel();
		logInstrLbl = new JLabel();
		logUserTxt = new JTextField();
		logPassTxt = new JTextField();
		sbmBtn = new JButton();
		clsBtn = new JButton();
		GroupLayout logLayout = new GroupLayout(logPanel);
		listener = new LoginListener();
		
		logFrame.setLocation(450, 250);
		logFrame.setSize(250, 170);
		logFrame.setTitle("KisTalk Login");
		logFrame.setResizable(false);
		logFrame.setUndecorated(true);
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
		
		logUserTxt.setText("androse");
		logPassTxt.setText("6dd3vr1xil");
		
		sbmBtn.setText("Submit");
		sbmBtn.addActionListener(listener);
		clsBtn.setText("Close");
		clsBtn.addActionListener(listener);
		
		logLayout.setHorizontalGroup(
				logLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(logLayout.createSequentialGroup()
					.addGap(95)
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
							.addComponent(clsBtn, 80, 80, 80)
							.addGap(10)
							.addComponent(sbmBtn, 80, 80, 80)
					).addGroup(logLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(logInstrLbl)
					)
		);
		
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
		
		logFrame.add(logPanel);
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