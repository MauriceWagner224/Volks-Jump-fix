package GameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import Enum.ObjectID;
import Enum.Type;
import Handler.AnimationHandler;
import Super.GameObject;

public class Currywurst extends GameObject{
	
	AnimationHandler animationHandler;
	
	public static BufferedImage[] currywurstAnimation;	
	
	private float gravity = 0.3f;
	private final int maxSpeed = 15;
	
	private boolean optionToFall;

	public Currywurst(int x, int y, int width, int height, ObjectID id, Type type, boolean optionToFall) {
		super(x, y, width, height, id, type);
		this.optionToFall = optionToFall;
		this.animationHandler = new AnimationHandler();
		
		animationHandler.setAnimation(currywurstAnimation);
		animationHandler.setDelay(-1);
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(animationHandler.getImage(), x + 9, y + 27, width, height, null);
	}

	@Override
	public void tick(List<GameObject> objectlist) {
		// TODO Auto-generated method stub
		
		if (optionToFall == true) {
			this.y += this.velY;

			if (falling || jumping) {

				this.velY += gravity;

				if (this.velY >= maxSpeed) {

					this.velY = maxSpeed;

				}
			}
		}
		animationHandler.tick();
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
