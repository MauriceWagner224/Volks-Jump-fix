package GameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import EntityObjects.Player;
import Enum.ObjectID;
import Enum.Type;
import Handler.AnimationHandler;
import Handler.ObjectHandler;
import Super.GameObject;

public class Ammo extends GameObject {

	AnimationHandler animationHandler;
	ObjectHandler objectHandler;
	
	public static BufferedImage[] test;

	private float gravity = 0.3f;
	private final int maxSpeed = 15;
	boolean shooting;
	boolean optionToFall;
	public boolean asd;
	boolean right;
	
	
	public Ammo(int x, int y, int width, int height, ObjectID id, Type type, ObjectHandler objectHandler, boolean optionToFall, boolean right) {
		super(x, y, width, height, id, type);
		// TODO Auto-generated constructor stub
		this.optionToFall = optionToFall;
		this.animationHandler = new AnimationHandler();
		this.objectHandler = objectHandler;
		this.right = right;
		
		animationHandler.setAnimation(test);
		animationHandler.setDelay(-1);
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
		g.drawImage(animationHandler.getImage(), x + 9, y + 30, width -5 , height -5, null);		
	}

	@Override
	public void tick(List<GameObject> objectlist) {
		// TODO Auto-generated method stub
		
		if (optionToFall == true) {
			this.y += this.velY;
			this.x += this.velX;
									
			
			
			
			if (falling && right == true) {
				
//				this.velX += 2;
//				this.y -=10;
				this.x += 30;
								
				this.velY += gravity;

				if (this.velY >= maxSpeed) {

					this.velY = maxSpeed;

				} 
			
			} else if(falling && right == false) {
				
				this.x -= 30;
				
				this.velY += gravity;

				if (this.velY >= maxSpeed) {

					this.velY = maxSpeed;

				}
			}
		}
		
		collisions(objectlist);
		animationHandler.tick();
	}
	
	public void collisions(List<GameObject>objectList) {
		// TODO Auto-generated method stub
		synchronized (objectHandler.objectlist) {
			
		ObjectID[] ammoDestroyingObjects = {ObjectID.BLOCK, ObjectID.TEST_ENEMY, ObjectID.WATER};
		
		for(int i = 0; i < objectHandler.objectlist.size(); i++) {
			GameObject tempObject = objectHandler.objectlist.get(i);
							
			if(Arrays.asList(ammoDestroyingObjects).contains(tempObject.getId())) {
				
				if(getBounds().intersects(tempObject.getBounds())) {
					
					if(!(tempObject.getId() == ObjectID.TEST_ENEMY && !tempObject.isAlive())) {
						
						tempObject.setAlive(false);
						this.destroy();
					
					}
				}
			}
		}
		}
	}
			
	@Override
	public void setAnimation(BufferedImage[] animation) {
		// TODO Auto-generated method stub
		
	}
	
	public Rectangle getBounds() {
		
		return new Rectangle(x, y, width, height);
	}
	
	public Rectangle getBoundsBottom() {
		// TODO Auto-generated method stub
		return new Rectangle((int) ((int) x + (width / 2) - ((width / 2) / 2)),
				(int) ((int) y + height / 2), (int) width / 2, (int) height / 2); 
	}
	
	public Rectangle getBoundsTop() {
		// TODO Auto-generated method stub
		return new Rectangle((int) ((int) x + (width / 2) - ((width / 2) / 2)),
				(int) y, (int) width / 2, (int) height / 2); 
	}
	
	public Rectangle getBoundsRight() {
		// TODO Auto-generated method stub
		return new Rectangle((int) ((int) x + width - 5), (int) y + 5,
				(int) 5, (int) height - 10); 
	}
	
	public Rectangle getBoundsLeft() {
		// TODO Auto-generated method stub
		return new Rectangle((int) x, (int) y + 5, (int) 5, (int) height - 10); 
	}
	
}
