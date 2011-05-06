import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
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
}
