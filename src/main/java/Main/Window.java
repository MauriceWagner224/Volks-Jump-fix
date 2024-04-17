package Main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Inputs.MouseEvents;

public class Window {
	
	public JFrame frame;
	private Dimension dimension;
	
	public Window(int width, int height, String title, Game game) {
		
		frame = new JFrame(title);
		dimension = new Dimension(width, height);
		ImageIcon icon = new ImageIcon("res/Sprites/mc_with_weapon.png");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setIconImage(icon.getImage());		

		frame.add(game);

		frame.setVisible(true);
		
		game.start();
	}
	
}
