package cl.makinolas.atk.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.WorldManifold;
import com.badlogic.gdx.scenes.scene2d.Actor;

import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.bosses.IBoss;
import cl.makinolas.atk.actors.enemies.AttackDetector;
import cl.makinolas.atk.actors.enemies.Enemy;
import cl.makinolas.atk.actors.items.BallActor;
import cl.makinolas.atk.actors.items.ItemActor;
import cl.makinolas.atk.actors.platform.Platform;
import cl.makinolas.atk.minigames.MinigameCharacter;

public abstract class GameActor extends Actor{
  
  public Body myBody;
  
  protected void setBody(Body givenBody){
    this.myBody = givenBody;
    myBody.setUserData(this);
  }

  public boolean isHero() {
    return false;
  }

  public boolean isPlatform() {
    return false;
  }

  public Body getBody() {
    return myBody;
  }

  public boolean isAttack() {
    return false;
  }

  public boolean isMonster() {
    return false;
  }
  
  public boolean isBall() {
    return false;
  }

  public boolean isItem() {
    return false;
  }

  public boolean isDead(){
    return false;
  }
  
  public boolean isDetector() {
    return false;
  }

  public abstract void interact(GameActor actor2, WorldManifold worldManifold);

  public void interactWithHero(Hero hero, WorldManifold worldManifold) {}

  public void interactWithEnemy(Enemy enemy, WorldManifold worldManifold) {}

  public void interactWithAttack(Attacks attacks, WorldManifold worldManifold) {}

  public void interactWithPlatform(Platform platform, WorldManifold worldManifold) {}
  
  public void interactWithAttackDetector(AttackDetector attackDetector) {}

  public void interactWithPortal(Portal portal) {}

  public void interactWithBoss(IBoss boss) {  }

  public void interactWithBall(BallActor ball){}

  public void interactWithItem(ItemActor item) {}
  
  public void interactWithMinigameCharacter(MinigameCharacter minigameCharacter, WorldManifold worldManifold) {}

  public boolean isEnemy() {
    return false;
  }

  public boolean isPuff() {
    return false;
  }

  public void setState(JumpState state) {}

  public void setSpeed(float x, float y) {}

  public boolean isMinigameCharacter() {
    return false;
  }

  public abstract void endInteraction(GameActor actor2, WorldManifold worldManifold);

  public void endHeroInteraction(Hero hero, WorldManifold worldManifold) {}

  public void endPlatformInteraction(Platform platform, WorldManifold worldManifold) {}

}