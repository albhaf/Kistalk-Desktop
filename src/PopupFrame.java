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
public class PopupFrame{
	public JButton sbmBtn;
	public JButton clsBtn;
	public JLabel label;
	public JFrame popFrame;
	public TextField popTxt;
	public DesktopApplication controller;
	
	public PopupFrame(String messageTmp, DesktopApplication contrTmp){
		controller = contrTmp;
		setup(messageTmp);
	}
	
	public void setup(String message){
		
		ButtonListener listener = new ButtonListener();
		
		//	Background Pic
		ImageIcon icon = new ImageIcon("bgIcon.png");
		final Image bgImage = icon.getImage();
		
		popFrame = new JFrame();
		JPanel popPanel = new JPanel(){ // Insert annan bild! Knapp ist för Kryss
			public void paint(Graphics g){
				g.drawImage(bgImage, 0,0, this);
				setOpaque(false);
	    		super.paint(g);
	    		setOpaque(true);
	    		repaint();
	    		
			}
		};
		label = new JLabel();
		popTxt = new TextField();
		sbmBtn = new JButton();
		clsBtn = new JButton();
		GroupLayout popLayout = new GroupLayout(popPanel);
		
		popFrame.setLocation(400, 270);
		popFrame.setSize(400, 100);
		popFrame.setTitle("KisTalk Popup");
		popFrame.setResizable(false);
		popFrame.setUndecorated(true);
		popPanel.setLayout(popLayout);
		label.setText(message);
		label.setForeground(Color.WHITE);
		sbmBtn.setText("Submit");
		sbmBtn.addActionListener(listener);
		clsBtn.setText("Close");
		clsBtn.addActionListener(listener);
		
		popLayout.setHorizontalGroup(
				popLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						
						.addGroup(popLayout.createSequentialGroup()
								.addGap(10)
								.addComponent(label)
								.addGap(5)
								.addComponent(popTxt, 200, 200, 200)
						)
						.addGroup(popLayout.createSequentialGroup()
								.addGap(120)
								.addComponent(sbmBtn, 90, 90, 90)
								.addGap(5)
								.addComponent(clsBtn, 90, 90, 90)
						)
		);
		
		popLayout.setVerticalGroup(
				popLayout.createSequentialGroup()
				.addGap(10)
				.addGroup(popLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label)
						.addComponent(popTxt, 20, 20, 20)
				)
				.addGap(10)
				.addGroup(popLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(sbmBtn, 25, 25, 25)
						.addComponent(clsBtn, 25, 25, 25)
				)
				
		);
		
		popFrame.add(popPanel);
		
	}
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == sbmBtn){
				controller.savePath(popTxt.getText());
				
			}else if(e.getSource() == clsBtn){
				popFrame.dispose();
			}
		}
	}
}