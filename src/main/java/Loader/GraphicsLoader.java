package Loader;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.*;
import java.net.URL;
import java.nio.file.Path;

import javax.imageio.ImageReader;
import javax.imageio.ImageReader.*;
import javax.imageio.ImageIO;


import EntityObjects.Player;
import EntityObjects.Test_Enemy;
import GameObjects.Ammo;
import GameObjects.BoxLowerLeft;
import GameObjects.BoxLowerRight;
import GameObjects.BoxUpperLeft;
import GameObjects.BoxUpperRight;
import GameObjects.Currywurst;
import GameObjects.Ground;
import GameObjects.Ground2;
import GameObjects.LittleBox;
import GameObjects.PlatformLeftEnd;
import GameObjects.PlatformLeftPillar;
import GameObjects.PlatformMiddle;
import GameObjects.PlatformRightEnd;
import GameObjects.PlatformRightPillar;
import GameObjects.Water;
import Super.GameObject;


public class GraphicsLoader {

	public void load() {

		this.loadGraphics();
		this.loadSprite();

	}

	public BufferedImage read(File input) throws FileNotFoundException {

        try {
			BufferedImage img;
            img = ImageIO.read(input);
			return img;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

	public void loadGraphics() {

		try {


		Ground.img = read(new File("res/Graphics/asphalt_ground.png")); //0
		Ground2.img = read(new File("res/Graphics/ground2.png")); //10

  		PlatformLeftEnd.img = read(new File("res/Graphics/platform_left_end.png")); 	//89
  		PlatformRightEnd.img = read(new File("res/Graphics/platform_right_end.png"));	//94
  		PlatformMiddle.img = read(new File("res/Graphics/platform_middle.png"));   //92

  		PlatformLeftPillar.img = read(new File("res/Graphics/platform_pillar_left.png"));		//87
  		PlatformRightPillar.img = read(new File("res/Graphics/platform_pillar_right.png"));	//97

  		LittleBox.img = read(new File("res/Graphics/little_box.png"));	//191

  		BoxUpperLeft.img = read(new File("res/Graphics/box_upper_left.png"));	//171
  		BoxUpperRight.img = read(new File("res/Graphics/box_upper_right.png"));	//173
  		BoxLowerLeft.img = read(new File("res/Graphics/box_lower_left.png"));	//176
  		BoxLowerRight.img = read(new File("res/Graphics/box_lower_right.png"));	//179

  																				//water == 26

		} catch(Exception e) {

			e.printStackTrace();

		}
	}

	public void loadSprite() {

		try {

			//Animations for the Player	//8

			Player.idleAnimation = new BufferedImage[1];
			Player.jumpingAnimation = new BufferedImage[1];
			Player.walkingAnimation = new BufferedImage[2];
			Player.dieAnimation = new BufferedImage[1];
			Player.shootAnimation = new BufferedImage[3];

			Player.idleAnimation[0] = read(new File("res/Sprites/mc_stand_right_wide_final.png"));

			Player.jumpingAnimation[0] = read(new File("res/Sprites/mc_walk_right2_wide_final.png"));

			Player.walkingAnimation[0] = read(new File("res/Sprites/mc_walk_right2_wide_final.png"));
			Player.walkingAnimation[1] = read(new File("res/Sprites/mc_walk_right1_wide_final.png"));

			Player.dieAnimation[0] = read(new File("res/Sprites/ghost.png"));

			Player.shootAnimation[0] = read(new File("res/Sprites/mc_with_weapon.png"));
			Player.shootAnimation[1] = read(new File("res/Sprites/mc_with_weapon_2.png"));
			Player.shootAnimation[2] = read(new File("res/Sprites/mc_with_weapon_3.png"));

			//Animations for the Test_Enemy //3

			Test_Enemy.idleAnimation = new BufferedImage[1];
			Test_Enemy.walkingAnimation = new BufferedImage[2];
			Test_Enemy.transformedAnimation = new BufferedImage[2];

			Test_Enemy.idleAnimation[0] = read(new File("res/Sprites/en_stand_right.png"));

			Test_Enemy.walkingAnimation[0] = read(new File("res/Sprites/Enemy_walk1.png"));
			Test_Enemy.walkingAnimation[1] = read(new File("res/Sprites/Enemy_walk2.png"));

			Test_Enemy.transformedAnimation[0] = read(new File("res/Sprites/fe_happy_2.png"));
			Test_Enemy.transformedAnimation[1] = read(new File("res/Sprites/transformed2.png"));

			//Animation for the Currywurst	//5

			Currywurst.currywurstAnimation = new BufferedImage[1];

			Currywurst.currywurstAnimation[0] = read(new File("res/Sprites/currywurst.png"));

			Ammo.test = new BufferedImage[1];

			Ammo.test[0] = read(new File("res/Sprites/currywurst.png"));
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		
	}
	
	
}
