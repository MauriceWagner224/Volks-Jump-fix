package Loader;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import EntityObjects.Player;
import EntityObjects.Test_Enemy;
import Enum.ObjectID;
import Enum.Type;
import GameObjects.Ausbildungszentrum;
import GameObjects.Ausbildungszentrum;
import GameObjects.BoxLowerLeft;
import GameObjects.BoxLowerRight;
import GameObjects.BoxUpperLeft;
import GameObjects.BoxUpperRight;
import GameObjects.Cars;
import GameObjects.Currywurst;
import GameObjects.ElonOnRocket;
import GameObjects.Ground;
import GameObjects.Ground2;
import GameObjects.LittleBox;
import GameObjects.Markenhochhaus;
import GameObjects.PlatformLeftEnd;
import GameObjects.PlatformLeftPillar;
import GameObjects.PlatformMiddle;
import GameObjects.PlatformRightEnd;
import GameObjects.PlatformRightPillar;
import GameObjects.VoidBlock;
import GameObjects.Water;
import GameObjects.WinBlock;
import GameObjects.missionBlock;
import Handler.ObjectHandler;

public class LevelLoader {
	
	private int nr;
	private int row;
	private int enemyCounter = 0;
	
	Scanner scanner;
	File file;
	ObjectHandler objectHandler;
	
	public LevelLoader(ObjectHandler objectHandler) {
		
		this.objectHandler = objectHandler;
		this.nr = -1;
		this.row = 0;
		
	}
	
