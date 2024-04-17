package Super;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import Enum.ObjectID;
import Enum.Type;

public abstract class GameObject {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected int ammocounter = 0;
	protected int enemiesCounter = 0;
	
	protected ObjectID id;
	
	protected float velX;
	protected float velY;
	
	protected boolean falling = true;
	protected boolean jumping = false;
	protected boolean alive = true;
	protected boolean shooting = false;
	protected boolean fright;
	protected boolean restart = false;
	private boolean destroyed = false;
	protected boolean win = false;

	protected Type type;
	
	public GameObject(int x, int y, int width, int height, ObjectID id, Type type) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
		this.type = type;
	}

	public abstract void render(Graphics g);
	public abstract void tick(List<GameObject>objectlist);
	public abstract Rectangle getBounds();
	public abstract void setAnimation(BufferedImage[]animation);
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public ObjectID getId() {
		return id;
	}

	public void setId(ObjectID id) {
		this.id = id;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}
		
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public boolean isShooting() {
		return jumping;
	}

	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}
	

	public boolean isFacingRight() {
		return fright;
	}

	public void setFacingRight(boolean facingRight) {
		this.fright = facingRight;
	}
	
	public int getAmmoCounter() {
		
		return ammocounter;
	}
	
	public void setAmmocounter(int ammocounter) {
		
		this.ammocounter = ammocounter;
	}
		
	public boolean getRestart() {
		return restart;
	}

	public void setRestart(boolean restart) {
		this.restart = restart;
	}
	
	public boolean isDestroyed() {
		return destroyed;		
	}
	
	public void destroy() {
		this.destroyed = true;
				
	}

	public boolean getWin() {
		return win;		
	}
	
	public void setWin() {
		this.win = true;
	}
}
