package Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
/*import java.lang.StackWalker;*/
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import EntityObjects.Player;
import Enum.ObjectID;
import Enum.Type;
import GameObjects.Ground;
import Handler.ObjectHandler;
import Inputs.Button;
import Inputs.Camera;
import Inputs.KeyInput;
import Inputs.MouseEvents;
import Loader.GraphicsLoader;
import Loader.LevelLoader;
import Super.GameObject;

public class Game extends Canvas implements Runnable{

	private boolean running = false;
	
	static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

	public static int displayWidth = gd.getDisplayMode().getWidth();
	public static int displayHeight = gd.getDisplayMode().getHeight();
	public static double scale = gd.getDefaultConfiguration().getDefaultTransform().getScaleY();
		
	Image deathScreen = Toolkit.getDefaultToolkit().getImage("res/Background/GameOver.png");
	Image winScreen = Toolkit.getDefaultToolkit().getImage("res/Background/win_screen_V2.gif");
	
	Image restartButtonSprite = Toolkit.getDefaultToolkit().getImage("res/Background/restart_button.png");
	Image restartButtonSpriteHovered = Toolkit.getDefaultToolkit().getImage("res/Background/restart_button_hover.png");
	Image exitButtonSprite = Toolkit.getDefaultToolkit().getImage("res/Background/exit_button.png");
	Image exitButtonSpriteHovered = Toolkit.getDefaultToolkit().getImage("res/Background/exit_button_hover.png");
		
	Image lvlText = Toolkit.getDefaultToolkit().getImage("res/Background/lvltext_white.png");
	
	public static int buttonWidth = (int) (displayWidth/scale);
	public static int buttonHeight = (int) (displayHeight/scale);
	
	Button exitButton = new Button(buttonWidth/2 - 240, buttonHeight/3 * 2, 480, 120, exitButtonSprite, exitButtonSpriteHovered);
	Button restartButton = new Button(buttonWidth/2 - 240, buttonHeight/3 * 2 - 140, 480, 120, restartButtonSprite, restartButtonSpriteHovered);
		
	public Image backgroundImg = null;
	
	Thread thread;
	ObjectHandler objectHandler;
	LevelLoader levelLoader;
	GraphicsLoader graphicsLoader;
	Camera camera;
		
	public boolean timerCheck = false;
	boolean restart;
	boolean passed = false;
	boolean win;
	
	public static int width;
	int amount = 0;


	Optional<MouseEvent> mouseClick = java.util.Optional.empty();
	
	public synchronized void start() {
		
		thread = new Thread(this);
		running = true;
		thread.start();	
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		init();
		this.requestFocus();
		
		while(true) {

			long lastTime = System.nanoTime();
			double amountOfTicks = 60.0;
			double ns = 1000000000 / amountOfTicks;
			double delta = 0;
			long timer = System.currentTimeMillis();
			int updates = 0;
			int frames = 0;
			
			float currentTime = System.currentTimeMillis();
			float actionTime = 0L;
			
			while (running) {
				long now = System.nanoTime();
				delta += (now - lastTime) / ns;
				lastTime = now;
				while (delta >= 1) {
					tick();
					updates++;
					delta--;
				}
				render();
				frames++;

				if (System.currentTimeMillis() - timer > 1000) {
					timer += 1000;
					System.out.println("FPS: " + frames + " TICKS: " + updates);
					frames = 0;
					updates = 0;
				}
				
				if(currentTime - actionTime == 10000) {
					
					actionTime = currentTime;
					setTimerCheck(true); 
				} 
			}
			
			running = true;
			reset();			
		}
	}

