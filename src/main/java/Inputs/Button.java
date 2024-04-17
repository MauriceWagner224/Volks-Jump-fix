package Inputs;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;

public class Button {
	
	int x;
	int y;
	int width;
	int height;
	
	Image sprite;
	Image hoveredSprite;
	
	boolean hovered = false;
	
	public Button(int x, int y, int width, int height, Image sprite) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
		this.hoveredSprite = sprite;
	}
	
	public Button(int x, int y, int width, int height, Image sprite, Image hoveredSprite) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
		this.hoveredSprite = hoveredSprite;
	}

	public void render(Graphics g) {
		
		if(hovered) {
		
			g.drawImage(this.hoveredSprite, this.x, this.y, width, height, null);
			
			} else {
				
				g.drawImage(this.sprite, this.x, this.y, width, height, null);
			}
	
	}

	public boolean inBounds(Point point) {
		
		return x < point.x && (x + width) > point.x && y < point.y && (y + height) > point.y; 
	}
	
	public void update(MouseEvent e) {
		
		if(this.inBounds(e.getPoint())) {
			
			hovered = true;
		
		} else {
			
			hovered = false;
		}
	}
}
