package GameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import Enum.ObjectID;
import Enum.Type;
import Super.GameObject;

public class PlatformMiddle extends GameObject{
	
	public static BufferedImage img;

	public PlatformMiddle(int x, int y, int width, int height, ObjectID id, Type type) {
		super(x, y, width, height, id, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
		g.drawImage(img, x, y, width, height, null);		
	
	}

	@Override
	public void tick(List<GameObject> objectlist) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, width, height - 54);
	}

	@Override
	public void setAnimation(BufferedImage[] animation) {
		// TODO Auto-generated method stub
		
	}

	
	
}