	private void reset() {
		// TODO Auto-generated method stub
		
		restart = false;
		win = false;

		graphicsLoader = new GraphicsLoader();
		
		graphicsLoader.load();
		
		objectHandler = new ObjectHandler();
		levelLoader = new LevelLoader(objectHandler);
		
		levelLoader.load("res/Level/level_presentation_V2.pgm");
		
		this.addKeyListener(new KeyInput(objectHandler));
		
		System.out.println("levelloader: " + levelLoader.getRow());
		System.out.println("width: " + this.displayWidth);
		System.out.println("height: " + this.displayHeight);
		System.out.println("scale: " + this.scale);
		
		camera = new Camera(0, (this.displayHeight/this.scale) - (levelLoader.getRow() + 1)* 64);
		camera.setMapSize(levelLoader.getNr());
		System.out.println(levelLoader.getRow());
		
		try {
			
			backgroundImg = ImageIO.read(new File("res/Background/background.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void setTimerCheck(boolean b) {
		// TODO Auto-generated method stub
		timerCheck = b;
	}
	
	public boolean getTimerCheck() {
		
		return timerCheck;
	}
	
	public void render() {
		// TODO Auto-generated method stub
		objectHandler.lvlPassed = false;		
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
	
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;	
		
		g.drawImage(backgroundImg, (int) camera.getX(), 0, camera.getMapSize(), this.getHeight(), null);
			
		g2d.translate(camera.getX(), camera.getY());
		
		objectHandler.renderObjects(g);
		objectHandler.renderEntitys(g);
		objectHandler.renderPlayer(g);
		objectHandler.renderBackground(g);
		
		g2d.translate(-camera.getX(), -camera.getY());
				
		Font myFont = new Font ("Calibri", 1, 50);
		g.setColor(Color.WHITE);
		g.setFont(myFont);
		
		if(restart == false && win == false) {
		
		g.drawString("Ammunition: " + amount, 25, 75); 
		g.drawString("Enemies: " + objectHandler.enemyCounter, 25, 125);
		}
		
		if (objectHandler.enemyCounter == 0 && !restart && win == false) {
			
			objectHandler.lvlPassed = true;
			g.drawImage( lvlText, buttonWidth / 2 - 600, buttonHeight / 3, 1000, 50, null);
		}
			
		if(restart == true) {
			
			g2d.translate(0,0);
			renderDeadScreen(g);
		}
		
		if(win == true) {
			
			g2d.translate(0,0);
			renderWinScreen(g);			
		}
		
		g.dispose();
		bs.show();
	}
	
	public void renderDeadScreen(Graphics g) {

		g.drawImage(deathScreen, 0, -200, this.getWidth(), this.getHeight(), null);
		
		exitButton.render(g);
		restartButton.render(g);
		
		if(mouseClick.isPresent()) {
			
			MouseEvent e = mouseClick.get();
			
			if(e.getButton() == 1) {
				
				if(exitButton.inBounds(e.getLocationOnScreen())) {
				
					System.exit(-1);	
					
				} else if(restartButton.inBounds(e.getLocationOnScreen())) {
					
					running = false;	
				}
			}
		}
	}
	
	public void renderWinScreen(Graphics g) {

		g.drawImage(winScreen, 0, 0, this.getWidth(), this.getHeight(), null);
		
		exitButton.render(g);
		restartButton.render(g);
		
		if(mouseClick.isPresent()) {
			
			MouseEvent e = mouseClick.get();
			
			if(e.getButton() == 1) {
				
				if(exitButton.inBounds(e.getLocationOnScreen())) {
				
					System.exit(-1);	
					
				} else if(restartButton.inBounds(e.getLocationOnScreen())) {
					
					running = false;	
				}
			}
		}
	}
	
	public void mousePressed(MouseEvent e) {
		
		this.mouseClick = Optional.of(e);
	}
	
	public void mouseMoved(MouseEvent e) {
		
		this.exitButton.update(e);
		this.restartButton.update(e);
	}

	public void tick() {
		// TODO Auto-generated method stub
		
		synchronized (objectHandler.objectlist) {
		
		objectHandler.tick();
		
		for(int i = 0; i < objectHandler.objectlist.size(); i++) {
			
			GameObject tempObject = objectHandler.objectlist.get(i);
			
			if(tempObject.getId() == ObjectID.PLAYER) {
				
				camera.tick(tempObject);
				this.restart = tempObject.getRestart();
				this.amount = tempObject.getAmmoCounter();
				this.win = tempObject.getWin();
			} 
		}
		
		this.mouseClick = Optional.empty();
		}
	}

	public void init() {
		// TODO Auto-generated method stub
		this.width = this.getWidth();
			
		MouseEvents listener = new MouseEvents(this);
		
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
		
		reset();
		}
	
}
