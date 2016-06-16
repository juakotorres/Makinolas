package cl.makinolas.atk.actors;

import cl.makinolas.atk.GameConstants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Platform extends GameActor {
  
  private BodyDef myBodyDefinition;
  private int xp, yp, wp;
  private TextureRegion region;
  private static final float TILE_FACTOR = 1.8f;

  /**
   * Creates a new platform object.
   * @param myWorld Box2D World.
   * @param x x coordinate in meters.
   * @param y y coordinate in meters.
   * @param widthTiles number of tiles of width.
     */
  public Platform(World myWorld, int x, int y, int widthTiles) {
    
    // Definici�n del cuerpo del jugador.
    myBodyDefinition = new BodyDef();
    myBodyDefinition.position.set(new Vector2(x + widthTiles * TILE_FACTOR /2, y));
    
    // Forma del collider del jugador.
    Body myBody = myWorld.createBody(myBodyDefinition);
    
    PolygonShape shape = new PolygonShape();
    shape.setAsBox(widthTiles * TILE_FACTOR /2, 0.9f);
    ///
    myBody.setGravityScale(1);
    myBody.createFixture(shape, 0.5f);
    myBody.resetMassData();
    shape.dispose();
    
    // Guardar body.
    setBody(myBody);

    xp = x * GameConstants.WORLD_FACTOR;
    yp = y * GameConstants.WORLD_FACTOR;
    wp = widthTiles;

    region = new TextureRegion(new Texture(Gdx.files.internal("Background/platforms2.png"))).split(36,36)[7][7];

  }
  
  @Override
  public boolean isPlatform(){
    return true;
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    for (int i = 0; i < wp; i++) {
      batch.draw(region,xp + i*36, yp - 18);
    }
  }
}
