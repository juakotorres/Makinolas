package cl.makinolas.atk.actors;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;
import com.badlogic.gdx.utils.viewport.FitViewport;

import cl.makinolas.atk.screen.GameScreen;
import cl.makinolas.atk.stages.AbstractStage;
import cl.makinolas.atk.stages.BossStage;
import cl.makinolas.atk.stages.GameStage;
import cl.makinolas.atk.stages.Levels;

public class Portal extends AnimatedActor{
  
  private BodyDef myBodyDefinition; 
  private static final float TILE_FACTOR = 1.8f;
  private Game myGame;
  
  public Portal(World myWorld,Vector2 position, Game game){
    this(myWorld, position.x, position.y, game);
  }
  
  private void setAnimation() {
    setMasterTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/portal2.png"))),40,42);
    addAnimation(0.2f, new int[]{0, 0}, new int[]{0, 1}, new int[]{0, 2},new int[]{0, 3});
  }

  public Portal(World myWorld, float xPosition, float yPosition, Game game){
    
    this.myGame = game;
    myBodyDefinition = new BodyDef();
    myBodyDefinition.type = BodyDef.BodyType.DynamicBody;
    myBodyDefinition.position.set(xPosition*TILE_FACTOR , yPosition*TILE_FACTOR);
    
    Body myBody = myWorld.createBody(myBodyDefinition);
    
    PolygonShape shape = new PolygonShape();
    shape.setAsBox(getBodySize(23), getBodySize(23));
    
    myBody.setGravityScale(0);
    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.isSensor = true;
    fixtureDef.density = 0;
    fixtureDef.shape = shape;
    myBody.createFixture(fixtureDef);
    myBody.resetMassData();
    shape.dispose();
    
    // Guardar body.
    setBody(myBody);
    
    // Guardar animaciones del jugador
    setAnimation();
  }
  

  // This is used to get body width and height.
  private float getBodySize(int size){
    return (0.5f*size)/22;
  }

  @Override
  public void interact(GameActor actor2, WorldManifold worldManifold) {
    actor2.interactWithPortal(this);
  }
  
  @Override
  public void interactWithHero(Hero hero, WorldManifold worldManifold){
    completeStage();
  }


  public void completeStage() {
    Hero.getInstance().completeStage(myGame);
  }
}
