package GameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import EntityObjects.Player;
import Enum.ObjectID;
import Enum.Type;
import Handler.ObjectHandler;
import Super.GameObject;

public class LittleBox extends GameObject{
	
	ObjectHandler objectHandler;
	Player test;
	
	public static BufferedImage img;

	public LittleBox(int x, int y, int width, int height, ObjectID id, Type type, ObjectHandler objectHandler) {
		super(x, y, width, height, id, type);
		this.objectHandler = objectHandler;
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
		return new Rectangle(x, y, width, height);
	}

	@Override
	public void setAnimation(BufferedImage[] animation) {
		// TODO Auto-generated method stub
		
	}
	
	public Rectangle getBoundsTop() {
		// TODO Auto-generated method stub
		return new Rectangle((int) ((int) x + (width / 2) - ((width / 2) / 2)), (int) y, (int) width / 2,
				(int) height / 2);
	}

	public Rectangle getBoundsRight() {
		// TODO Auto-generated method stub
		return new Rectangle((int) ((int) x + width - 5), (int) y + 5, (int) 5, (int) height - 10);
	}

	public Rectangle getBoundsLeft() {
		// TODO Auto-generated method stub
		return new Rectangle((int) x, (int) y + 5, (int) 5, (int) height - 10);
	}
	
	
}
