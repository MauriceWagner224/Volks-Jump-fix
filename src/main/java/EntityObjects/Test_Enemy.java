package EntityObjects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import Enum.ObjectID;
import Enum.Type;
import Handler.AnimationHandler;
import Handler.ObjectHandler;
import Main.Game;
import Super.GameObject;

public class Test_Enemy extends GameObject {

	Game test;
	
	private float gravity = 0.3f;
	private final int maxSpeed = 15;

	public static BufferedImage[] walkingAnimation;
	public static BufferedImage[] idleAnimation;
	public static BufferedImage[] dieAnimation;
	public static BufferedImage[] transformedAnimation;
	
	ObjectHandler objectHandler;
	AnimationHandler animationHandler;
	GameObject gameobject;

	float timer;
	Random random;

	private boolean facingRight;

	
	public Test_Enemy(int x, int y, int width, int height, ObjectID id, ObjectHandler objectHandler, Type type) {
		super(x, y, width, height, id, type);
		// TODO Auto-generated constructor stub
		this.objectHandler = objectHandler;
		this.animationHandler = new AnimationHandler();
		this.random = new Random();
		this.test = new Game();

		facingRight = true;

		animationHandler.setAnimation(walkingAnimation);
		animationHandler.setDelay(200);

		int temp = random.nextInt(2);

		if (temp == 0) {

			velX = 3;

		} else if (temp == 1) {

			velX = -3;

		}
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub

		if (facingRight) {

			g.drawImage(animationHandler.getImage(), x, y, width, height, null);

		} else if (!facingRight) {

			g.drawImage(animationHandler.getImage(), x + width, y, -width, height, null);

		}
	}

	@Override
	public void tick(List<GameObject> objectlist) {
		// TODO Auto-generated method stub
		
		if(alive == true) {
		this.x += this.velX;		
		
		} else {
		
			this.y += this.velY;
			animationHandler.setAnimation(transformedAnimation);
		}					
		
				if (falling || jumping) {

					this.velY = velY + gravity;

					if (this.velY >= maxSpeed) {

						this.velY = maxSpeed;

					}
				}

				if (velX > 0) {

					facingRight = true;

				} else if (velX < 0) {

					facingRight = false;

				}
				
				if(alive == false && y == 1088) {
					
					velY = -5;
				} 
			
		animationHandler.tick();
		collisions(objectlist);

	}
	
	public void collisions(List<GameObject> objectList) {
		// TODO Auto-generated method stub
		synchronized (objectHandler.objectlist) {
		
		for (int i = 0; i < objectHandler.objectlist.size(); i++) {
			GameObject tempObject = objectHandler.objectlist.get(i);

			if (tempObject.getId() == ObjectID.BLOCK) {

				if (getBoundsBottom().intersects(tempObject.getBounds())) {

					y = tempObject.getY() - tempObject.getHeight();
					velY = 0;
					falling = false;
					jumping = false;

				} else {

					falling = true;

				}

				if (getBoundsRight().intersects(tempObject.getBounds())) {

					x = tempObject.getX() - tempObject.getWidth();
					velX = velX * -1;

				}

				if (getBoundsLeft().intersects(tempObject.getBounds())) {

					x = tempObject.getX() + tempObject.getWidth();
					velX = velX * -1;

				}

				if (getBoundsTop().intersects(tempObject.getBounds())) {

					y = tempObject.getY() + tempObject.getHeight();
					velY = 0;

				}
			} 		
		}
		}
	}

	public Rectangle getBounds() {

		return new Rectangle(x, y, width, height);

	}

	public Rectangle getBoundsBottom() {
		// TODO Auto-generated method stub
		return new Rectangle((int) ((int) x + (width / 2) - ((width / 2) / 2)), (int) ((int) y + height / 2),
				(int) width / 2, (int) height / 2);
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

	@Override
	public void setAnimation(BufferedImage[] animation) {
		// TODO Auto-generated method stub

		animationHandler.setAnimation(animation);
		animationHandler.setDelay(500);

	}

}
