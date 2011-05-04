import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

	//	Creates a pop-up
public class FailFrame{
	public JButton okBtn;
	public JFrame errFrame;
	
	public FailFrame(){
		
		ButtonListener listener = new ButtonListener();
		
		//	Background Pic
		ImageIcon icon = new ImageIcon("bgIcon.png");
		final Image bgImage = icon.getImage();
		
		errFrame = new JFrame();
		JPanel errPanel = new JPanel(){ // Insert annan bild! Knapp ist för Kryss
			public void paint(Graphics g){
				g.drawImage(bgImage, 0,0, this);
				setOpaque(false);
	    		super.paint(g);
	    		setOpaque(true);
	    		repaint();
	    		
			}
		};

		JLabel label = new JLabel();
		okBtn = new JButton();
		GroupLayout errLayout = new GroupLayout(errPanel);
		
		errFrame.setLocation(400, 270);
		errFrame.setSize(300, 100);
		errFrame.setTitle("Login Error");
		errFrame.setResizable(false);
		errFrame.setUndecorated(true);
		label.setText("Wrong username and/or token. Try again.");
		label.setForeground(Color.WHITE);
		okBtn.setText("Ok");
		okBtn.addActionListener(listener);
		
		errLayout.setHorizontalGroup(
				errLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						
						.addGroup(errLayout.createSequentialGroup()
								.addGap(10)
								.addComponent(label)
						)
						.addGroup(errLayout.createSequentialGroup()
								.addGap(70)
								.addComponent(okBtn, 90, 90, 90)
						)
		);
		
		errLayout.setVerticalGroup(
				errLayout.createSequentialGroup()
				.addGap(10)
				.addGroup(errLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label)
				)
				.addGap(10)
				.addGroup(errLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(okBtn, 25, 25, 25)
				)
				
		);
		
		errFrame.add(errPanel);
		
	}
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == okBtn){
				errFrame.dispose();
			}
		}
	}
}