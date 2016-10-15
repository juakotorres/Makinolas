package cl.makinolas.atk.actors.platform;

import cl.makinolas.atk.actors.Hero;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;

public class BreakablePlatform extends Platform {

  private boolean heroIsInPlatform;
  private boolean isBroken;
  private TextureRegion[][] mySprite;
  private int actualSprite;
  private float timeSprite = 1/3f;
  private float recoverTime = 3f;
  private float recovering;
  private float accumulator;
  private World myWorld;
  private int myX;
  private int myY;

  public BreakablePlatform(World myWorld, int x, int y) {
    super(myWorld, "CU", x, y, 1, 1);

    heroIsInPlatform = false;
    isBroken = false;
    mySprite = new TextureRegion(new Texture(Gdx.files.internal("Background/IceFallingBlock.png"))).split(44,43);
    actualSprite = 0;
    accumulator = 0;
    recovering = 0;
    this.myWorld = myWorld;
    myX = x;
    myY = y;
  }

  @Override
  public void act(float delta) {
    if(heroIsInPlatform && !isBroken){
      accumulator += delta;
      if(accumulator > timeSprite){
        actualSprite += 1;
        accumulator = 0;
        if(actualSprite > 2){
          isBroken = true;
          myWorld.destroyBody(myBody);
          actualSprite = 0;
        }
      }
    }

    if(isBroken){
      recovering += delta;
      if(recovering > recoverTime){
        setPlatformBody(myWorld, myX, myY, 1, 1);
        recovering = 0;
        isBroken = false;
      }
    }
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    if(!isBroken) {
      for (int i = 0; i < wp; i++) {
        for (int j = 0; j < hp; j++) {
          batch.draw(mySprite[0][actualSprite], xp + i * 36, yp + j * 36, 37, 37);
        }
      }
    }
  }

  @Override
  public void interactWithHero(Hero hero, WorldManifold worldManifold){
    heroIsInPlatform = true;
    hero.landedPlatform(worldManifold, this);
  }

  @Override
  public void endHeroInteraction(Hero hero, WorldManifold worldManifold) {
    heroIsInPlatform = false;
  }

}
