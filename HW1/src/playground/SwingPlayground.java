package playground;

import javax.swing.JDialog;
import javax.swing.JFrame;


public class SwingPlayground {
	public static void main(String[] args) {
		JFrame frame = new JFrame("My Frame");
		frame.setSize(400,400);
		frame.setVisible(true);
		
		JDialog dlg = new JDialog(frame);
		dlg.setSize(400, 400);
		dlg.setVisible(true);
	}
}
