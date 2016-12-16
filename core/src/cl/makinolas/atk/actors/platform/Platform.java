package cl.makinolas.atk.actors.platform;

import cl.makinolas.atk.actors.ui.IHero;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;

import cl.makinolas.atk.GameConstants;
import cl.makinolas.atk.actors.GameActor;
import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.enemies.Enemy;
import cl.makinolas.atk.minigames.MinigameCharacter;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;

public class Platform extends GameActor {
  
  protected BodyDef myBodyDefinition;
  int xp, yp, wp, hp;
  TextureRegion region;
  static final float TILE_FACTOR = 1.8f;
  private World myWorld;
  private int widthTiles;
  private int heightTiles;

  protected float cameraPositionWhenCreated;
  /**
   * Creates a new platform object.
   * @param myWorld Box2D World.
   * @param x x coordinate in meters.
   * @param y y coordinate in meters.
   * @param widthTiles number of tiles of width.
   * @param heightTiles number of tiles of height
     */
  public Platform(World myWorld, String textureCode, int x, int y, int widthTiles, int heightTiles) {
    this.myWorld = myWorld;
    this.widthTiles = widthTiles;
    this.heightTiles = heightTiles;
    
    setPlatformBody(myWorld, x, y, widthTiles, heightTiles);

    xp = (int) (x * TILE_FACTOR * GameConstants.WORLD_FACTOR);
    yp = (int) (y * TILE_FACTOR* GameConstants.WORLD_FACTOR);
    wp = widthTiles;
    hp = heightTiles;

    region = PlatformResource.getInstance().getRegionWithCode(textureCode);

  }

  protected void setPlatformBody(World myWorld, int x, int y, int widthTiles, int heightTiles){
    // Definici�n del cuerpo del jugador.
    myBodyDefinition = new BodyDef();
    myBodyDefinition.position.set(new Vector2(x*TILE_FACTOR + widthTiles * TILE_FACTOR /2, y*TILE_FACTOR + heightTiles * TILE_FACTOR / 2));

    // Forma del collider del jugador.
    Body myBody = myWorld.createBody(myBodyDefinition);

    PolygonShape shape = new PolygonShape();
    shape.setAsBox((widthTiles)* TILE_FACTOR /2, heightTiles * TILE_FACTOR / 2);
    ///
    myBody.setGravityScale(1);
    myBody.createFixture(shape, 0.5f).setFriction(0);
    myBody.resetMassData();
    shape.dispose();

    // Guardar body.
    setBody(myBody);

  }
  public Platform(World myWorld, int x, int y, int widthTiles){
      this(myWorld,"CU",x,y,widthTiles,1);
  }


  public float getCameraPositionWhenCreated () {
    return cameraPositionWhenCreated;
  }
  
  @Override
  public boolean isPlatform(){
    return true;
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    for (int i = 0; i < wp; i++) {
      for (int j = 0; j < hp; j++) {
        batch.draw(region,xp + i*36, yp + j*36,37,37);
      }
    }
  }

  @Override
  public void setPosition (float x, float y) {
    if (this.xp != x || this.yp != y) {
      this.xp = (int) ((int)x * TILE_FACTOR * GameConstants.WORLD_FACTOR);
      this.yp = (int)((int)y * TILE_FACTOR* GameConstants.WORLD_FACTOR);
      setPlatformBody(myWorld,(int)x,(int)y,widthTiles,heightTiles);
    }

  }

  @Override
  public void interact(GameActor actor2, WorldManifold worldManifold) {
    actor2.interactWithPlatform(this, worldManifold);
  }
  
  @Override
  public void interactWithHero(IHero hero, WorldManifold worldManifold){
    hero.landedPlatform(worldManifold, this);
  }
  
  @Override
  public void interactWithAttack(Attacks attack, WorldManifold worldManifold){
    attack.interactWithPlatform(this, worldManifold);
  }
  
  @Override
  public void interactWithEnemy(Enemy enemy, WorldManifold worldManifold){
    enemy.landedPlatform(worldManifold, this);
  }
  
  @Override
  public void interactWithMinigameCharacter(MinigameCharacter minigameCharacter, WorldManifold worldManifold) {
    minigameCharacter.landedPlatform(worldManifold, this);
  }

  @Override
  public void endInteraction(GameActor actor2, WorldManifold worldManifold) {
    actor2.endPlatformInteraction(this, worldManifold);
  }

  public void destroySurvivalPlatform(Array<GameActor> gameActors, Group ground, World survivalWorld, Body actorBody ) {
    gameActors.removeValue(this, true);
    ground.removeActor(this, true);
    survivalWorld.destroyBody(actorBody);
    this.remove();
  }

}
