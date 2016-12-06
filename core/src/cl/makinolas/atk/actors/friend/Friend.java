package cl.makinolas.atk.actors.friend;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.GameActor;
import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.enemies.Enemy;
import cl.makinolas.atk.actors.heroState.AbstractFriendState;
import cl.makinolas.atk.actors.heroState.StandartState;
import cl.makinolas.atk.stateEfects.IStateEfects;
import cl.makinolas.atk.types.IType;

public interface Friend {  
  public void setVariables(int health, int magic);
  public void setHealth(int health);
  public int getHealth(); 
  public int getMaxHealth();
  public void setMagic(int magic);
  public int getMaxMagic();
  public int getMagic();
  public void setDead(boolean dead);
  public boolean getDead();
  public void isDead();
  public void setExp(double d);
  public Attacks getFriendAttack(World myWorld, float x , float y, boolean facingRight, Monsters source);
  public int[][] getIdleAnimation();
  public int[][] getHurtAnimation();
  public int[][] getWalkAnimation();
  public int[][] getMeleeAnimation();
  public int[][] getSpecialAnimation();
  public int[][] getSingAnimation();
  public int getMeleeFrame();
  public TextureRegion getTexture();
  public int getWidth();
  public int getHeight();
  public int getLevel();
  public void gainExperience(int wildLevel, Enemies type);
  public double thisLevelExp();
  public double getNextExperience();
  public TextureRegion getFriendFaceSprite();
  public Enemy returnEnemy(World myWorld, int positionX, int positionY, boolean facingRight);
  public Enemies getFriend();
  public int getAttack();
  public int getDefense();
  public int getSpecialAttack();
  public int getSpecialDefense();
  public ArrayList<IType> getType();
  public void addType(IType type);
  public void resetType();
  public int getSpeed();


  int getCatchRate();

  public Enemy returnLongRangeEnemy(World myWorld, int heroPosition);
  public Enemy returnPhysicalEnemy(World myWorld, int heroPosition);
  public String getName();
  public Enemy returnStayAndShootEnemy(World myWorld, int positionX, int positionY, boolean facingRight);
  public Enemy returnFlyWaveAndDropEnemy(World myWorld, int positionX, int positionY, boolean facingRight);
  public Enemy returnJumperEnemy(World myWorld, int positionX, int positionY, boolean facingRight);
  public Enemy returnFollowerEnemy(World myWorld, int positionX, int positionY, boolean facingRight);

  void setIvs(int individualValue);
  int getIvs();
  void setEvs(int effortValue1, int effortValue2);
  int getEv1();
  int getEv2();
  void addHpEv(int n);
  void addAttackEv(int n);
  void addDefenseEv(int n);
  void addSpAttackEv(int n);
  void addSpDefenseEv(int n);
  void addSpeedEv(int n);
  void setCriticModificator(int val);
  void setAttackiv(int val);
  int getAttackiv();
  public int getCriticModificator();
  public int getAttackMagicRequirement();
public boolean secondaryAttack();
public GameActor getFriendSecondaryAttack(World myWorld, float f, float y, boolean isFacingRight,
		Monsters source);
public void setState(AbstractFriendState standartState);
public AbstractFriendState getState();
public ArrayList<IStateEfects> getStateEfectList();
  
}
