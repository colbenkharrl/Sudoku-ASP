import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

public class JTButton extends JButton {
	
	char type;

	public JTButton(String title, ActionListener listener, char type) {
		super(title);
		this.type = type;
		addActionListener(listener);
		setForeground(Color.BLACK);
		setBackground(Color.WHITE);
		setFont(new Font("Monospaced", 1, 12));
		if (type == 'h') {
			setBorderPainted(false);
			setFont(new Font("Monospaced", 1, 24));
		} else if (type == 'b') {
			setPreferredSize(new Dimension(100, 100));
		}
		setOpaque(true);
	}
	
	public void setColor(Color t, Color b) {
		setBackground(b);
		setForeground(t);
	}
}
