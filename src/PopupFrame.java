import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
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
	Image bgImage;
	ButtonListener listener;
	
	public PopupFrame(String messageTmp, Image tmpImg, DesktopApplication contrTmp){
		bgImage = tmpImg;
		controller = contrTmp;
		setup(messageTmp);
	}
	
	public void setup(String message){
		
		listener = new ButtonListener();
		
		popFrame = new JFrame();
		JPanel popPanel = new JPanel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = -8640480719459300882L;

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
		popFrame.setUndecorated(false);
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
								.addGap(136)
								.addComponent(clsBtn, 90, 90, 90)
								.addGap(5)
								.addComponent(sbmBtn, 90, 90, 90)
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
						.addComponent(clsBtn, 25, 25, 25)
						.addComponent(sbmBtn, 25, 25, 25)
				)
				
		);
		
		popFrame.add(popPanel);
		
	}
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == sbmBtn){
				if (popTxt.getText().equals("") == false){
					controller.savePath(popTxt.getText());
					popFrame.dispose();
				}else{
					//controller.Failframe();
				}
				
			}else if(e.getSource() == clsBtn){
				controller.closePop();
				popFrame.dispose();
			}
		}
	}
}