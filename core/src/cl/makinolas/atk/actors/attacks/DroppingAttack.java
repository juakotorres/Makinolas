package cl.makinolas.atk.actors.attacks;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.GameConstants;
import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.states.SpriteState;
import cl.makinolas.atk.types.IType;

public class DroppingAttack extends ShootAttack{

  public DroppingAttack(SpriteState spriteState, World myWorld, float x, float y, boolean facingRight, Monsters source, IType type) {
    super(spriteState, myWorld, x, y, facingRight, source, true, type);
    isDropping();
  }
  
  public DroppingAttack(SpriteState spriteState, World myWorld, float x, float y, boolean facingRight, Monsters source, boolean rotate, IType type) {
    super(spriteState, myWorld, x, y, facingRight, source, rotate, type);
    isDropping();
  }

  @Override
  public void draw(Batch batch, float alpha){
      Vector2 myPosition = myBody.getPosition();
      TextureRegion actualSprite = getActualSprite();
      batch.draw(actualSprite, myPosition.x * GameConstants.WORLD_FACTOR - actualSprite.getRegionWidth() / 2 , myPosition.y * GameConstants.WORLD_FACTOR - actualSprite.getRegionHeight() / 2,
              actualSprite.getRegionWidth() / 2, actualSprite.getRegionHeight() / 2, actualSprite.getRegionWidth(), actualSprite.getRegionHeight(),
              getScaleX(), -1*getScaleY(), -90);
  }
  
}
