package cl.makinolas.atk.actors;

import cl.makinolas.atk.GameConstants;
import cl.makinolas.atk.actors.attacks.Attacks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;

public class Platform extends GameActor {
  
  private BodyDef myBodyDefinition;
  private int xp, yp, wp, hp;
  private TextureRegion region;
  private static final float TILE_FACTOR = 1.8f;

  /**
   * Creates a new platform object.
   * @param myWorld Box2D World.
   * @param x x coordinate in meters.
   * @param y y coordinate in meters.
   * @param widthTiles number of tiles of width.
   * @param heightTiles number of tiles of height
     */
  public Platform(World myWorld, int x, int y, int widthTiles, int heightTiles) {
    
    // Definici�n del cuerpo del jugador.
    myBodyDefinition = new BodyDef();
    myBodyDefinition.position.set(new Vector2(x*TILE_FACTOR + widthTiles * TILE_FACTOR /2, y*TILE_FACTOR + heightTiles * TILE_FACTOR / 2));
    
    // Forma del collider del jugador.
    Body myBody = myWorld.createBody(myBodyDefinition);
    
    PolygonShape shape = new PolygonShape();
    shape.setAsBox(widthTiles * TILE_FACTOR /2, heightTiles * TILE_FACTOR / 2);
    ///
    myBody.setGravityScale(1);
    myBody.createFixture(shape, 0.5f);
    myBody.resetMassData();
    shape.dispose();
    
    // Guardar body.
    setBody(myBody);

    xp = (int) (x * TILE_FACTOR * GameConstants.WORLD_FACTOR);
    yp = (int) (y * TILE_FACTOR* GameConstants.WORLD_FACTOR);
    wp = widthTiles;
    hp = heightTiles;

    region = new TextureRegion(new Texture(Gdx.files.internal("Background/platforms2.png"))).split(36,36)[7][7];

  }

  public Platform(World myWorld, int x, int y, int widthTiles){
      this(myWorld,x,y,widthTiles,1);
  }
  
  @Override
  public boolean isPlatform(){
    return true;
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    for (int i = 0; i < wp; i++) {
      for (int j = 0; j < hp; j++) {
        batch.draw(region,xp + i*36, yp + j*36);
      }
    }
  }

  @Override
  public void interact(GameActor actor2, WorldManifold worldManifold) {
    actor2.interactWithPlatform(this, worldManifold);
  }
  
  @Override
  public void interactWithHero(Hero hero, WorldManifold worldManifold){
    hero.landedPlatform(worldManifold, this);
  }
  
  @Override
  public void interactWithAttack(Attacks attack, WorldManifold worldManifold){
    attack.interactWithPlatform(this, worldManifold);
  }
}
