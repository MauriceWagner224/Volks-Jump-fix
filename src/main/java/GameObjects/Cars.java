package GameObjects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.List;

import Enum.ObjectID;
import Enum.Type;
import Super.GameObject;

public class Cars extends GameObject{

	int i;
		
	Image id5 = Toolkit.getDefaultToolkit().getImage("res/Sprites/id5.png");
	Image derby = Toolkit.getDefaultToolkit().getImage("res/Sprites/derby.png");
	Image passat = Toolkit.getDefaultToolkit().getImage("res/Sprites/passat.png");
	Image scirocco = Toolkit.getDefaultToolkit().getImage("res/Sprites/scirocco_broken_V2.gif");
	Image up = Toolkit.getDefaultToolkit().getImage("res/Sprites/up.png");
	Image roomster = Toolkit.getDefaultToolkit().getImage("res/Sprites/roomster.png");
	
	public Cars(int x, int y, int width, int height, ObjectID id, Type type, int i) {
		super(x, y, width, height, id, type);
		this.i = i;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g) throws NullPointerException {
		// TODO Auto-generated method stub

		switch(i) {
		
		case 54: //id5
			g.drawImage(id5, x + 64 * 2, y - 62, width * 5, height * 2, null);
			break;
		
		case 51: //derby
			g.drawImage(derby, x, y - 62, width * 4, height * 2, null);
			break;
			
		case 56: //passat
			g.drawImage(passat, x + 64 * 2, y + (64 * 4 - 10), width * 5, height * 2, null);
			break;
			
		case 59: //scirocco
			g.drawImage(scirocco, x, y - 62, width * 4, height * 2, null);
			break;
			
		case 61: //up
			g.drawImage(up, x, y - 62, width * 3, height * 2, null);
			break;
			
		case 69: //roomster
			g.drawImage(roomster, x, y - 60, width * 4, height * 2, null);
			break;	
		
		default:
			throw new NullPointerException();
			
		}
		

	}

	@Override
	public void tick(List<GameObject> objectlist) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAnimation(BufferedImage[] animation) {
		// TODO Auto-generated method stub
		
	}

}
