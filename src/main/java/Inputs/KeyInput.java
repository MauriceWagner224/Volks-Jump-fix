package Inputs;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

import EntityObjects.Player;
import Enum.ObjectID;
import Enum.Type;
import GameObjects.Ammo;
import Handler.ObjectHandler;
import Main.Game;
import Super.GameObject;

public class KeyInput implements KeyListener{

	ObjectHandler objectHandler;
	int bullets;
	Game game;
		
	public KeyInput (ObjectHandler objectHandler) {
		
		this.objectHandler = objectHandler;
		
	}
		
	@Override
	public void keyTyped(KeyEvent e) {}
	

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		synchronized (objectHandler.objectlist) {
		
		int key = e.getKeyCode();
		
		for(int i = 0; i < objectHandler.objectlist.size(); i++) {
			
			GameObject tempObject = objectHandler.objectlist.get(i);
			
			if(tempObject.getId() == ObjectID.PLAYER) {
			
				if(key == KeyEvent.VK_A) {
					
					tempObject.setVelX(-5); //-5
					
				}
				
				if(key == KeyEvent.VK_D) {
					
					tempObject.setVelX(5); //5
					
					
				}
				
				if(key == KeyEvent.VK_SPACE && !tempObject.isJumping() && tempObject.isAlive() == true) {
	
					tempObject.setVelY(-7); //-7
					tempObject.setJumping(true);
	
				}
				
				if(key == KeyEvent.VK_E && !tempObject.isJumping() && tempObject.isAlive() == true && tempObject.getVelX() == 0) {
					
					if(tempObject.getAmmoCounter() > 0) {
						this.objectHandler.addObject(new Ammo(tempObject.getX() , tempObject.getY(), 48, 16, ObjectID.AMMO, Type.ENTITY, objectHandler, true, tempObject.isFacingRight()));
						tempObject.setShooting(true);
						bullets = tempObject.getAmmoCounter();
						tempObject.setAmmocounter(bullets -= 1);
					}										
				} 

			}
		}
		}
	}	
	

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		synchronized (objectHandler.objectlist) {
		
		int key = e.getKeyCode();
		
		for(int i = 0; i < objectHandler.objectlist.size(); i++) {
			
			GameObject tempObject = objectHandler.objectlist.get(i);
			
			if(tempObject.getId() == ObjectID.PLAYER) {
				
				if(key == KeyEvent.VK_A) {
					
					tempObject.setVelX(0);
					
				}
				
				if(key == KeyEvent.VK_D) {
					
					tempObject.setVelX(0);
					
				}
				
				if(key == KeyEvent.VK_E) {
					
//					this.objectHandler.addObject(new Ammo(tempObject.getX() , tempObject.getY(), 48, 16, ObjectID.AMMO, Type.ENTITY, objectHandler, true));
					tempObject.setShooting(false);;
					
				}
			}
		}	
	}
	}

}
