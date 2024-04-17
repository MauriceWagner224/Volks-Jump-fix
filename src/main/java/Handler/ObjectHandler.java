package Handler;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import Enum.ObjectID;
import Enum.Type;
import Super.GameObject;

public class ObjectHandler {
	
	public List<GameObject>objectlist = Collections.synchronizedList(new ArrayList<GameObject>());
	private GameObject tempObject;
	public int enemyCounter;
	public boolean lvlPassed = false;
			
	public void renderObjects(Graphics g) {
		
		synchronized (objectlist) {
			
			for(int i = 0; i < objectlist.size(); i++) {
				
				tempObject = objectlist.get(i);
				
				if(tempObject.getType() == Type.OBJECT) {
				
					tempObject.render(g);
				}
			}	
		}
	}
	
	public void renderEntitys(Graphics g) {

		synchronized (objectlist) {

			for (int i = 0; i < objectlist.size(); i++) {

				tempObject = objectlist.get(i);

				if (tempObject.getType() == Type.ENTITY) {

					tempObject.render(g);
				}
			}
		}
	}
	
	public void renderPlayer(Graphics g) {
		
		synchronized (objectlist) {
		
		for(int i = 0; i < objectlist.size(); i++) {
			
			tempObject = objectlist.get(i);
			
			if(tempObject.getType() == Type.PLAYER) {
				
				tempObject.render(g);
			}
		}		
		}
	}
	
	public void renderBackground(Graphics g) {
		
		synchronized (objectlist) {
		
		for(int i = 0; i < objectlist.size(); i++) {
			
			tempObject = objectlist.get(i);
			
			if(tempObject.getType() == Type.BACKGROUND) {
				
				tempObject.render(g);
			}
		}	
		}
	}

	public void tick() {
		
		synchronized (objectlist) {
			
			enemyCounter = 0;			
			Iterator<GameObject> objectIterator = objectlist.iterator();
			
			while(objectIterator.hasNext()) {
				
				tempObject = objectIterator.next();
				tempObject.tick(objectlist);
				
				if(tempObject.isDestroyed()) {
					
					objectIterator.remove();
				}
				
				if(tempObject.getId() == ObjectID.TEST_ENEMY && tempObject.isAlive()) {
					
					enemyCounter += 1;
				}

			}
		}
	}
	
	public void addObject(GameObject obj) {
		
		synchronized (objectlist) {
			
		this.objectlist.add(obj);
		}
	}
	
	public void removeObject(GameObject obj) {
		
		synchronized (objectlist) {
		
		this.objectlist.remove(obj);
		
		}
	}
}
