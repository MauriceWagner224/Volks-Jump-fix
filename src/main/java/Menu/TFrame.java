package Menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Main.Game;
import Main.Window;

public class TFrame extends JPanel {
	
	public TFrame() {
		
		ButtonConfig();
	}

	Font pixelMplus;
	Image pic = Toolkit.getDefaultToolkit().getImage("res/Background/startmenu.gif");
	Image titelPicture = Toolkit.getDefaultToolkit().getImage("res/Background/title.png");
	JLabel j;
	JPanel buttons;
	JButton b1;
	JButton b2;
	JButton b3;

	public void ButtonConfig() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 8, 8, 8);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		buttons = new JPanel(new GridBagLayout());
		b1 = new BevelRoundedCornerButton("Start");
		b2 = new BevelRoundedCornerButton("About");
		b3 = new BevelRoundedCornerButton("Exit");
		
		j = new JLabel();
		j.setPreferredSize(new Dimension(300, 250));
		buttons.setOpaque(false);
		b1.setPreferredSize(new Dimension(350, 40));
		b1.setBackground(Color.decode("#150093"));
		b1.setForeground(Color.white);
		b2.setPreferredSize(new Dimension(350, 40));
		b2.setBackground(Color.decode("#150093"));
		b2.setForeground(Color.white);
		b3.setPreferredSize(new Dimension(350, 40));
		b3.setBackground(Color.decode("#150093"));
		b3.setForeground(Color.white);
		
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Window(Game.displayWidth, Game.displayHeight, "Volks-Jump-Wagen", new Game());	
				MenuFrame.dispose();
			}
		});
		
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime.getRuntime().exec("cmd /c start https://github.com/vw-wob-it-edu/volks-jump-wagen");
					} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
			}
		});
		
		
		b1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				b1.setBackground(Color.decode("#0f0065"));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				b1.setBackground(Color.decode("#150093"));
			}
		});
		b2.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				b2.setBackground(Color.decode("#0f0065"));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				b2.setBackground(Color.decode("#150093"));
			}
		});
		b3.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				b3.setBackground(Color.decode("#0f0065"));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				b3.setBackground(Color.decode("#150093"));
			}
		});
		buttons.add(j, gbc);
		buttons.add(b1, gbc);
		buttons.add(b2, gbc);
		buttons.add(b3, gbc);
		gbc.weighty = 1;
		add(buttons, gbc);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(pic, 0, 0, this.getWidth(), this.getHeight(), this);
		g.drawImage(titelPicture, Game.buttonWidth/2-400, Game.buttonHeight/3-25, 800, 200, this);
	}
//	public void paintComponen(Graphics g) {
//		super.paintComponent(g);
//		g.drawImage(TitelPicture, WIDTH, SOMEBITS, PROPERTIES, HEIGHT, FRAMEBITS, ERROR, ALLBITS, ABORT, b1);
//	}
}
