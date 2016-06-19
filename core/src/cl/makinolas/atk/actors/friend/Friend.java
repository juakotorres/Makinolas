package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Enemy;

public interface Friend {  
  public void setVariables(int health, boolean dead);  
  public int getHealth(); 
  public boolean getDead();
  public int getHurtFrames();
  public int[][] getHurtAnimation();
  public int getWalkFrames();
  public int[][] getWalkAnimation();
  public TextureRegion getTexture();
  public int getWidth();
  public int getHeight();
  public Enemy returnEnemy(World myWorld, int heroPosition);
}
