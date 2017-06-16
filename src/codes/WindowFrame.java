package codes;

import java.awt.Color;

import javax.swing.JFrame;

public class WindowFrame extends JFrame {

	static final long serialVersionUID = 20L; // Associates a serial version number to the class

	public WindowFrame(int height, int width, String title) {
		super(title);// Names the JFrame Object
		setSize(height, width);// Sets the window size.
		setResizable(false); // Makes the window unresizable
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Window & Program Closes when you click X
		setLocationRelativeTo(null); // Centers the Window on the screen.
		this.getContentPane().setBackground(Color.white);
		// setVisible(true); // Sets the window to be visible.

	}

}
