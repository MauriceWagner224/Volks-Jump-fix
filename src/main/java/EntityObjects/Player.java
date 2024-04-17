package EntityObjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import Enum.ObjectID;
import Enum.Type;
import GameObjects.Ammo;
import Handler.AnimationHandler;
import Handler.ObjectHandler;
import Super.GameObject;

public class Player extends GameObject {
	
	private float gravity = 0.3f;
	private final int maxSpeed = 15;
	
	public static BufferedImage[] idleAnimation;
	public static BufferedImage[] walkingAnimation;
	public static BufferedImage[] jumpingAnimation;
	public static BufferedImage[] dieAnimation;
	public static BufferedImage[] shootAnimation;
	
	public boolean facingRight;
	
	ObjectHandler objectHandler;
	AnimationHandler animationHandler;
	
	public Player(int x, int y, int width, int height, ObjectID id, ObjectHandler objectHandler, Type type) {
		super(x, y, width, height, id, type);
		this.objectHandler = objectHandler;
		this.animationHandler = new AnimationHandler();
		
		animationHandler.setAnimation(idleAnimation);
		animationHandler.setDelay(-1);
		facingRight = true;
				
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
		if(facingRight) {
		
			g.drawImage(animationHandler.getImage(), x, y, width, height, null);
		
		} else if(!facingRight) {
			
			g.drawImage(animationHandler.getImage(), x + width, y, -width, height, null);

		}

	}

	@Override
	public void tick(List<GameObject> objectlist) {
		// TODO Auto-generated method stub
				
		if(alive == true) {
		this.x += this.velX;
		}
		this.y += this.velY;
		
		if(alive == true){
		if(falling || jumping) {
			
			this.velY += gravity;
			
			if(this.velY >= maxSpeed) {
				
				this.velY = maxSpeed;
				
			}
		}

		
		if(velX != 0 && !jumping) {
			
			animationHandler.setAnimation(walkingAnimation);
			animationHandler.setDelay(160);
			
		} else if(velX == 0 && !jumping) {
			
			animationHandler.setAnimation(idleAnimation);
			animationHandler.setDelay(-1);

		} else if(jumping) {
			
			animationHandler.setAnimation(jumpingAnimation);
			animationHandler.setDelay(-1);
		
		} 
		
		if(shooting == true && velX == 0)	{
			
			animationHandler.setAnimation(shootAnimation);
			animationHandler.setDelay(-1);
		}	
		
		if(velX > 0) {
			
			facingRight = true;
			setFacingRight(true); 
			
		} else if (velX < 0){
			
			facingRight = false;
			setFacingRight(false);
			
		}
			collisions(objectlist);
		}
		animationHandler.tick();
	}

