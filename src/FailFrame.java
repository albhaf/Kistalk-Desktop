import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
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
	
	public FailFrame(String title, String message, Font bldFont){
		
		ButtonListener listener = new ButtonListener();
		
		//	Background Pic
		ImageIcon icon = new ImageIcon("bgIcon.png");
		final Image bgImage = icon.getImage();
		
		errFrame = new JFrame();
		JPanel errPanel = new JPanel(){ // Insert annan bild! Knapp ist för Kryss
			private static final long serialVersionUID = -5083318046876650701L;

			public void paint(Graphics g){
				g.drawImage(bgImage, 0,0, this);
				setOpaque(false);
	    		super.paint(g);
	    		setOpaque(true);
	    		
			}
		};
		
		JLabel label = new JLabel();
		okBtn = new JButton();
		GroupLayout errLayout = new GroupLayout(errPanel);
		
		errFrame.setLocation(430, 290);
		errFrame.setSize(320, 100);
		errFrame.setTitle(title);
		errFrame.setResizable(false);
		errFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		errPanel.setLayout(errLayout);
		label.setText(message);
		label.setForeground(Color.WHITE);
		label.setFont(bldFont);
		okBtn.setText("OK");
		okBtn.addActionListener(listener);
		
		errLayout.setHorizontalGroup(
				errLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
						
						.addGroup(errLayout.createSequentialGroup()
								.addGap(20)
								.addComponent(label)
						)
						.addGroup(errLayout.createSequentialGroup()
								.addGap(30)
								.addComponent(okBtn, 100, 100, 100)
						)
		);
		
		errLayout.setVerticalGroup(
				errLayout.createSequentialGroup()
				.addGap(10)
				.addComponent(label)
				.addGap(10)
				.addComponent(okBtn, 25, 25, 25)
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