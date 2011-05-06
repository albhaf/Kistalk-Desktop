import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreateNewElements {

	protected CreateNewElements() {
	}

	protected JLabel setNewLabel(String text, Font font) {
		JLabel tmp = new JLabel();
		tmp.setText(text);
		tmp.setForeground(Color.WHITE);
		tmp.setFont(font);
		return tmp;
	}
	
	protected JButton setNewButton(String text, ActionListener listener){
		JButton tmp = new JButton();
		tmp.setText(text);
		tmp.setForeground(Color.BLACK);
		tmp.addActionListener(listener);
		
		return tmp;
	}
	
	protected JTextField setNewTextField(String text, Font font, boolean enabled){
		JTextField tmp = new JTextField();
		tmp.setText(text);
		tmp.setFont(font);
		tmp.setEnabled(enabled);
		return tmp;
	}
	
	protected JCheckBox setNewCheckBox(String text, ActionListener listener, boolean enabled){
		JCheckBox tmp = new JCheckBox();

		tmp.setText(text);
		tmp.setForeground(Color.WHITE);
		tmp.addActionListener(listener);
		tmp.setOpaque(false);
		tmp.setEnabled(enabled);
		
		return tmp;
	}

	protected JPanel setNewPanel(final Image bgImage){
		JPanel tmp = new JPanel(){ //Observera att detta �r en create!
			private static final long serialVersionUID = -7374793566417261848L;

			public void paint(Graphics g){
				g.drawImage(bgImage, 0,0, this);
				setOpaque(false);
	    		super.paint(g);
	    		setOpaque(true);
	    		
			}
		};

		tmp.setBackground(Color.decode("#ae0808"));
		
		
		return tmp;
	}
	
}