	public void collisions(List<GameObject> objectlist) {
		// TODO Auto-generated method stub
		synchronized (objectHandler.objectlist) {		
		
		for(int i = 0; i < objectHandler.objectlist.size(); i++) {
			GameObject tempObject = objectHandler.objectlist.get(i);
							
			if(tempObject.getId() == ObjectID.BLOCK) {
				
				if(getBounds().intersects(tempObject.getBounds())) {
					
					y = tempObject.getY() - tempObject.getHeight();
					velY = 0;
					falling = false;
					jumping = false;
				
				}	else {
					
					falling = true;
					
				}
				
				if(getBoundsRight().intersects(tempObject.getBounds())) {
					
					x = tempObject.getX() - tempObject.getWidth();
					velX = 0;					
					
				}
				
				if(getBoundsLeft().intersects(tempObject.getBounds())) {
					
					x = tempObject.getX() + tempObject.getWidth();
					velX = 0;					
					
				}
				
				if(getBoundsTop().intersects(tempObject.getBounds())) {
					
					y = tempObject.getY() + tempObject.getHeight();
					velY = 0;
										
				}
			
			} else if(tempObject.getId() == ObjectID.TEST_ENEMY) {
				
				if(tempObject.isAlive() == true) {
				
				if(getBounds().intersects(tempObject.getBounds())) {
					
					tempObject.setAnimation(Test_Enemy.transformedAnimation);
					tempObject.setAlive(false);
					return;					
				}
				
				if(getBoundsRight().intersects(tempObject.getBounds())) {
					
					die();
					return;
				}
				
				if(getBoundsLeft().intersects(tempObject.getBounds())) {
					
					die();
					return;
				}
				
				if(getBoundsTop().intersects(tempObject.getBounds())) {
					
					die();
					return;
					}
				}
			
			} else if(tempObject.getId() == ObjectID.CURRYWURST) {
				
				if(getBounds().intersects(tempObject.getBounds())) {
					
					tempObject.destroy();
					setAmmocounter(ammocounter += 1);
					
				}
				
				if(getBoundsRight().intersects(tempObject.getBounds())) {
					
					tempObject.destroy();
					setAmmocounter(ammocounter += 1);

				}
				
				if(getBoundsLeft().intersects(tempObject.getBounds())) {
					
					tempObject.destroy();
					setAmmocounter(ammocounter += 1);
					
				}
				
				if(getBoundsTop().intersects(tempObject.getBounds())) {
					
					tempObject.destroy();
					setAmmocounter(ammocounter += 1);

					}
			
			} else if(tempObject.getId() == ObjectID.WATER) {
				
				if(getBounds().intersects(tempObject.getBounds())) {
					
					die();
					return;					
				}
				
				if(getBoundsRight().intersects(tempObject.getBounds())) {
					
					die();
					return;
				}
				
				if(getBoundsLeft().intersects(tempObject.getBounds())) {
					
					die();
					return;
				}
				
				if(getBoundsTop().intersects(tempObject.getBounds())) {
					
					die();
					return;
				}
				
			} else if(tempObject.getId() == ObjectID.AMMOBLOCK) {
					
					if(getBounds().intersects(tempObject.getBounds())) {
						
						y = tempObject.getY() - tempObject.getHeight();
						velY = 0;
						falling = false;
						jumping = false;
					
					}	else {
						
						falling = true;
						
					}
					
					if(getBoundsRight().intersects(tempObject.getBounds())) {
						
						x = tempObject.getX() - tempObject.getWidth();
						velX = 0;					
						
					}
					
					if(getBoundsLeft().intersects(tempObject.getBounds())) {
						
						x = tempObject.getX() + tempObject.getWidth();
						velX = 0;					
						
					}
					
					if(getBoundsTop().intersects(tempObject.getBounds())) {
						
						y = tempObject.getY() + tempObject.getHeight();
						velY = 0;
											
					}				
				} else if(tempObject.getId() == ObjectID.MISSIONBLOCK) {
					
					if(objectHandler.lvlPassed == false) {
					
					if(getBounds().intersects(tempObject.getBounds())) {
						
						y = tempObject.getY() - tempObject.getHeight();
						velY = 0;
						falling = false;
						jumping = false;
					
					}	else {
						
						falling = true;
						
					}
					
					if(getBoundsRight().intersects(tempObject.getBounds())) {
						
						x = tempObject.getX() - tempObject.getWidth();
						velX = 0;					
						
					}
					
					if(getBoundsLeft().intersects(tempObject.getBounds())) {
						
						x = tempObject.getX() + tempObject.getWidth();
						velX = 0;					
						
					}
					
					if(getBoundsTop().intersects(tempObject.getBounds())) {
						
						y = tempObject.getY() + tempObject.getHeight();
						velY = 0;
											
					}				
				}					
				}	else if(tempObject.getId() == ObjectID.WINBLOCK) {
															
					if(getBounds().intersects(tempObject.getBounds())) {
						
						y = tempObject.getY() - tempObject.getHeight();
						velY = 0;
						falling = false;
						jumping = false;
						setWin();
					
					}	else {
						
						falling = true;
						
					}
					
					if(getBoundsRight().intersects(tempObject.getBounds())) {
						
						x = tempObject.getX() - tempObject.getWidth();
						velX = 0;	
						setWin();

						
					}
					
					if(getBoundsLeft().intersects(tempObject.getBounds())) {
						
						x = tempObject.getX() + tempObject.getWidth();
						velX = 0;					
						setWin();

					}
					
					if(getBoundsTop().intersects(tempObject.getBounds())) {
						
						y = tempObject.getY() + tempObject.getHeight();
						velY = 0;
						setWin();
											
					}				
				}					
			
			}
		}
	}
	
	public void die() {
		
		velY = -3;
		alive = false;
		animationHandler.setAnimation(dieAnimation);
		animationHandler.setDelay(-1);
		
		setRestart(true);

	}
	
	public Rectangle getBounds() {
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

	@Override
	public void setAnimation(BufferedImage[] animation) {
		// TODO Auto-generated method stub
		
	}


}
