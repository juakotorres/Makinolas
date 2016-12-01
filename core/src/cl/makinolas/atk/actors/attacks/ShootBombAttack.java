package cl.makinolas.atk.actors.attacks;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.states.SpriteState;
import cl.makinolas.atk.actors.platform.Platform;

public class ShootBombAttack extends BombAttack{

  public ShootBombAttack(SpriteState spriteState, World myWorld, float x, float y, boolean facingRight,
      Monsters source) {
    super(spriteState, myWorld, x, y, facingRight, source);
    this.xVelocity = (facingRight)? 10: -10;
  }
  
  @Override
  public void act(float delta){
    myBody.setLinearVelocity(xVelocity, 0);
    checkFinish(delta);
  }
  
  @Override
  public void setDead(){
    dead = true;
  }
  
  @Override
  public void interactWithPlatform(Platform platform, WorldManifold worldManifold){
    this.setDead();
  }
  
}
