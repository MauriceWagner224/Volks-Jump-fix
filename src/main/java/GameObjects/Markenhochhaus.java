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

public class Markenhochhaus extends GameObject {
	
	Image markenhochhaus = Toolkit.getDefaultToolkit().getImage("res/Sprites/markenhochhausv2.png");

	public Markenhochhaus(int x, int y, int width, int height, ObjectID id, Type type) {
		super(x, y, width, height, id, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(markenhochhaus, x, y, width * 8, height * 18, null);
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
