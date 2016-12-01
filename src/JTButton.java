import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

public class JTButton extends JButton {

	public JTButton(String title, ActionListener listener) {
		super(title);
		addActionListener(listener);
	}
}
