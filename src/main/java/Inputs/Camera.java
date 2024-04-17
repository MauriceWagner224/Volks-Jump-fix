package Inputs;

import Main.Game;
import Super.GameObject;

public class Camera {

	private double x;
	private double y;
	
	int mapWidth;
	
	public Camera(double x, double y) {

		this.x = x;
		this.y = y;
		
	}//-223 oder -450
	
	public void tick(GameObject obj) {
				
		if(obj.getX() < mapWidth - Game.displayWidth/Game.scale/3 * 2 && obj.getX() > Game.displayWidth/Game.scale/3 ) {
			
			this.x = -obj.getX() + Game.displayWidth/Game.scale/3;
//			this.y = -obj.getY() + 500;
		}
		
	}

	public double getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setMapSize(int nr) {
		
		this.mapWidth = nr * 64;
	}	
	
	public int getMapSize() {
		
		return mapWidth;
	}	
	




}
