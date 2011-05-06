import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

	//	Creates a pop-up
public class PopupFrame{
	public JButton sbmBtn;
	public JButton clsBtn;
	public JLabel label;
	public JFrame popFrame;
	public JTextField popTxt;
	public Font bldFont;
	public DesktopApplication controller;
	FrameListener framelistener;
	Image bgImage;
	ButtonListener listener;
	
	public PopupFrame(String messageTmp, Image tmpImg, DesktopApplication tmpContr, Font tmpFont, FrameListener tmplistener){
		bldFont = tmpFont;
		bgImage = tmpImg;
		controller = tmpContr;
		framelistener = tmplistener;
		setup(messageTmp);
	}
	
	public void setup(String message){
		
		listener = new ButtonListener();
		
		popFrame = new JFrame();
		JPanel popPanel = new JPanel(){
			private static final long serialVersionUID = -8640480719459300882L;

			public void paint(Graphics g){
				g.drawImage(bgImage, 0,0, this);
				setOpaque(false);
	    		super.paint(g);
	    		setOpaque(true);
	    		
			}
		};
		label = new JLabel();
		popTxt = new JTextField();
		sbmBtn = new JButton();
		clsBtn = new JButton();
		GroupLayout popLayout = new GroupLayout(popPanel);
		
		popFrame.setLocation(400, 270);
		popFrame.setSize(320, 140);
		popFrame.setTitle("KisTalk Popup");
		popFrame.setResizable(false);
		popFrame.setUndecorated(false);
		popFrame.addWindowListener(framelistener);
		popPanel.setLayout(popLayout);
		label.setText(message);
		label.setForeground(Color.WHITE);
		label.setFont(bldFont);
		sbmBtn.setText("Submit");
		sbmBtn.addActionListener(listener);
		clsBtn.setText("Close");
		clsBtn.addActionListener(listener);
		popTxt.addKeyListener(listener);
		if (message.equals("Are you sure you want to reset the config file?"))
			popTxt.setVisible(false);
		
		popLayout.setHorizontalGroup(
				popLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						
						.addGroup(popLayout.createSequentialGroup()
								.addGap(15)
								.addComponent(label)
						)
						.addGroup(popLayout.createSequentialGroup()
								.addGap(15)
								.addComponent(popTxt, 200, 200, 200)
						)
						.addGroup(popLayout.createSequentialGroup()
								.addGap(75)
								.addComponent(clsBtn, 90, 90, 90)
								.addGap(5)
								.addComponent(sbmBtn, 90, 90, 90)
						)
		);
		
		popLayout.setVerticalGroup(
				popLayout.createSequentialGroup()
				.addGap(10)
				.addComponent(label)
				.addGap(5)
				.addComponent(popTxt, 20, 20, 20)
				.addGap(20)
				.addGroup(popLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(clsBtn, 25, 25, 25)
						.addComponent(sbmBtn, 25, 25, 25)
				)
				
		);
		
		popFrame.add(popPanel);
		
	}
	
	private class ButtonListener implements ActionListener, KeyListener {
		public void keyPressed(KeyEvent e){
			if (e.getKeyCode() == KeyEvent.VK_ENTER){
				sbmBtn.doClick();
			}
		}
		
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == sbmBtn){
				if (label.getText().equals("Name the slideshow: ")){
					if (popTxt.getText().equals("") == false && popTxt.getText().length() <= 40){
						controller.savePath(popTxt.getText());
						popFrame.dispose();
					}else{
						controller.fail("Error", "The name must be between 1 and 40 chars!");
					}
				} else if (label.getText().equals("Are you sure you want to reset the config file?")){
					controller.resetConf();
					controller.closePop();
					popFrame.dispose();
				}
			}else if(e.getSource() == clsBtn){
				controller.closePop();
				popFrame.dispose();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}