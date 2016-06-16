package cl.makinolas.atk.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

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

  public boolean isEnemy() {
    return false;
  }
  
  public Body getBody(){
    return myBody;
  }

  public boolean isAttack() {
    return false;
  }
  
}
