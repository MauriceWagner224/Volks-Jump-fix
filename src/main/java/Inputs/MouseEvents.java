package Inputs;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Main.Game;

public class MouseEvents extends MouseAdapter {
	
	Game game;
	
	public MouseEvents(Game game) {
		
		super();
		this.game = game;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		this.game.mousePressed(e);
		}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		this.game.mouseMoved(e);
		}

}