	public void load(String path) {
		
		file = new File(path);
		
		try {		
		
			scanner = new Scanner(file);
			
			scanner.nextLine();
			scanner.nextLine();
			
			int width = scanner.nextInt();
//			int row = scanner.nextInt();
			
			scanner.nextLine();
			scanner.nextLine(); 
			
			row = 0;
			nr = -1;
			
			while(scanner.hasNextInt()) {
				
				synchronized (objectHandler.objectlist) {
				
				int object = scanner.nextInt();
				nr = nr + 1;
				
				if(nr == width) {
					
					nr = 0;
					row = row + 1;
				}
				
				if(object == 255) {
					
					
					
				} else if(object == 0) {
					
					objectHandler.addObject(new Ground(nr * 64, row * 64, 64, 64, objectHandler,ObjectID.BLOCK, Type.OBJECT));
					
				} else if(object == 8) {
					
					objectHandler.addObject(new Player(nr * 64, row * 64, 64, 64, ObjectID.PLAYER, objectHandler, Type.PLAYER));
					
				} else if(object == 3) {
					
					objectHandler.addObject(new Test_Enemy(nr * 64, row * 64, 64, 64, ObjectID.TEST_ENEMY, objectHandler, Type.ENTITY));
					
				} else if(object == 5) {
					
					objectHandler.addObject(new Currywurst(nr * 64, row * 64, 48, 16, ObjectID.CURRYWURST, Type.OBJECT, false));
					
				} else if(object == 10) {
					
					objectHandler.addObject(new Ground2(nr * 64, row * 64, 64, 64, ObjectID.BLOCK, Type.OBJECT));
					
				} else if(object == 87) {
					
					objectHandler.addObject(new PlatformLeftPillar(nr * 64, row * 64, 64, 64, ObjectID.DESIGNBLOCK, Type.OBJECT));
					
				} else if(object == 97) {
					
					objectHandler.addObject(new PlatformRightPillar(nr * 64, row * 64, 64, 64, ObjectID.DESIGNBLOCK, Type.OBJECT));
					
				} else if(object == 89) {
					
					objectHandler.addObject(new PlatformLeftEnd(nr * 64, row * 64, 64, 64, ObjectID.AMMOBLOCK, Type.OBJECT));
					
				} else if(object == 94) {
					
					objectHandler.addObject(new PlatformRightEnd(nr * 64, row * 64, 64, 64, ObjectID.AMMOBLOCK, Type.OBJECT));
					
				} else if(object == 92) {
					
					objectHandler.addObject(new PlatformMiddle(nr * 64, row * 64, 64, 64, ObjectID.AMMOBLOCK, Type.OBJECT));
					
				} else if(object == 191) {
					
					objectHandler.addObject(new LittleBox(nr * 64, row * 64, 64, 64, ObjectID.BLOCK, Type.OBJECT, objectHandler));
					
				} else if(object == 171) {
					
					objectHandler.addObject(new BoxUpperLeft(nr * 64, row * 64, 64, 64, ObjectID.BLOCK, Type.OBJECT, objectHandler));
					
				} else if(object == 173) {
					
					objectHandler.addObject(new BoxUpperRight(nr * 64, row * 64, 64, 64, ObjectID.BLOCK, Type.OBJECT, objectHandler));
					
				} else if(object == 176) {
					
					objectHandler.addObject(new BoxLowerLeft(nr * 64, row * 64, 64, 64, ObjectID.BLOCK, Type.OBJECT, objectHandler));
					
				} else if(object == 179) {
					
					objectHandler.addObject(new BoxLowerRight(nr * 64, row * 64, 64, 64, ObjectID.BLOCK, Type.OBJECT, objectHandler));
					
				} else if(object == 128) {
					
					objectHandler.addObject(new VoidBlock(nr * 64, row * 64, 64, 64, ObjectID.BLOCK, Type.OBJECT));
					
				} else if(object == 26) {
					
					objectHandler.addObject(new Water(nr * 64, row * 64, 64, 64, objectHandler, ObjectID.WATER, Type.OBJECT));
					
				} else if(object == 102) {
					
					objectHandler.addObject(new Markenhochhaus(nr * 64, row * 64, 64, 64, ObjectID.DESIGNBLOCK, Type.BACKGROUND));
					
				} else if(object == 153) {
					
					objectHandler.addObject(new ElonOnRocket(nr * 64, row * 64, 64, 64, ObjectID.DESIGNBLOCK, Type.BACKGROUND));
					
				} else if(object == 105) {
					
					objectHandler.addObject(new Ausbildungszentrum(nr * 64, row * 64, 64, 64, ObjectID.DESIGNBLOCK, Type.OBJECT));
					
				} else if(object == 66) {
					
					objectHandler.addObject(new missionBlock(nr * 64, row * 64, 64, 64, ObjectID.MISSIONBLOCK, Type.OBJECT));
					
				} else if(object == 64) {
					
					objectHandler.addObject(new WinBlock(nr * 64, row * 64, 64, 64, ObjectID.WINBLOCK, Type.OBJECT));
					
				} else if(object == 54) {
					
					objectHandler.addObject(new Cars(nr * 64, row * 64, 64, 64, ObjectID.DESIGNBLOCK, Type.OBJECT, 54));
					
				} else if(object == 51) {
					
					objectHandler.addObject(new Cars(nr * 64, row * 64, 64, 64, ObjectID.DESIGNBLOCK, Type.OBJECT, 51));
					
				} else if(object == 56) {
					
					objectHandler.addObject(new Cars(nr * 64, row * 64, 64, 64, ObjectID.DESIGNBLOCK, Type.OBJECT, 56));
					
				} else if(object == 61) {
					
					objectHandler.addObject(new Cars(nr * 64, row * 64, 64, 64, ObjectID.DESIGNBLOCK, Type.OBJECT, 61));
					
				} else if(object == 59) {
					
					objectHandler.addObject(new Cars(nr * 64, row * 64, 64, 64, ObjectID.DESIGNBLOCK, Type.OBJECT, 59));
					
				}  else if(object == 69) {
					
					objectHandler.addObject(new Cars(nr * 64, row * 64, 64, 64, ObjectID.DESIGNBLOCK, Type.OBJECT, 69));
					
				}								
						
				for(int i = 0; i < objectHandler.objectlist.size(); i++) {
					
					if(objectHandler.objectlist.get(i) instanceof ElonOnRocket) {
						
						((ElonOnRocket) objectHandler.objectlist.get(i)).setMapSize(nr);
					}
				}
			}
			}
			} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		}
		
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
	public int getNr() {
		return nr;
	}
	
	public void setEnemyCounter(int enemyCounter) {
		this.enemyCounter = enemyCounter;		
	}
	
	public int getEnemyCounter() {
		return enemyCounter;
	}
}
