package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Enemy;

public interface Friend {  
  public void setVariables(int health, boolean dead);
  public void setLevel(float level);
  public int getHealth(); 
  public void setMagic(int magic);
  public int getMagic();
  public boolean getDead();
  public int[][] getHurtAnimation();
  public int[][] getWalkAnimation();
  public TextureRegion getTexture();
  public int getWidth();
  public int getHeight();
  public float getLevel();
  public TextureRegion getFriendFaceSprite();
  public Enemy returnEnemy(World myWorld, int heroPosition);
}
