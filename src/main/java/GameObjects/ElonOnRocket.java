package GameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import Enum.ObjectID;
import Enum.Type;
import Handler.AnimationHandler;
import Super.GameObject;

public class ElonOnRocket extends GameObject{

	Image elonOnRocketGIF = Toolkit.getDefaultToolkit().getImage("res/Sprites/elon.gif");
	Image elonOnRocketGIFRight = Toolkit.getDefaultToolkit().getImage("res/Sprites/elonRight.gif");
	
	AnimationHandler animationHandler;
	
	boolean travellingLeft = true;
	
	int yOffset = y;
	int mapWidth;
	double rotation;
	
	public ElonOnRocket(int x, int y, int width, int height, ObjectID id, Type type) {
		super(x, y, width, height, id, type);
		// TODO Auto-generated constructor stub
		this.animationHandler = new AnimationHandler();	
		this.yOffset = y;
		elonOnRocketGIF = elonOnRocketGIF.getScaledInstance(width + 400, height + 200, 1);
		elonOnRocketGIFRight = elonOnRocketGIFRight.getScaledInstance(width + 400, height + 200, 1);
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
		AffineTransform transform = new AffineTransform();
		transform.translate(x, y + yOffset);
		transform.rotate(this.rotation);
		transform.translate(elonOnRocketGIF.getWidth(null) / -2, elonOnRocketGIF.getHeight(null) / -2);
			
		Graphics2D g2d = (Graphics2D) g;
				
		if(travellingLeft) {
			
			g2d.drawImage(elonOnRocketGIF, transform, null);
		
		} else if(!travellingLeft) {
		
			g2d.drawImage(elonOnRocketGIFRight, transform, null);

		}

	}

	@Override
	public void tick(List<GameObject> objectlist) {
		// TODO Auto-generated method stub

	if(x > mapWidth) {
		
		travellingLeft = true;
	
	} else if(x <= 0) {
		
		travellingLeft = false;
	}
		
		if(travellingLeft) {
			
			x -= 4;
			
		} else {
			
			x += 4;
		}
		
		y = (int) Math.round(Math.sin(x * 0.01) * 75);
		rotation = (Math.atan(Math.cos(x * 0.01) * 0.75));
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

	public void setMapSize(int nr) {
		
		this.mapWidth = nr * 64;
	}	
}
