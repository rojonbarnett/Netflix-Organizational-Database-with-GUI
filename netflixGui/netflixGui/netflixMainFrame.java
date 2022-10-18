package netflixGui;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.color.*;
//RoJon Barnett
//A GUI program that allows a user to view and manipulate Netflix data
public class netflixMainFrame extends JPanel {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Netflix Panel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		netflixPanel panel = new netflixPanel();
		frame.getContentPane().add(panel);
		
		frame.pack();
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				panel.doClose();
			}
		});

	}

}
