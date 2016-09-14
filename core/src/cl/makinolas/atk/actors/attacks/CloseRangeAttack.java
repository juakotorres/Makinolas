package cl.makinolas.atk.actors.attacks;

import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;

public abstract class CloseRangeAttack extends Attacks {

  public CloseRangeAttack(World myWorld, float x, float y, boolean facingRight, Monsters source) {
    super(myWorld, x, y, facingRight, source);
  }
    
}
