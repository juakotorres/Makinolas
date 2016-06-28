package cl.makinolas.atk.actors;

import cl.makinolas.atk.actors.items.BallActor;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

import cl.makinolas.atk.actors.attacks.Attacks;

public abstract class GameActor extends Actor{
  
  protected Body myBody;
  
  protected void setBody(Body givenBody){
    this.myBody = givenBody;
    myBody.setUserData(this);
  }
  
  public boolean isHero(){
    return false;
  }
  
  public boolean isPlatform(){
    return false;
  }
  
  public Body getBody(){
    return myBody;
  }

  public boolean isAttack() {
    return false;
  }
  
  public boolean isMonster() {
    return false;
  }
  
  public boolean isDead(){
    return false;
  }

  public abstract void interact(GameActor actor2);

  public void interactWithHero(Hero hero) {}

  public void interactWithEnemy(Enemy enemy) {}

  public void interactWithAttack(Attacks attacks) {}

  public void interactWithPlatform(Platform platform) {}

  public void interactWithPortal(Portal portal) {}

  public void interactWithBall(BallActor ball){}
  
}