package Menu;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MenuFrame {
	
	public MenuFrame(){
		frame();
	}
	
	TFrame T = new TFrame();
	static JFrame f = new JFrame();

	public void frame() {
		
		ImageIcon img = new ImageIcon("res/Sprites/mc_with_weapon.png");
		f.setIconImage(img.getImage());
		f.add(T);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Volks-Jump-Wagen");
		f.setVisible(true);

	}

	public static void dispose() {
		// TODO Auto-generated method stub
		f.dispose();
	}

}
